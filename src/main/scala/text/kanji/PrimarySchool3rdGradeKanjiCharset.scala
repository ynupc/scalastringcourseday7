package text.kanji

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
object PrimarySchool3rdGradeKanjiCharset extends KanjiCharset {
  override val kanji: Seq[String] = readKanjiCSV("primary_school_3rd_grade")
}
