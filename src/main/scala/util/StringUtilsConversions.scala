package util

/**
  * @author ynupc
  *         Created on 2017/05/01
  */
trait StringUtilsConversions {
  protected def str: String

  def replaceAllLiteratim(target: CharSequence, replacement: CharSequence): String

  def quote(quotation: (String, String)): String

  def codePointNumber: Int

  def toCodePointArray: Array[Int]

  //binaryString to AnyVal
  def binaryStringToByte: Byte

  def binaryStringToByteOpt: Option[Byte] = {
    try {
      Option(binaryStringToByte)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def binaryStringToShort: Short

  def binaryStringToShortOpt: Option[Short] = {
    try {
      Option(binaryStringToShort)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def binaryStringToInt: Int

  def binaryStringToIntOpt: Option[Int] = {
    try {
      Option(binaryStringToInt)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def binaryStringToUnsignedInt: Int

  def binaryStringToUnsignedIntOpt: Option[Int] = {
    try {
      Option(binaryStringToUnsignedInt)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def binaryStringToLong: Long

  def binaryStringToLongOpt: Option[Long] = {
    try {
      Option(binaryStringToLong)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def binaryStringToUnsignedLong: Long

  def binaryStringToUnsignedLongOpt: Option[Long] = {
    try {
      Option(binaryStringToUnsignedLong)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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
  def octalStringToByte: Byte

  def octalStringToByteOpt: Option[Byte] = {
    try {
      Option(octalStringToByte)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def octalStringToShort: Short

  def octalStringToShortOpt: Option[Short] = {
    try {
      Option(octalStringToShort)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def octalStringToInt: Int

  def octalStringToIntOpt: Option[Int] = {
    try {
      Option(octalStringToInt)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def octalStringToUnsignedInt: Int

  def octalStringToUnsignedIntOpt: Option[Int] = {
    try {
      Option(octalStringToUnsignedInt)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def octalStringToLong: Long

  def octalStringToLongOpt: Option[Long] = {
    try {
      Option(octalStringToLong)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def octalStringToUnsignedLong: Long

  def octalStringToUnsignedLongOpt: Option[Long] = {
    try {
      Option(octalStringToUnsignedLong)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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
  def hexStringToByte: Byte

  def hexStringToByteOpt: Option[Byte] = {
    try {
      Option(hexStringToByte)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def hexStringToShort: Short

  def hexStringToShortOpt: Option[Short] = {
    try {
      Option(hexStringToShort)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def hexStringToInt: Int

  def hexStringToIntOpt: Option[Int] = {
    try {
      Option(hexStringToInt)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def hexStringToUnsignedInt: Int

  def hexStringToUnsignedIntOpt: Option[Int] = {
    try {
      Option(hexStringToUnsignedInt)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def hexStringToLong: Long

  def hexStringToLongOpt: Option[Long] = {
    try {
      Option(hexStringToLong)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def hexStringToUnsignedLong: Long

  def hexStringToUnsignedLongOpt: Option[Long] = {
    try {
      Option(hexStringToUnsignedLong)
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
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

  def toIntOpt: Option[Int]

  def toIntOr(defaultValue: Int): Int = {
    toIntOpt match {
      case Some(int) =>
        int
      case None =>
        defaultValue
    }
  }

  def toLongOpt: Option[Long]

  def toLongOr(defaultValue: Long): Long = {
    toLongOpt match {
      case Some(long) =>
        long
      case None =>
        defaultValue
    }
  }

  def toShortOpt: Option[Short]

  def toShortOr(defaultValue: Short): Short = {
    toShortOpt match {
      case Some(short) =>
        short
      case None =>
        defaultValue
    }
  }

  def toByteOpt: Option[Byte]

  def toByteOr(defaultValue: Byte): Byte = {
    toByteOpt match {
      case Some(byte) =>
        byte
      case None =>
        defaultValue
    }
  }

  def toFloatOpt: Option[Float]

  def toFloatOr(defaultValue: Float): Float = {
    toFloatOpt match {
      case Some(float) =>
        float
      case None =>
        defaultValue
    }
  }

  def toDoubleOpt: Option[Double]

  def toDoubleOr(defaultValue: Double): Double = {
    toDoubleOpt match {
      case Some(double) =>
        double
      case None =>
        defaultValue
    }
  }

  def toBooleanOpt: Option[Boolean]

  def toBooleanOr(defaultValue: Boolean): Boolean = {
    toBooleanOpt match {
      case Some(boolean) =>
        boolean
      case None =>
        defaultValue
    }
  }
}