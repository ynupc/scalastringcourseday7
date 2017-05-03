package util

/**
  * @author ynupc
  *         Created on 2017/05/01
  */
package object primitive {

  implicit class StringUtils(repr: CharSequence) extends StringUtilsConversions {
    override protected def str: String = {
      repr match {
        case s: String => s
        case otherwise =>
          otherwise.toString
      }
    }

    override def replaceAllLiteratim(target: CharSequence, replacement: CharSequence): String = {
      str.replace(target, replacement)
    }

    override def quote(quotation: (String, String)): String = {
      new StringBuilder().
        append(quotation._1).
        append(repr).
        append(quotation._2).
        result
    }

    override def codePointNumber: Int = {
      str.codePointCount(0, str.length)
    }

    override def toCodePointArray: Array[Int] = {
      if (Option(repr).isEmpty) {
        throw new NullPointerException
      }

      val charArray: Array[Char] = str.toCharArray
      val length: Int = charArray.length
      var surrogatePairCount: Int = 0
      var isSkipped = false
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
      val codePoints = new Array[Int](length - surrogatePairCount)
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
    override def binaryStringToByte: Byte = {
      java.lang.Byte.parseByte(str, 2)
    }

    override def binaryStringToShort: Short = {
      java.lang.Short.parseShort(str, 2)
    }

    override def binaryStringToInt: Int = {
      java.lang.Integer.parseInt(str, 2)
    }

    override def binaryStringToUnsignedInt: Int = {
      java.lang.Integer.parseUnsignedInt(str, 2)
    }

    override def binaryStringToLong: Long = {
      java.lang.Long.parseLong(str, 2)
    }

    override def binaryStringToUnsignedLong: Long = {
      java.lang.Long.parseUnsignedLong(str, 2)
    }

    //octalString to AnyVal
    override def octalStringToByte: Byte = {
      java.lang.Byte.parseByte(str, 8)
    }

    override def octalStringToShort: Short = {
      java.lang.Short.parseShort(str, 8)
    }

    override def octalStringToInt: Int = {
      java.lang.Integer.parseInt(str, 8)
    }

    override def octalStringToUnsignedInt: Int = {
      java.lang.Integer.parseUnsignedInt(str, 8)
    }

    override def octalStringToLong: Long = {
      java.lang.Long.parseLong(str, 8)
    }

    override def octalStringToUnsignedLong: Long = {
      java.lang.Long.parseUnsignedLong(str, 8)
    }

    //hexString to AnyVal
    override def hexStringToByte: Byte = {
      java.lang.Byte.parseByte(str, 0x10)
    }

    override def hexStringToShort: Short = {
      java.lang.Short.parseShort(str, 0x10)
    }

    override def hexStringToInt: Int = {
      java.lang.Integer.parseInt(str, 0x10)
    }

    override def hexStringToUnsignedInt: Int = {
      java.lang.Integer.parseUnsignedInt(str, 0x10)
    }

    override def hexStringToLong: Long = {
      java.lang.Long.parseLong(str, 0x10)
    }

    override def hexStringToUnsignedLong: Long = {
      java.lang.Long.parseUnsignedLong(str, 0x10)
    }

    override def toIntOpt: Option[Int] = {
      try {
        Option(str.toInt)
      } catch {
        case e: NumberFormatException =>
          e.printStackTrace()
          None
      }
    }

    override def toLongOpt: Option[Long] = {
      try {
        Option(str.toLong)
      } catch {
        case e: NumberFormatException =>
          e.printStackTrace()
          None
      }
    }

    override def toShortOpt: Option[Short] = {
      try {
        Option(str.toShort)
      } catch {
        case e: NumberFormatException =>
          e.printStackTrace()
          None
      }
    }

    override def toByteOpt: Option[Byte] = {
      try {
        Option(str.toByte)
      } catch {
        case e: NumberFormatException =>
          e.printStackTrace()
          None
      }
    }

    override def toFloatOpt: Option[Float] = {
      try {
        Option(str.toFloat)
      } catch {
        case e: NumberFormatException =>
          e.printStackTrace()
          None
      }
    }

    override def toDoubleOpt: Option[Double] = {
      try {
        Option(str.toDouble)
      } catch {
        case e: NumberFormatException =>
          e.printStackTrace()
          None
      }
    }

    override def toBooleanOpt: Option[Boolean] = {
      try {
        Option(java.lang.Boolean.parseBoolean(str))
      } catch {
        case e: IllegalArgumentException =>
          e.printStackTrace()
          None
      }
    }
  }
}