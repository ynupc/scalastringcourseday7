package text.kanji

import java.io.File
import java.util.regex.Pattern

import util.Config

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * @author ynupc
  *         Created on 2016/07/26
  */
trait KanjiCharacter {
  val kanji: Seq[String]

  lazy val regex: String = {
    val size: Int = kanji.size
    if (size <= 0) {
      ""
    } else {
      val builder: StringBuilder = new StringBuilder(size)
      kanji foreach builder.append
      builder.result.mkString("[", "", "]")
    }
  }

  lazy val pattern: Pattern = Pattern.compile(regex)

  def isDefined(codePoint: Int): Boolean = {
    if (Character.isValidCodePoint(codePoint)) {
      pattern.matcher(new String(Array(codePoint), 0, 1)).matches
    } else {
      false
    }
  }

  def notDefined(codePoint: Int): Boolean = {
    !isDefined(codePoint)
  }

  def isDefined(char: Char): Boolean = {
    pattern.matcher(char.toString).matches
  }

  def notDefined(char: Char): Boolean = {
    !isDefined(char)
  }

  protected def readKanjiCSV(fileName: String): Seq[String] = {
    val buffer: ListBuffer[String] = ListBuffer[String]()
    val file: File = Config.resourceFile("kanji", fileName.concat(".csv")).toFile
    if (file.canRead && file.isFile) {
      Source.fromFile(file).getLines foreach {
        line =>
          val elements: Array[String] = line.split(",")
          if (elements.nonEmpty && elements.length == 2) {
            val kanji: String = elements(1)
            buffer += kanji
          }
      }
    }
    buffer.result
  }
}
