package text

/**
  * @author ynupc
  *         Created on 15/10/28
  */
object NormalizedStringOption {
  def apply(text: NormalizedString): NormalizedStringOption = {
    StringOption(text.toString) match {
      case StringSome(t) =>
        NormalizedStringSome(text)
      case StringNone =>
        NormalizedStringNone
    }
  }
}

sealed abstract class NormalizedStringOption {
  def getOrElse(value: NormalizedString): NormalizedString = {
    this match {
      case NormalizedStringSome(s) => s
      case NormalizedStringNone => value
    }
  }

  def isEmpty: Boolean = {
    this == NormalizedStringNone
  }

  def nonEmpty: Boolean = {
    !isEmpty
  }

  def get: NormalizedString = {
    throw new NoSuchElementException("NormalizedStringNone.get")
  }

  def map(f: NormalizedString => NormalizedString): NormalizedStringOption =
    if (isEmpty) {
      NormalizedStringNone
    } else {
      NormalizedStringSome(f(get))
    }
}

final case class NormalizedStringSome(value: NormalizedString) extends NormalizedStringOption {
  override def get: NormalizedString = {
    value
  }
}

sealed abstract class NormalizedStringNoneOption extends NormalizedStringOption

object NormalizedStringNone extends NormalizedStringNoneOption
