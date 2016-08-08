package util

/**
  * @author ynupc
  *         Created on 2016/08/08
  */
object LongUtils {
  implicit def longToLongUtils(repr: Long): LongUtils = {
    new LongUtils(repr)
  }
}

class LongUtils(repr: Long) {
  def toHexString: String = {
    java.lang.Long.toHexString(repr)
  }

  def toOctalString: String = {
    java.lang.Long.toOctalString(repr)
  }

  def toBinaryString: String = {
    java.lang.Long.toBinaryString(repr)
  }
}