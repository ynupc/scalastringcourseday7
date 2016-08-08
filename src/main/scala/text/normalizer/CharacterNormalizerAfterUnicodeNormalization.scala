package text.normalizer

import text.StringOption

/**
  * @author ynupc
  *         Created on 2016/02/20
  */
object CharacterNormalizerAfterUnicodeNormalization
  extends DictionaryBasedNormalizer(
    StringOption("character_dic_after_unicode_normalization.yml"))
