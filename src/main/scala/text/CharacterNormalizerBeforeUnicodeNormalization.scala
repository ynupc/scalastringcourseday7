package text

/**
  * @author ynupc
  *         Created on 2016/02/20
  */
object CharacterNormalizerBeforeUnicodeNormalization
  extends DictionaryBasedNormalizer(
    StringOption("character_dic_before_unicode_normalization.yml"))
