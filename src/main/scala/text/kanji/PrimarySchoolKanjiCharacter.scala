package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object PrimarySchoolKanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] = {
    PrimarySchool1StGradeKanjiCharacter.kanji ++
    PrimarySchool2NdGradeKanjiCharacter.kanji ++
    PrimarySchool3RdGradeKanjiCharacter.kanji ++
    PrimarySchool4ThGradeKanjiCharacter.kanji ++
    PrimarySchool5ThGradeKanjiCharacter.kanji ++
    PrimarySchool6ThGradeKanjiCharacter.kanji
  }
}
