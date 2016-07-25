package text.normalizer

import java.nio.file.Paths

import util.Config

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

  private final val properNounsWithJapanesePeriod: Seq[String] = initialize
  private final val ghost: String = "妛"
  private final val ghost2: String = "彁"

  // このようなprivateな型を作成しておかないとobject SentenceParser内のprivate class NormalizedSentenceをSeqで返すparseメソッドが作成ができない。
  private type NS = NormalizedSentence

  private def initialize: Seq[String] = {
    Source.fromFile(
      Paths.get(Config.resourcesDir, "normalizer", "proper_noun_with_japanese_period.txt").toAbsolutePath.toFile
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
        val replacementBuffer: ListBuffer[String] = ListBuffer[String]()

        //句点を含む固有名詞の句点を幽霊文字に変換
        for (properNoun <- properNounsWithJapanesePeriod
             if line contains properNoun) {
          val replacement: String = properNoun.
            replaceAllLiterally(japanesePeriod, ghost).
            replaceAllLiterally(japanesePeriod2, ghost2)
          replacementBuffer += replacement
          line = line.replaceAllLiterally(
            properNoun,
            replacement
          )
        }

        val replacements: Seq[String] = replacementBuffer.result

        //句点により文単位に分割
        for (sentence <- line.trim split japanesePeriodRegex
             if StringOption(sentence).nonEmpty) {
          //正規化処理
          val sOpt: Option[String] = parseSentence(sentence)
          if (sOpt.nonEmpty) {
            var s: String = sOpt.get
            //幽霊文字を元の句点に戻す
            for (replacement <- replacements) {
              val normalizedProperNoun: String = NormalizedString(StringOption(replacement)).toString
              val normalizedProperNounWithJapanesePeriod: String = normalizedProperNoun.
                replaceAllLiterally(ghost, japanesePeriod).
                replaceAllLiterally(ghost2, japanesePeriod2)
              s = s.replaceAllLiterally(normalizedProperNoun, normalizedProperNounWithJapanesePeriod)
            }
            sentences += new NormalizedSentence(s)
          }
        }
    }

    sentences.result
  }

  private def parseSentence(sentence: String): Option[String] = {
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

    if (phrases.isEmpty) {
      return None
    }

    Option(
      phrases.
        //文末の読点を削除
        deleteCharAt(phrases.size - 1).
        //文末に句点を追加
        append(japanesePeriod).
        result)
  }

  private class NormalizedSentence(val text: String) {
    override def toString: String = text
  }
}
