import util.StringUtils._

import scala.collection.mutable

/**
  * @author ynupc
  *         Created on 2016/08/06
  */
object JISStandardKanji {
  private def remainder(sjisCodePoint: Int): Int = {
    val denominator: Int = 0x100
    sjisCodePoint % denominator match {
      case remainder: Int if remainder < 0 =>
        remainder + denominator
      case otherwise => otherwise
    }
  }

  private def isCorrect(sjisCodePoint: Int): Boolean = {
    val r: Int = remainder(sjisCodePoint)
    (0x40 to 0x7E).contains(r) ||
    (0x80 to 0xFC).contains(r)
  }

  private def isJISLevel1(sjisCodePoint: Int): Boolean = {
    (0x889F to 0x9872).contains(sjisCodePoint) &&
    isCorrect(sjisCodePoint)
  }

  private def isJISLevel2(sjisCodePoint: Int): Boolean = {
    (
      (0x989F to 0x9FFC).contains(sjisCodePoint) ||
      (0xE040 to 0xEAA4).contains(sjisCodePoint)
    ) &&
      isCorrect(sjisCodePoint)
  }

  private def isJISLevel3(sjisCodePoint: Int): Boolean = {
    (0x879F to 0x87FC).contains(sjisCodePoint) ||
    (0x8840 to 0x887E).contains(sjisCodePoint) ||
    (0x8880 to 0x889E).contains(sjisCodePoint) ||
    (0x9873 to 0x987E).contains(sjisCodePoint) ||
    (0x9880 to 0x989E).contains(sjisCodePoint) ||
    (
      (0xEAA5 to 0xEFFC).contains(sjisCodePoint) &&
      isCorrect(sjisCodePoint)
    )
  }

  private def isJISLevel4(sjisCodePoint: Int): Boolean = {
    (0xF040 to 0xFCF4).contains(sjisCodePoint) &&
    isCorrect(sjisCodePoint)
  }

  private def getLevel(sjisCodePoint: Int): Byte = {
    if (isJISLevel1(sjisCodePoint)) {
      1: Byte
    } else if (isJISLevel2(sjisCodePoint)) {
      2: Byte
    } else if (isJISLevel3(sjisCodePoint)) {
      3: Byte
    } else if (isJISLevel4(sjisCodePoint)) {
      4: Byte
    } else {
      0: Byte
    }
  }

  private def getCodePointHex(codePoint: Int): String = {
    val size: Int = 4
    codePoint.toHexString match {
      case cp: String if cp.length < size =>
        val builder: mutable.StringBuilder = new mutable.StringBuilder(size)
        for (i <- 0 until (size - cp.length)) {
          builder.append('0')
        }
        builder.append(cp)
        builder.result
      case otherwise => otherwise
    }
  }

  def main(args: Array[String]): Unit = {
    val surrogateRange: Range = 0 to 0xFF
    print("SJIS-Level,SJIS-CodePoint,Unicode-CodePoint,Character\n")
    for (highSurrogate: Int <- surrogateRange; lowSurrogate: Int <- surrogateRange) {
      val sjis: String = highSurrogate.toHexString concat lowSurrogate.toHexString
      val sjisCodePoint: Int = sjis.hexStringToInt
      val level: Byte = getLevel(sjisCodePoint)
      if (0 < level) {
        val str: String = new String(Array(highSurrogate.toByte, lowSurrogate.toByte), "x-MS932_0213")//"x-SJIS_0213"でも可
        val codePoint: Int = str.toCodePointArray.head
        val codePointHex: String = getCodePointHex(codePoint)
        if (codePoint != 0xFFFD) {//double check
          printf("lv.%s,0x%s,U+%s,%s\n",
            level,
            sjis.toUpperCase,
            codePointHex,
            str)
        }
      }
    }
  }
}
