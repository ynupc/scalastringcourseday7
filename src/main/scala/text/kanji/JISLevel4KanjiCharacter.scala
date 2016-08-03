package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object JISLevel4KanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] = readKanjiCSV("jis_level_4")
}