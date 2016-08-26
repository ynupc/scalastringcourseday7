package text.parser

import java.nio.file.Paths

import text.normalizer._
import text.{StringNone, StringOption, StringSome}
import util.Config
import util.StringUtils._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.matching.Regex
import scala.util.matching.Regex.Match

/**
  * @author ynupc
  *         Created on 2016/02/21
  */
object SentenceQuotationParser {
  private type Quotation = (String, String)
  private type QuotationSentence = (Quotation, QuotedSentence)

  private final val quotations: Seq[Quotation] = {
    val buffer: ListBuffer[Quotation] = ListBuffer[Quotation]()
    Source.fromFile(
      Paths.get(Config.resourcesDir, "parser", "quotation.csv").toAbsolutePath.toFile
    ).getLines foreach {
      line: String =>
        val quotations: Array[String] = line.trim.split(',')
        if (2 <= quotations.length) {
          val headOpt: StringOption = StringOption(quotations.head.trim)
          val lastOpt: StringOption = StringOption(quotations.last.trim)
          if (headOpt.nonEmpty && lastOpt.nonEmpty) {
            buffer += ((headOpt.get, lastOpt.get))
          }
        }
    }
    buffer.result
  }

  private def getUnContainedNoun(text: String, nouns: Seq[String]): String = {
    nouns foreach {
      noun: String =>
        if (!text.contains(noun)) {
          return noun
        }
    }
    throw new NoSuchElementException("SentenceQuotationParser.getUnContainedNoun")
  }

  def parse(sentenceOpt: StringOption): Option[QuotedSentence] = {
    sentenceOpt match {
      case StringSome(sentence) =>
        Option(parse(sentence, ListBuffer[String]() ++ EscapeNoun.objects))
      case StringNone =>
        None
    }
  }

  private def getFirstMatchOpt(sentence: String): Option[(Quotation, Range)] = {
    val quotationRangeBuffer: ListBuffer[(Quotation, Range)] = ListBuffer[(Quotation, Range)]()
    var firstMatchOpt: Option[(Quotation, Range)] = None

    quotations foreach {
      quotation: Quotation =>
        val regex: Regex = s".+".quote(quotation).r
        regex.findFirstMatchIn(sentence) foreach {
          m: Match =>
            quotationRangeBuffer += ((quotation, Range(m.start, m.end)))
        }
    }

    if (quotationRangeBuffer.nonEmpty) {
      quotationRangeBuffer foreach {
        case (quotation, range) =>
          if (firstMatchOpt.isEmpty || (range.start < firstMatchOpt.get._2.start)) {
            firstMatchOpt = Some((quotation, range))
          }
        case otherwise =>
          //Do nothing
      }
    }

    firstMatchOpt
  }

  private def parse(sentence: String, nouns: ListBuffer[String]): QuotedSentence = {
    var parentSentence: String = sentence
    val childrenSentences: mutable.Map[String, QuotationSentence] = mutable.Map[String, QuotationSentence]()

    var firstMatchOpt: Option[(Quotation, Range)] = getFirstMatchOpt(sentence)

    while (firstMatchOpt.nonEmpty) {
      val (quotation, range): (Quotation, Range) = firstMatchOpt.get
      val start: Int = range.start
      val end:   Int = range.end
      val prefix:     String = parentSentence.substring(0, start)
      val quotedPart: String = parentSentence.substring(start, end)
      val suffix:     String = parentSentence.substring(end)
      val noun:       String = getUnContainedNoun(parentSentence, nouns)
      nouns -= noun
      parentSentence = noun.quote((prefix, suffix))
      childrenSentences.put(noun, (quotation, parse(quotedPart, nouns)))
      firstMatchOpt = getFirstMatchOpt(sentence)
    }

    new QuotedSentence(parentSentence, childrenSentences.toMap)
  }

  private class QuotedSentence(val parentSentence: String,
                               val childrenSentences: Map[String, QuotationSentence]) {
    override def toString: String = {
      var text: String = parentSentence
      childrenSentences foreach {
        case (replacement, (quotation, sentence)) =>
          text = text.replaceAllLiteratim(
            replacement,
            sentence.toString.quote(quotation))
        case otherwise =>
          //Do nothing
      }
      text
    }
  }

  def splitAndQuotationParseJapaneseText(textOpt: StringOption): Seq[NormalizedQuotedSentence] = {
    val buffer: ListBuffer[NormalizedQuotedSentence] = ListBuffer[NormalizedQuotedSentence]()
    JapaneseSentenceSplitter.split(textOpt) foreach {
      sentence =>
        parse(StringOption(sentence.text)) match {
          case Some(quotedSentence) =>
            buffer += new NormalizedQuotedSentence(sentence.originalText, quotedSentence)
          case None =>
            //Do nothing
        }
    }
    buffer.result
  }

  private class NormalizedQuotedSentence(val originalText: String,
                                         val quotedSentence: QuotedSentence) {
    override def toString: String = quotedSentence.toString
  }
}

