package text

import java.io.File

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * @author ynupc
  *         Created on 2016/02/19
  */
object WordExpressionNormalizer {
  private val regex = """([^:]+):\[([^\]]+)\]""".r
  private val terms: Seq[(String, String)] = initialize()

  private def initialize(): Seq[(String, String)] = {
    val map: mutable.Map[String, Seq[String]] = mutable.Map[String, Seq[String]]()
    val buffer: ListBuffer[(String, String)] = ListBuffer[(String, String)]()
    val lines: Iterator[String] = Source.fromFile(
      new File("../../src/test/resources/word_expression_dic.yml")
    ).getLines()
    while (lines.hasNext) {
      val line: String = lines.next.trim
      line match {
        case regex(representation, notationalVariants) =>
          val trimmedRepresentation: String = representation.trim
          val sortedNotationalVariants: List[String] = notationalVariants.split(',').toList.
            sorted.//alphabetical order
            sortWith((a, b) => a.length > b.length)//length descending order
          map(trimmedRepresentation) = if (map.contains(trimmedRepresentation)) {
            (map(trimmedRepresentation) ++ sortedNotationalVariants).
              sorted.//alphabetical order
              sortWith((a, b) => a.length > b.length)//length descending order
          } else {
            sortedNotationalVariants
          }
        case otherwise =>
          //Do nothing
      }
    }
    map.keySet.toList.
      sorted.//alphabetical order
      sortWith((a, b) => a.length < b.length).//length ascending order
      foreach {
      representation =>
        map(representation) foreach {
          notationalVariant =>
            buffer += ((notationalVariant, representation))
        }
    }
    buffer.toSeq
  }

  def normalize(text: StringOption): StringOption = {
    text map {
      t: String =>
        var result: String = t
        if (terms.nonEmpty) {
          terms foreach {
            case (term, replacement) =>
              result = result.replaceAllLiterally(term, replacement)
            case otherwise =>
            //Do nothing
          }
        }
        result
    }
  }
}

