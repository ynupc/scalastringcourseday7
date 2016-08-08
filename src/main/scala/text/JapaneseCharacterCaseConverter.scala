package text

import text.normalizer.NormalizedString

/**
  * @author ynupc
  *         Created on 2016/02/22
  */
object JapaneseCharacterCaseConverter {
  private class Conversion(val begin: Int, val end: Int, val target: Int)

  private final val KATAKANA: Seq[(Char, Char)] = Seq[(Char, Char)](
    ('\u30A1', '\u30F6'),//'ァ', 'ヶ'
    ('\u30FD', '\u30FE') //'ヽ', 'ヾ'
  )

  private final val HIRAGANA: Seq[(Char, Char)] = Seq[(Char, Char)](
    ('\u3041', '\u3096'),//'ぁ', 'ゖ'
    ('\u309D', '\u309E') //'ゝ', 'ゞ'
  )

  private final val FROM_KATAKANA_TO_HIRAGANA: Seq[Conversion] = {
    for (i <- KATAKANA.indices) yield {
      new Conversion(KATAKANA(i)._1, KATAKANA(i)._2, HIRAGANA(i)._1)
    }
  }

  private final val FROM_HIRAGANA_TO_KATAKANA: Seq[Conversion] = {
    for (i <- HIRAGANA.indices) yield {
      new Conversion(HIRAGANA(i)._1, HIRAGANA(i)._2, KATAKANA(i)._1)
    }
  }

  private def convert(normalizedString: NormalizedString, conversion: Conversion): NormalizedString = {
    NormalizedString(convert(normalizedString.toStringOption, conversion))
  }

  private def convert(textOpt: StringOption, conversion: Conversion): StringOption = {
    val diff: Int = conversion.target - conversion.begin
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

  def convertKatakana2Hiragana(text: NormalizedString): NormalizedString = {
    var ret: NormalizedString = text
    FROM_KATAKANA_TO_HIRAGANA foreach {
      conversion =>
        ret = convert(ret, conversion)
    }
    ret
  }

  def convertHiragana2Katakana(text: NormalizedString): NormalizedString = {
    var ret: NormalizedString = text
    FROM_HIRAGANA_TO_KATAKANA foreach {
      conversion =>
        ret = convert(ret, conversion)
    }
    ret
  }
}
