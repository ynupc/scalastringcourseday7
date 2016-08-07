package text.normalizer

/**
  * @author ynupc
  *         Created on 2016/08/06
  */
object SentenceBeginningNormalizer
  extends DictionaryBasedNormalizer(
    StringOption("sentence_beginning_normalization.yml"))
