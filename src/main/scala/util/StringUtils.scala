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

  def binaryStringToByteOpt: Option[Byte] = {
    try {
      Option(binaryStringToByte)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def binaryStringToByteOr(defaultValue: Byte): Byte = {
    binaryStringToByteOpt match {
      case Some(byte) =>
        byte
      case None =>
        defaultValue
    }
  }

  def binaryStringToShort: Short = {
    java.lang.Short.parseShort(str, 2)
  }

  def binaryStringToShortOpt: Option[Short] = {
    try {
      Option(binaryStringToShort)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def binaryStringToShortOr(defaultValue: Short): Short = {
    binaryStringToShortOpt match {
      case Some(short) =>
        short
      case None =>
        defaultValue
    }
  }

  def binaryStringToInt: Int = {
    java.lang.Integer.parseInt(str, 2)
  }

  def binaryStringToIntOpt: Option[Int] = {
    try {
      Option(binaryStringToInt)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def binaryStringToIntOr(defaultValue: Int): Int = {
    binaryStringToIntOpt match {
      case Some(int) =>
        int
      case None =>
        defaultValue
    }
  }

  def binaryStringToUnsignedInt: Int = {
    java.lang.Integer.parseUnsignedInt(str, 2)
  }

  def binaryStringToUnsignedIntOpt: Option[Int] = {
    try {
      Option(binaryStringToUnsignedInt)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def binaryStringToUnsignedIntOr(defaultValue: Int): Int = {
    binaryStringToUnsignedIntOpt match {
      case Some(int) =>
        int
      case None =>
        defaultValue
    }
  }

  def binaryStringToLong: Long = {
    java.lang.Long.parseLong(str, 2)
  }

  def binaryStringToLongOpt: Option[Long] = {
    try {
      Option(binaryStringToLong)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def binaryStringToLongOr(defaultValue: Long): Long = {
    binaryStringToLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  def binaryStringToUnsignedLong: Long = {
    java.lang.Long.parseUnsignedLong(str, 2)
  }

  def binaryStringToUnsignedLongOpt: Option[Long] = {
    try {
      Option(binaryStringToUnsignedLong)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def binaryStringToUnsignedLongOr(defaultValue: Long): Long = {
    binaryStringToUnsignedLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  //octalString to AnyVal
  def octalStringToByte: Byte = {
    java.lang.Byte.parseByte(str, 8)
  }

  def octalStringToByteOpt: Option[Byte] = {
    try {
      Option(octalStringToByte)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def octalStringToByteOr(defaultValue: Byte): Byte = {
    octalStringToByteOpt match {
      case Some(byte) =>
        byte
      case None =>
        defaultValue
    }
  }

  def octalStringToShort: Short = {
    java.lang.Short.parseShort(str, 8)
  }

  def octalStringToShortOpt: Option[Short] = {
    try {
      Option(octalStringToShort)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def octalStringToShortOr(defaultValue: Short): Short = {
    octalStringToShortOpt match {
      case Some(short) =>
        short
      case None =>
        defaultValue
    }
  }

  def octalStringToInt: Int = {
    java.lang.Integer.parseInt(str, 8)
  }

  def octalStringToIntOpt: Option[Int] = {
    try {
      Option(octalStringToInt)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def octalStringToIntOr(defaultValue: Int): Int = {
    octalStringToIntOpt match {
      case Some(int) =>
        int
      case None =>
        defaultValue
    }
  }

  def octalStringToUnsignedInt: Int = {
    java.lang.Integer.parseUnsignedInt(str, 8)
  }

  def octalStringToUnsignedIntOpt: Option[Int] = {
    try {
      Option(octalStringToUnsignedInt)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def octalStringToUnsignedIntOr(defaultValue: Int): Int = {
    octalStringToUnsignedIntOpt match {
      case Some(int) =>
        int
      case None =>
        defaultValue
    }
  }

  def octalStringToLong: Long = {
    java.lang.Long.parseLong(str, 8)
  }

  def octalStringToLongOpt: Option[Long] = {
    try {
      Option(octalStringToLong)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def octalStringToLongOr(defaultValue: Long): Long = {
    octalStringToLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  def octalStringToUnsignedLong: Long = {
    java.lang.Long.parseUnsignedLong(str, 8)
  }

  def octalStringToUnsignedLongOpt: Option[Long] = {
    try {
      Option(octalStringToUnsignedLong)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def octalStringToUnsignedLongOr(defaultValue: Long): Long = {
    octalStringToUnsignedLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  //hexString to AnyVal
  def hexStringToByte: Byte = {
    java.lang.Byte.parseByte(str, 0x10)
  }

  def hexStringToByteOpt: Option[Byte] = {
    try {
      Option(hexStringToByte)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def hexStringToByteOr(defaultValue: Byte): Byte = {
    hexStringToByteOpt match {
      case Some(byte) =>
        byte
      case None =>
        defaultValue
    }
  }

  def hexStringToShort: Short = {
    java.lang.Short.parseShort(str, 0x10)
  }

  def hexStringToShortOpt: Option[Short] = {
    try {
      Option(hexStringToShort)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def hexStringToShortOr(defaultValue: Short): Short = {
    hexStringToShortOpt match {
      case Some(short) =>
        short
      case None =>
        defaultValue
    }
  }

  def hexStringToInt: Int = {
    java.lang.Integer.parseInt(str, 0x10)
  }

  def hexStringToIntOpt: Option[Int] = {
    try {
      Option(hexStringToInt)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def hexStringToIntOr(defaultValue: Int): Int = {
    hexStringToIntOpt match {
      case Some(int) =>
        int
      case None =>
        defaultValue
    }
  }

  def hexStringToUnsignedInt: Int = {
    java.lang.Integer.parseUnsignedInt(str, 0x10)
  }

  def hexStringToUnsignedIntOpt: Option[Int] = {
    try {
      Option(hexStringToUnsignedInt)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def hexStringToUnsignedIntOr(defaultValue: Int): Int = {
    hexStringToUnsignedIntOpt match {
      case Some(int) =>
        int
      case None =>
        defaultValue
    }
  }

  def hexStringToLong: Long = {
    java.lang.Long.parseLong(str, 0x10)
  }

  def hexStringToLongOpt: Option[Long] = {
    try {
      Option(hexStringToLong)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def hexStringToLongOr(defaultValue: Long): Long = {
    hexStringToLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  def hexStringToUnsignedLong: Long = {
    java.lang.Long.parseUnsignedLong(str, 0x10)
  }

  def hexStringToUnsignedLongOpt: Option[Long] = {
    try {
      Option(hexStringToUnsignedLong)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def hexStringToUnsignedLongOr(defaultValue: Long): Long = {
    hexStringToUnsignedLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  def toIntOpt: Option[Int] = {
    try {
      Option(str.toInt)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def toIntOr(defaultValue: Int): Int = {
    toIntOpt match {
      case Some(int) =>
        int
      case otherwise =>
        defaultValue
    }
  }

  def toLongOpt: Option[Long] = {
    try {
      Option(str.toLong)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def toLongOr(defaultValue: Long): Long = {
    toLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  def toShortOpt: Option[Short] = {
    try {
      Option(str.toShort)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def toShortOr(defaultValue: Short): Short = {
    toShortOpt match {
      case Some(short) =>
        short
      case None =>
        defaultValue
    }
  }

  def toByteOpt: Option[Byte] = {
    try {
      Option(str.toByte)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def toByteOr(defaultValue: Byte): Byte = {
    toByteOpt match {
      case Some(byte) =>
        byte
      case None =>
        defaultValue
    }
  }

  def toFloatOpt: Option[Float] = {
    try {
      Option(str.toFloat)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def toFloatOr(defaultValue: Float): Float = {
    toFloatOpt match {
      case Some(float) =>
        float
      case None =>
        defaultValue
    }
  }

  def toDoubleOpt: Option[Double] = {
    try {
      Option(str.toDouble)
    } catch {
      case e: NumberFormatException =>
        None
    }
  }

  def toDoubleOr(defaultValue: Double): Double = {
    toDoubleOpt match {
      case Some(double) =>
        double
      case None =>
        defaultValue
    }
  }

  def toBooleanOpt: Option[Boolean] = {
    try {
      Option(java.lang.Boolean.parseBoolean(str))
    } catch {
      case e: IllegalArgumentException =>
        None
    }
  }

  def toBooleanOr(defaultValue: Boolean): Boolean = {
    toBooleanOpt match {
      case Some(boolean) =>
        boolean
      case None =>
        defaultValue
    }
  }
}
