package util

import java.io.ByteArrayInputStream
import java.nio.charset.{Charset, CodingErrorAction}

import text.StringOption

import scala.collection.mutable.ListBuffer
import scala.io.{Codec, Source}
import scala.sys.process.ProcessBuilder

/**
  * @author ynupc
  *         Created on 2016/09/14
  */
object ProcessBuilderUtils {
  implicit def processToProcessUtils(repr: ProcessBuilder): ProcessBuilderUtils = {
    new ProcessBuilderUtils(repr)
  }
}

class ProcessBuilderUtils(repr: ProcessBuilder) {
  def lineStream(encoding: Charset,
                 onMalformedInput: CodingErrorAction,
                 onUnmappableCharacter: CodingErrorAction,
                 replacementOpt: StringOption): Iterator[String] = {
    val lines: Iterator[String] = repr.lineStream_!.iterator
    val byteBuffer = ListBuffer.empty[Byte]
    while (lines.hasNext) {
      val line: String = lines.next.trim concat "\n"
      byteBuffer ++= line.getBytes
    }
    implicit val codec = Codec(encoding).
      onMalformedInput(onMalformedInput).
      onUnmappableCharacter(onUnmappableCharacter)
    if (replacementOpt.nonEmpty) {
      codec.decodingReplaceWith(replacementOpt.get)
    }
    Source.fromInputStream(new ByteArrayInputStream(byteBuffer.toArray)).getLines
  }
}
