package text.normalizer

import text.StringOption

/**
  * @author ynupc
  *         Created on 2016/08/06
  */
object SentenceEndingNormalizer
  extends DictionaryBasedNormalizer(
    StringOption("sentence_ending_normalization.yml"))
