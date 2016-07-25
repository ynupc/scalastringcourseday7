package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object JoyoKanjiCharset extends KanjiCharset {
  override val kanji: Seq[String] = readKanjiCSV("joyo_kanji")
}
