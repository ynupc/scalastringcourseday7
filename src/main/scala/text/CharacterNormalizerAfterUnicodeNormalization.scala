package text

/**
  * @author ynupc
  *         Created on 2016/02/20
  */
object CharacterNormalizerAfterUnicodeNormalization
  extends DictionaryBasedNormalizer(
    StringOption("normalizer/character_dic_after_unicode_normalization.yml"))
