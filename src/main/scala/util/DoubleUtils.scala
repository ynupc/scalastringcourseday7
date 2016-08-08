package util

/**
  * @author ynupc
  *         Created on 2016/08/08
  */
object DoubleUtils {
  implicit def doubleToDoubleUtils(repr: Double): DoubleUtils = {
    new DoubleUtils(repr)
  }
}

class DoubleUtils(repr: Double) {
  def toHexString: String = {
    java.lang.Double.toHexString(repr)
  }
}