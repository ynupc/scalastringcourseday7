package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object PrimarySchool6thGradeKanjiCharset extends KanjiCharset {
  override val kanji: Seq[String] = readKanjiCSV("primary_school_6th_grade")
}
