package text

import scala.collection.mutable.ListBuffer

/**
  * @author ynupc
  *         Created on 2016/02/21
  */
object SentenceParser {
  private final val japanesePeriodRegex: String = "[。．]"
  private final val japaneseCommaRegex: String  = "[、，]"
  private final val japanesePeriod: String = "。"
  private final val japaneseComma: String  = "、"

  def parse(textOpt: StringOption): Seq[NormalizedSentence] = {
    if (textOpt.isEmpty) {
      return Nil
    }

    val sentences: ListBuffer[NormalizedSentence] = ListBuffer[NormalizedSentence]()

    //改行文字により行に分割
    textOpt.get split '\n' foreach {
      line =>
        sentences ++= {
          //句点により文単位に分割
          for (sentence <- line.trim split japanesePeriodRegex
               if StringOption(sentence).nonEmpty) yield {
            new NormalizedSentence(parseSentence(sentence))
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

  private class NormalizedSentence(val text: String)
}
