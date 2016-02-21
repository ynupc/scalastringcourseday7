package text

/**
  * @author ynupc
  *         Created on 2016/02/22
  */
object JapaneseCharacterCaseConverter {
  class Conversion(val begin: Int, val end: Int, val d: Int)

  private val FROM_KATAKANA_TO_HIRAGANA: Seq[Conversion] = Seq[Conversion](
    new Conversion('\u30A1', '\u30F6', '\u3041'),//'ァ', 'ヶ', 'ぁ'
    new Conversion('\u30FD', '\u30FE', '\u309D') //'ヽ', 'ヾ', 'ゝ'
  )

  private val FROM_HIRAGANA_TO_KATAKANA: Seq[Conversion] = Seq[Conversion](
    new Conversion('\u3041', '\u3096', '\u30A1'),//'ぁ', 'ゖ', 'ァ'
    new Conversion('\u309D', '\u309E', '\u30FD') //'ゝ', 'ゞ', 'ヽ'
  )

  private def convert(normalizedStringOpt: NormalizedStringOption, conversion: Conversion): NormalizedStringOption = {
    normalizedStringOpt map {
      normalizedString =>
        NormalizedString(convert(normalizedString.toStringOption, conversion))
    }
  }

  private def convert(textOpt: StringOption, conversion: Conversion): StringOption = {
    val diff: Int = conversion.d - conversion.begin
    textOpt map {
      text =>
        new String(
          text.toCharArray map {
            case char if (conversion.begin to conversion.end).contains(char) =>
              (char + diff).toChar
            case otherwise =>
              otherwise
          }
        )
    }
  }

  def convertKatakana2Hiragana(textOpt: NormalizedStringOption): NormalizedStringOption = {
    var ret: NormalizedStringOption = textOpt
    FROM_KATAKANA_TO_HIRAGANA foreach {
      conversion =>
        ret = convert(ret, conversion)
    }
    ret
  }

  def convertHiragana2Katakana(textOpt: NormalizedStringOption): NormalizedStringOption = {
    var ret: NormalizedStringOption = textOpt
    FROM_HIRAGANA_TO_KATAKANA foreach {
      conversion =>
        ret = convert(ret, conversion)
    }
    ret
  }
}
