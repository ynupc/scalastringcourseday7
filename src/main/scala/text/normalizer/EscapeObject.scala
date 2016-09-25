package text.normalizer

import text.{StringNone, StringOption, StringSome}
import util.Config

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * @author ynupc
  *         Created on 2016/08/06
  */
class EscapeObject(objectFileNameOpt: StringOption) {

  val objects: Seq[String] = initialize()

  private def initialize(): Seq[String] = {
    if (objectFileNameOpt.isEmpty) {
      return Nil
    }

    val buffer: ListBuffer[String] = ListBuffer[String]()
    Source.fromFile(
      Config.resourceFile("normalizer", objectFileNameOpt.get).toFile
    ).getLines foreach {
      line: String =>
        NormalizedString(StringOption(line.trim)).toStringOption match {
          case StringSome(l) =>
            buffer += l
          case StringNone =>
            //Do nothing
        }
    }

    buffer.result
  }
}
