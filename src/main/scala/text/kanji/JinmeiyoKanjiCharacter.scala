package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object JinmeiyoKanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] =
    readKanjiCSV("jinmeiyo_kanji_1") ++
    readKanjiCSV("jinmeiyo_kanji_2") ++
    JoyoKanjiCharacter.kanji
}