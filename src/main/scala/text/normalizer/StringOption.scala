package text.normalizer

/**
  * @author ynupc
  *         Created on 15/10/28
  */
object StringOption {
  def apply(value: String): StringOption = {
    if (value == null || value == "") {
      StringNone
    } else {
      StringSome(value)
    }
  }
}

sealed abstract class StringOption {
  def getOrElse(value: String): String = {
    this match {
      case StringSome(s) => s
      case StringNone => value
    }
  }

  def isEmpty: Boolean = {
    this == StringNone
  }

  def nonEmpty: Boolean = {
    !isEmpty
  }

  def get: String = {
    throw new NoSuchElementException("StringNone.get")
  }

  def map(f: String => String): StringOption =
    if (isEmpty) {
      StringNone
    } else {
      StringSome(f(get))
    }
}

final case class StringSome(value: String) extends StringOption {
  override def get: String = {
    value
  }
}

sealed abstract class StringNoneOption extends StringOption

object StringNone extends StringNoneOption

