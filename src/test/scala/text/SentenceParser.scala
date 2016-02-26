package text

import java.nio.file.Paths

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * @author ynupc
  *         Created on 2016/02/21
  */
object SentenceParser {
  private final val japanesePeriodRegex: String = "[。．]"
  private final val japaneseCommaRegex: String  = "[、，]"
  private final val japanesePeriod: String = "。"
  private final val japanesePeriod2: String = "．"
  private final val japaneseComma: String  = "、"

  private final val basePath: String = "../../src/test/resources/"
  private final val properNounsWithJapanesePeriod: Seq[String] = initialize()
  private final val ghost: String = "妛"
  private final val ghost2: String = "彁"

  // このようなprivateな型を作成しておかないとobject SentenceParser内のprivate class NormalizedSentenceをSeqで返すparseメソッドが作成ができない。
  private type NS = NormalizedSentence

  private def initialize(): Seq[String] = {
    Source.fromFile(
      Paths.get(basePath, "proper_noun_with_japanese_period.txt").toFile
    ).getLines().toSeq.sortWith((a, b) => a.length > b.length)
  }

  def parse(textOpt: StringOption): Seq[NS] = {
    if (textOpt.isEmpty) {
      return Nil
    }

    val sentences: ListBuffer[NormalizedSentence] = ListBuffer[NormalizedSentence]()

    //改行文字により行に分割
    textOpt.get split '\n' foreach {
      l =>
        var line: String = l
        sentences ++= {
          for (properNoun <- properNounsWithJapanesePeriod if line contains properNoun) {
            line = line.replaceAllLiterally(
              properNoun,
              properNoun.
                replaceAllLiterally(japanesePeriod, ghost).
                replaceAllLiterally(japanesePeriod2, ghost2)
            )
          }

          //句点により文単位に分割
          for (sentence <- line.trim split japanesePeriodRegex
               if StringOption(sentence).nonEmpty) yield {
            new NormalizedSentence(parseSentence(sentence).
              replaceAllLiterally(ghost, japanesePeriod).
              replaceAllLiterally(ghost2, japanesePeriod2)
            )
          }
        }
    }

    sentences.result
  }

  private def parseSentence(sentence: String): String = {
    val phrases: StringBuilder = new StringBuilder()

    //読点により節に分割
    sentence.trim split japaneseCommaRegex foreach {
      phrase =>
        phrases.
          //節を正規化
          append(NormalizedString(StringOption(phrase.trim)).toString).
          //節末に読点を追加
          append(japaneseComma)
    }

    phrases.
      //文末の読点を削除
      deleteCharAt(phrases.size - 1).
      //文末に句点を追加
      append(japanesePeriod).
      result
  }

  private class NormalizedSentence(val text: String) {
    override def toString: String = text
  }
}
