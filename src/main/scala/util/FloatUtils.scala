package util

/**
  * @author ynupc
  *         Created on 2016/08/08
  */
object FloatUtils {
  implicit def floatToFloatUtils(repr: Float): FloatUtils = {
    new FloatUtils(repr)
  }
}

class FloatUtils(repr: Float) {
  def toHexString: String = {
    java.lang.Float.toHexString(repr)
  }
}