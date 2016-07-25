package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object PrimarySchoolKanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] = {
    PrimarySchool1stGradeKanjiCharacter.kanji ++
    PrimarySchool2ndGradeKanjiCharacter.kanji ++
    PrimarySchool3rdGradeKanjiCharacter.kanji ++
    PrimarySchool4thGradeKanjiCharacter.kanji ++
    PrimarySchool5thGradeKanjiCharacter.kanji ++
    PrimarySchool6thGradeKanjiCharacter.kanji
  }
}
