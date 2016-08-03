package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object JISLevel1to4KanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] =
    JISLevel1KanjiCharacter.kanji ++
    JISLevel2KanjiCharacter.kanji ++
    JISLevel3KanjiCharacter.kanji ++
    JISLevel4KanjiCharacter.kanji
}
