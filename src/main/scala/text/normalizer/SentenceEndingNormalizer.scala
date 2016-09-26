package text.normalizer

import text.StringOption

/**
  * @author ynupc
  *         Created on 2016/08/06
  */
object SentenceEndingNormalizer
  extends DictionaryBasedNormalizer(
    StringOption("sentence_ending_normalization.yml")) {
  override def replaceAll(input: String, term: String, replacement: String): String = {
    //regex is available
    input.replaceFirst(term concat "$", replacement)
  }
}
