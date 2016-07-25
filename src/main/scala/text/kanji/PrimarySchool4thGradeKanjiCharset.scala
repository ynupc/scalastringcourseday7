package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object PrimarySchool4thGradeKanjiCharset extends KanjiCharset {
  override val kanji: Seq[String] = readKanjiCSV("primary_school_4th_grade")
}
