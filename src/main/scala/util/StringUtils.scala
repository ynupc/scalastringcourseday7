package util

/**
  * @author ynupc
  *         Created on 2016/08/07
  */
object StringUtils {
  implicit def stringToStringUtils(repr: CharSequence): StringUtils = {
    new StringUtils(repr)
  }
}

class StringUtils(repr: CharSequence) {
  private val str: String = {
    repr match {
      case s: String => s
      case otherwise =>
        otherwise.toString
    }
  }

  def replaceAllLiteratim(target: CharSequence, replacement: CharSequence): String = {
    str.replace(target, replacement)
  }

  def quote(quotation: (String, String)): String = {
    new StringBuilder().
      append(quotation._1).
      append(repr).
      append(quotation._2).
      result
  }

  def codePointCount: Int = {
    str.codePointCount(0, str.length)
  }

  def toCodePointArray: Array[Int] = {
    if (repr == null) {
      throw new NullPointerException
    }

    val charArray: Array[Char] = str.toCharArray
    val length: Int = charArray.length
    var surrogatePairCount: Int = 0
    var isSkipped: Boolean = false
    for (i <- 0 until length) {
      if (isSkipped) {
        isSkipped = false
      } else {
        if (0 < i && Character.isSurrogatePair(charArray(i - 1), charArray(i))) {
          surrogatePairCount += 1
          isSkipped = true
        }
      }
    }
    isSkipped = false
    val codePoints: Array[Int] = new Array[Int](length - surrogatePairCount)
    var j: Int = 0
    for (i <- 0 until length) {
      if (isSkipped) {
        isSkipped = false
      } else {
        val currentChar: Char = charArray(i)
        if (Character.isHighSurrogate(currentChar) && i + 1 < length) {
          val nextChar: Char = charArray(i + 1)
          if (Character.isLowSurrogate(nextChar)) {
            codePoints(j) = Character.toCodePoint(currentChar, nextChar)
            j += 1
            isSkipped = true
          }
        }
        if (!isSkipped) {
          codePoints(j) = currentChar
          j += 1
        }
      }
    }
    codePoints
  }

  //binaryString to AnyVal
  def binaryStringToByte: Byte = {
    java.lang.Byte.parseByte(str, 2)
  }

  def binaryStringToShort: Short = {
    java.lang.Short.parseShort(str, 2)
  }

  def binaryStringToInt: Int = {
    java.lang.Integer.parseInt(str, 2)
  }

  def binaryStringToUnsignedInt: Int = {
    java.lang.Integer.parseUnsignedInt(str, 2)
  }

  def binaryStringToLong: Long = {
    java.lang.Long.parseLong(str, 2)
  }

  def binaryStringToUnsignedLong: Long = {
    java.lang.Long.parseUnsignedLong(str, 2)
  }

  //octalString to AnyVal
  def octalStringToByte: Byte = {
    java.lang.Byte.parseByte(str, 8)
  }

  def octalStringToShort: Short = {
    java.lang.Short.parseShort(str, 8)
  }

  def octalStringToInt: Int = {
    java.lang.Integer.parseInt(str, 8)
  }

  def octalStringToUnsignedInt: Int = {
    java.lang.Integer.parseUnsignedInt(str, 8)
  }

  def octalStringToLong: Long = {
    java.lang.Long.parseLong(str, 8)
  }

  def octalStringToUnsignedLong: Long = {
    java.lang.Long.parseUnsignedLong(str, 8)
  }

  //hexString to AnyVal
  def hexStringToByte: Byte = {
    java.lang.Byte.parseByte(str, 0x10)
  }

  def hexStringToShort: Short = {
    java.lang.Short.parseShort(str, 0x10)
  }

  def hexStringToInt: Int = {
    java.lang.Integer.parseInt(str, 0x10)
  }

  def hexStringToUnsignedInt: Int = {
    java.lang.Integer.parseUnsignedInt(str, 0x10)
  }

  def hexStringToLong: Long = {
    java.lang.Long.parseLong(str, 0x10)
  }

  def hexStringToUnsignedLong: Long = {
    java.lang.Long.parseUnsignedLong(str, 0x10)
  }
}
