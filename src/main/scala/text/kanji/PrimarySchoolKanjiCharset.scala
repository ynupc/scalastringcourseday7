package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object PrimarySchoolKanjiCharset extends KanjiCharset {
  override val kanji: Seq[String] = {
    PrimarySchool1stGradeKanjiCharset.kanji ++
    PrimarySchool2ndGradeKanjiCharset.kanji ++
    PrimarySchool3rdGradeKanjiCharset.kanji ++
    PrimarySchool4thGradeKanjiCharset.kanji ++
    PrimarySchool5thGradeKanjiCharset.kanji ++
    PrimarySchool6thGradeKanjiCharset.kanji
  }
}
