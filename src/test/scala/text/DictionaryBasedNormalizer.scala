package text

import java.nio.file.{Path, Paths}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.sys.process.Process
import scala.util.matching.Regex

/**
  * @author ynupc
  *         Created on 2016/02/20
  */
class DictionaryBasedNormalizer(dictionaryNameOpt: StringOption) {
  private def ascii2native(inputPath: Path): Stream[String] = {
    Process(Seq[String](
      "native2ascii",
      "-reverse",
      "-encoding", "UTF-8",
      inputPath.toAbsolutePath.toString)).lineStream_!
  }
  private val basePath: String = "../../src/test/resources/"
  private val regex: Regex = """([^#:][^:]*):\[([^\]]+)\](#.*)?""".r
  private val terms: Seq[(String, String)] = initialize()

  private def initialize(): Seq[(String, String)] = {
    if (dictionaryNameOpt.isEmpty) {
      return Nil
    }
    val dictionaryName: String = dictionaryNameOpt.get
    val map: mutable.Map[String, List[String]] = mutable.Map[String, List[String]]()
    val buffer: ListBuffer[(String, String)] = ListBuffer[(String, String)]()
    val filePath: Path = Paths.get(basePath, dictionaryName)
    val lines: Iterator[String] = ascii2native(filePath).toIterator
    while (lines.hasNext) {
      val line: String = lines.next.trim
      line match {
        case regex(representation, notationalVariants, commentOut) =>
          var trimmedRepresentation: String = representation.trim
          if (trimmedRepresentation == "\"\"") {
            trimmedRepresentation = ""
          } else {
            val sortedNotationalVariants: List[String] = sortNotationVariants(notationalVariants.split(',').toList) //.
            map(trimmedRepresentation) = if (map.contains(trimmedRepresentation)) {
              sortNotationVariants(map(trimmedRepresentation) ++ sortedNotationalVariants)
            } else {
              sortedNotationalVariants
            }
          }
        case otherwise =>
        //Do nothing
      }
    }
    sortRepresentations(map.keySet.toList) foreach {
      representation =>
        map(representation) foreach {
          notationalVariant =>
            buffer += ((notationalVariant, representation))
        }
    }
    buffer.toSeq
  }

  protected def sortNotationVariants(notationVariants: List[String]): List[String] = {
    notationVariants.sorted//alphabetical order
  }

  protected def sortRepresentations(representations: List[String]): List[String] = {
    representations.sorted//alphabetical order
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
