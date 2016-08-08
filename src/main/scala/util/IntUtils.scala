package util

/**
  * @author ynupc
  *         Created on 2016/08/08
  */
object IntUtils {
  implicit def intToIntUtils(repr: Int): IntUtils = {
    new IntUtils(repr)
  }
}

class IntUtils(repr: Int) {
  def toHexString: String = {
    java.lang.Integer.toHexString(repr)
  }

  def toOctalString: String = {
    java.lang.Integer.toOctalString(repr)
  }

  def toBinaryString: String = {
    java.lang.Integer.toBinaryString(repr)
  }
}
