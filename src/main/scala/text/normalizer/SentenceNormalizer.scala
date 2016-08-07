package text.normalizer

/**
  * @author ynupc
  *         Created on 2016/08/06
  */
object SentenceNormalizer {
  def normalize(str: StringOption): StringOption = {
    SentenceEndingNormalizer.normalize(
      SentenceBeginningNormalizer.normalize(str))
  }
}
