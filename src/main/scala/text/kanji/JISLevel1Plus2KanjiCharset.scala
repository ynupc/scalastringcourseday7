package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object JISLevel1Plus2KanjiCharset extends KanjiCharset {
  override val kanji: Seq[String] = JISLevel1KanjiCharset.kanji ++ JISLevel2KanjiCharset.kanji
}
