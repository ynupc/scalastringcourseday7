package day7

import java.util.Locale

import org.junit.Test
import org.scalatest.junit.AssertionsForJUnit
import text._

/**
  * @author ynupc
  *         Created on 2016/02/19
  */
class Day7TestSuite extends AssertionsForJUnit {

  private val hiraganaChar: Char = 'か'
  private val katakanaChar: Char = 'カ'
  private val alphabetChar: Char = 'C'
  private val symbolChar:   Char = '+'

  private val hiraganaCodePoint: Int = Character.codePointAt(Array[Char](hiraganaChar), 0)
  private val katakanaCodePoint: Int = Character.codePointAt(Array[Char](katakanaChar), 0)
  private val alphabetCodePoint: Int = Character.codePointAt(Array[Char](alphabetChar), 0)
  private val symbolCodePoint:   Int = Character.codePointAt(Array[Char](symbolChar),   0)

  @Test
  def testCharacterClass(): Unit = {
    assert(Character.getName(hiraganaCodePoint) == "HIRAGANA LETTER KA")
    assert(Character.getName(katakanaCodePoint) == "KATAKANA LETTER KA")
    assert(Character.getName(alphabetCodePoint) == "LATIN CAPITAL LETTER C")
    assert(Character.getName(symbolCodePoint)   == "PLUS SIGN")
  }

  @Test
  def testCharacterType(): Unit = {
    //index order
    assert( 0 == Character.UNASSIGNED)
    assert( 1 == Character.UPPERCASE_LETTER)
    assert( 2 == Character.LOWERCASE_LETTER)
    assert( 3 == Character.TITLECASE_LETTER)
    assert( 4 == Character.MODIFIER_LETTER)
    assert( 5 == Character.OTHER_LETTER)
    assert( 6 == Character.NON_SPACING_MARK)
    assert( 7 == Character.ENCLOSING_MARK)
    assert( 8 == Character.COMBINING_SPACING_MARK)
    assert( 9 == Character.DECIMAL_DIGIT_NUMBER)
    assert(10 == Character.LETTER_NUMBER)
    assert(11 == Character.OTHER_NUMBER)
    assert(12 == Character.SPACE_SEPARATOR)
    assert(13 == Character.LINE_SEPARATOR)
    assert(14 == Character.PARAGRAPH_SEPARATOR)
    assert(15 == Character.CONTROL)
    assert(16 == Character.FORMAT)
    //17?
    assert(18 == Character.PRIVATE_USE)
    assert(19 == Character.SURROGATE)
    assert(20 == Character.DASH_PUNCTUATION)
    assert(21 == Character.START_PUNCTUATION)
    assert(22 == Character.END_PUNCTUATION)
    assert(23 == Character.CONNECTOR_PUNCTUATION)
    assert(24 == Character.OTHER_PUNCTUATION)
    assert(25 == Character.MATH_SYMBOL)
    assert(26 == Character.CURRENCY_SYMBOL)
    assert(27 == Character.MODIFIER_SYMBOL)
    assert(28 == Character.OTHER_SYMBOL)
    assert(29 == Character.INITIAL_QUOTE_PUNCTUATION)
    assert(30 == Character.FINAL_QUOTE_PUNCTUATION)

    //alphabetical order
    assert( 8 == Character.COMBINING_SPACING_MARK)
    assert(23 == Character.CONNECTOR_PUNCTUATION)
    assert(15 == Character.CONTROL)
    assert(26 == Character.CURRENCY_SYMBOL)
    assert(20 == Character.DASH_PUNCTUATION)
    assert( 9 == Character.DECIMAL_DIGIT_NUMBER)
    assert( 7 == Character.ENCLOSING_MARK)
    assert(22 == Character.END_PUNCTUATION)
    assert(30 == Character.FINAL_QUOTE_PUNCTUATION)
    assert(16 == Character.FORMAT)
    assert(29 == Character.INITIAL_QUOTE_PUNCTUATION)
    assert(10 == Character.LETTER_NUMBER)
    assert(13 == Character.LINE_SEPARATOR)
    assert( 2 == Character.LOWERCASE_LETTER)
    assert(25 == Character.MATH_SYMBOL)
    assert( 4 == Character.MODIFIER_LETTER)
    assert(27 == Character.MODIFIER_SYMBOL)
    assert( 6 == Character.NON_SPACING_MARK)
    assert( 5 == Character.OTHER_LETTER)
    assert(11 == Character.OTHER_NUMBER)
    assert(24 == Character.OTHER_PUNCTUATION)
    assert(28 == Character.OTHER_SYMBOL)
    assert(14 == Character.PARAGRAPH_SEPARATOR)
    assert(18 == Character.PRIVATE_USE)
    assert(12 == Character.SPACE_SEPARATOR)
    assert(21 == Character.START_PUNCTUATION)
    assert(19 == Character.SURROGATE)
    assert( 3 == Character.TITLECASE_LETTER)
    assert( 0 == Character.UNASSIGNED)
    assert( 1 == Character.UPPERCASE_LETTER)

    //Char
    assert(Character.getType(hiraganaChar) == Character.OTHER_LETTER)
    assert(Character.getType(katakanaChar) == Character.OTHER_LETTER)
    assert(Character.getType(alphabetChar) == Character.UPPERCASE_LETTER)
    assert(Character.getType(symbolChar)   == Character.MATH_SYMBOL)

    //コードポイント
    assert(Character.getType(hiraganaCodePoint) == Character.OTHER_LETTER)
    assert(Character.getType(katakanaCodePoint) == Character.OTHER_LETTER)
    assert(Character.getType(alphabetCodePoint) == Character.UPPERCASE_LETTER)
    assert(Character.getType(symbolCodePoint)   == Character.MATH_SYMBOL)
  }

  @Test
  def testCharacterDirectionality(): Unit = {
    //index order
    assert((-1: Byte) == Character.DIRECTIONALITY_UNDEFINED)
    assert(( 0: Byte) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert(( 1: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT)
    assert(( 2: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC)
    assert(( 3: Byte) == Character.DIRECTIONALITY_EUROPEAN_NUMBER)
    assert(( 4: Byte) == Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR)
    assert(( 5: Byte) == Character.DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR)
    assert(( 6: Byte) == Character.DIRECTIONALITY_ARABIC_NUMBER)
    assert(( 7: Byte) == Character.DIRECTIONALITY_COMMON_NUMBER_SEPARATOR)
    assert(( 8: Byte) == Character.DIRECTIONALITY_NONSPACING_MARK)
    assert(( 9: Byte) == Character.DIRECTIONALITY_BOUNDARY_NEUTRAL)
    assert((10: Byte) == Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR)
    assert((11: Byte) == Character.DIRECTIONALITY_SEGMENT_SEPARATOR)
    assert((12: Byte) == Character.DIRECTIONALITY_WHITESPACE)
    assert((13: Byte) == Character.DIRECTIONALITY_OTHER_NEUTRALS)
    assert((14: Byte) == Character.DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING)
    assert((15: Byte) == Character.DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE)
    assert((16: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING)
    assert((17: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE)
    assert((18: Byte) == Character.DIRECTIONALITY_POP_DIRECTIONAL_FORMAT)

    //alphabetical order
    assert(( 6: Byte) == Character.DIRECTIONALITY_ARABIC_NUMBER)
    assert(( 9: Byte) == Character.DIRECTIONALITY_BOUNDARY_NEUTRAL)
    assert(( 7: Byte) == Character.DIRECTIONALITY_COMMON_NUMBER_SEPARATOR)
    assert(( 3: Byte) == Character.DIRECTIONALITY_EUROPEAN_NUMBER)
    assert(( 4: Byte) == Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR)
    assert(( 5: Byte) == Character.DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR)
    assert(( 0: Byte) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert((14: Byte) == Character.DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING)
    assert((15: Byte) == Character.DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE)
    assert(( 8: Byte) == Character.DIRECTIONALITY_NONSPACING_MARK)
    assert((13: Byte) == Character.DIRECTIONALITY_OTHER_NEUTRALS)
    assert((10: Byte) == Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR)
    assert((18: Byte) == Character.DIRECTIONALITY_POP_DIRECTIONAL_FORMAT)
    assert(( 1: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT)
    assert(( 2: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC)
    assert((16: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING)
    assert((17: Byte) == Character.DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE)
    assert((11: Byte) == Character.DIRECTIONALITY_SEGMENT_SEPARATOR)
    assert((-1: Byte) == Character.DIRECTIONALITY_UNDEFINED)
    assert((12: Byte) == Character.DIRECTIONALITY_WHITESPACE)

    //Char
    assert(Character.getDirectionality(hiraganaChar) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert(Character.getDirectionality(katakanaChar) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert(Character.getDirectionality(alphabetChar) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert(Character.getDirectionality(symbolChar)   == Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR)

    //コードポイント
    assert(Character.getDirectionality(hiraganaCodePoint) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert(Character.getDirectionality(katakanaCodePoint) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert(Character.getDirectionality(alphabetCodePoint) == Character.DIRECTIONALITY_LEFT_TO_RIGHT)
    assert(Character.getDirectionality(symbolCodePoint)   == Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR)
  }

  private val nullPoGa: NormalizedString = NormalizedString(StringOption("「ぬるぽ」「ガッ」"))

  @Test
  def testKatakana2Hiragana(): Unit = {
    assert(JapaneseCharacterCaseConverter.convertKatakana2Hiragana(nullPoGa).toString == "「ぬるぽ」「がっ」")
  }

  @Test
  def testHiragana2Katakana(): Unit = {
    assert(JapaneseCharacterCaseConverter.convertHiragana2Katakana(nullPoGa).toString == "「ヌルポ」「ガッ」")
  }

  private val upperCaseChar: Char = '\u01C7'//「Ǉ」
  private val titleCaseChar: Char = '\u01C8'//「ǈ」
  private val lowerCaseChar: Char = '\u01C9'//「ǉ」

  private val upperCaseCodePoint: Int = Character.codePointAt(Array[Char](upperCaseChar), 0)
  private val titleCaseCodePoint: Int = Character.codePointAt(Array[Char](titleCaseChar), 0)
  private val lowerCaseCodePoint: Int = Character.codePointAt(Array[Char](lowerCaseChar), 0)

  @Test
  def testConverseLetterCaseOfCharacterWithChar(): Unit = {
    assert(Character.toUpperCase(upperCaseChar) == upperCaseChar)
    assert(Character.toUpperCase(titleCaseChar) == upperCaseChar)
    assert(Character.toUpperCase(lowerCaseChar) == upperCaseChar)

    assert(Character.toTitleCase(upperCaseChar) == titleCaseChar)
    assert(Character.toTitleCase(titleCaseChar) == titleCaseChar)
    assert(Character.toTitleCase(lowerCaseChar) == titleCaseChar)

    assert(Character.toLowerCase(upperCaseChar) == lowerCaseChar)
    assert(Character.toLowerCase(titleCaseChar) == lowerCaseChar)
    assert(Character.toLowerCase(lowerCaseChar) == lowerCaseChar)
  }

  @Test
  def testConverseLetterCaseOfCharacterWithCodePoint(): Unit = {
    assert(Character.toUpperCase(upperCaseCodePoint) == upperCaseChar)
    assert(Character.toUpperCase(titleCaseCodePoint) == upperCaseChar)
    assert(Character.toUpperCase(lowerCaseCodePoint) == upperCaseChar)

    assert(Character.toTitleCase(upperCaseCodePoint) == titleCaseChar)
    assert(Character.toTitleCase(titleCaseCodePoint) == titleCaseChar)
    assert(Character.toTitleCase(lowerCaseCodePoint) == titleCaseChar)

    assert(Character.toLowerCase(upperCaseCodePoint) == lowerCaseChar)
    assert(Character.toLowerCase(titleCaseCodePoint) == lowerCaseChar)
    assert(Character.toLowerCase(lowerCaseCodePoint) == lowerCaseChar)
  }

  private val locale: Locale = Locale.JAPAN

  @Test
  def testConverseLetterCaseOfString(): Unit = {
    val letterCase: String = "letter Case string"

    assert(letterCase.toUpperCase         == "LETTER CASE STRING")
    assert(letterCase.toUpperCase(locale) == "LETTER CASE STRING")

    assert(letterCase.toLowerCase         == "letter case string")
    assert(letterCase.toLowerCase(locale) == "letter case string")

    //文字列の１文字目を大文字にする
    assert(letterCase.capitalize == "Letter Case string")
  }

  private val wordVariants: String = "スパゲッティ,スパゲッティー,スパゲッテイ,スパゲティ,スパゲティー,スパゲテイ"
  private val nullString: String = null
  private val emptyString: String = ""

  @Test
  def testOption(): Unit = {
    assert(Option(wordVariants).nonEmpty)
    assert(Option(nullString).isEmpty)
    assert(Option(emptyString).nonEmpty)

    Option(wordVariants) match {
      case Some(str) =>
        assert(str == wordVariants)
      case None =>
        assert(false)
    }

    Option(emptyString) match {
      case Some(str) =>
        assert(str == emptyString)
      case None =>
        assert(false)
    }

    val wordVariantsOpt: Option[String] = Option(wordVariants)
    if (wordVariantsOpt.nonEmpty) {
      assert(wordVariantsOpt.get == wordVariants)
    }

    val nullStringOpt: Option[String] = Option(nullString)
    assert(nullStringOpt.getOrElse("ゲッティ") == "ゲッティ")
  }

  @Test
  def testStringOption(): Unit = {
    assert(StringOption(wordVariants).nonEmpty)
    assert(StringOption(nullString).isEmpty)
    assert(StringOption(emptyString).isEmpty)

    StringOption(wordVariants) match {
      case StringSome(str) =>
        assert(str == wordVariants)
      case StringNone =>
        assert(false)
    }

    StringOption(emptyString) match {
      case StringSome(str) =>
        assert(false)
      case StringNone =>
        assert(true)
    }
  }

  //\u30AC = 全角カタカナの「ガ」
  private val sOfU30AC = "\u30AC"
  //\u30AB = 全角カタカナの「カ」
  private val sOfU30AB = "\u30AB"
  //\uFF76 = 半角カタカナの「ｶ」
  private val sOfUFF76 = "\uFF76"
  //\u30F5 = 小書の全角カタカナの「ヵ」
  private val sOfU30F5 = "\u30F5"
  //\u32D5 = 丸囲いカタカナの「㋕」
  private val sOfU32D5 = "\u32D5"
  //\u309B = 全角濁点の「゙」
  private val sOfU309B = "\u309B"
  //\u3099 = 全角濁点の「゛」
  private val sOfU3099 = "\u3099"
  //\uFF9E = 半角濁点の「ﾞ」
  private val sOfUFF9E = "\uFF9E"
  @Test
  def testNormalizedStringOfCompatibilityEquivalent(): Unit = {
    //カ
    val nsOfU30AB: NormalizedString = NormalizedString(StringOption(sOfU30AB))
    val nsOfUFF76: NormalizedString = NormalizedString(StringOption(sOfUFF76))
    val nsOfU30F5: NormalizedString = NormalizedString(StringOption(sOfU30F5))
    val nsOfU32D5: NormalizedString = NormalizedString(StringOption(sOfU32D5))
    //濁点
    val nsOfU309B: NormalizedString = NormalizedString(StringOption(sOfU309B))
    val nsOfU3099: NormalizedString = NormalizedString(StringOption(sOfU3099))
    val nsOfUFF9E: NormalizedString = NormalizedString(StringOption(sOfUFF9E))

    assert(nsOfU30AB.toString == sOfU30AB)
    assert(nsOfU30AB == nsOfUFF76)
    assert(nsOfU30AB != nsOfU30F5)
    assert(nsOfU30AB == nsOfU32D5)

    assert(nsOfU3099.toString == sOfU3099)
    assert(nsOfU3099 == nsOfUFF9E)
    assert(nsOfU3099 != nsOfU309B)
    assert(nsOfUFF9E != nsOfU309B)
  }

  @Test
  def testNormalizedStringOfCanonicalEquivalent(): Unit = {
    val nsOfU30AC: NormalizedString = NormalizedString(StringOption(sOfU30AC))

    val nsOfU30ABU309B: NormalizedString = NormalizedString(StringOption(sOfU30AB concat sOfU309B))
    val nsOfU30ABU3099: NormalizedString = NormalizedString(StringOption(sOfU30AB concat sOfU3099))
    val nsOfU30ABUFF9E: NormalizedString = NormalizedString(StringOption(sOfU30AB concat sOfUFF9E))

    val nsOfUFF76U309B: NormalizedString = NormalizedString(StringOption(sOfUFF76 concat sOfU309B))
    val nsOfUFF76U3099: NormalizedString = NormalizedString(StringOption(sOfUFF76 concat sOfU3099))
    val nsOfUFF76UFF9E: NormalizedString = NormalizedString(StringOption(sOfUFF76 concat sOfUFF9E))

    val nsOfU30F5U309B: NormalizedString = NormalizedString(StringOption(sOfU30F5 concat sOfU309B))
    val nsOfU30F5U3099: NormalizedString = NormalizedString(StringOption(sOfU30F5 concat sOfU3099))
    val nsOfU30F5UFF9E: NormalizedString = NormalizedString(StringOption(sOfU30F5 concat sOfUFF9E))

    val nsOfU32D5U309B: NormalizedString = NormalizedString(StringOption(sOfU32D5 concat sOfU309B))
    val nsOfU32D5U3099: NormalizedString = NormalizedString(StringOption(sOfU32D5 concat sOfU3099))
    val nsOfU32D5UFF9E: NormalizedString = NormalizedString(StringOption(sOfU32D5 concat sOfUFF9E))

    assert(nsOfU30AC.toString == sOfU30AC)

    assert(nsOfU30AC != nsOfU30ABU309B)
    assert(nsOfU30AC == nsOfU30ABU3099)
    assert(nsOfU30AC == nsOfU30ABUFF9E)

    assert(nsOfU30AC != nsOfUFF76U309B)
    assert(nsOfU30AC == nsOfUFF76U3099)
    assert(nsOfU30AC == nsOfUFF76UFF9E)

    assert(nsOfU30AC != nsOfU30F5U309B)
    assert(nsOfU30AC != nsOfU30F5U3099)
    assert(nsOfU30AC != nsOfU30F5UFF9E)

    assert(nsOfU30AC != nsOfU32D5U309B)
    assert(nsOfU30AC == nsOfU32D5U3099)
    assert(nsOfU30AC == nsOfU32D5UFF9E)
  }

  @Test
  def testNormalizedString(): Unit = {
    NormalizedString(StringOption(wordVariants)).toStringOption match {
      case StringSome(str) =>
        assert(str == "スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ")
      case StringNone =>
        assert(false)
    }
  }

  @Test
  def testNormalizedSentence(): Unit = {
    val text: String = """2005年にカンザス州教育委員会では、公教育において進化論と同様にインテリジェント・デザイン（ID説）の立場も教えなければいけないという決議が評決されることになっていた。前年の教育委員の改選で委員6人中4人を保守派が占めており、可決は確実と見られていた。これに抗議するために、2005年6月、アメリカ合衆国のオレゴン州立大学物理学科卒業生のボビー・ヘンダーソンは公開質問状を提出した。ヘンダーソンは自分のサイト "venganza.org" （スペイン語で復讐の意）において空飛ぶスパゲッティー・モンスターの概略を示して、明らかな証拠や、それに基づいて進化を説明できる十分な論理性・整合性があると論じ、創造論の一部として「空飛ぶスパゲティ・モンスター」を進化論やID説と同様に公立高校で教えることを公開質問状において提案した。"""
    val sentences = SentenceParser.parse(StringOption(text))
    //sentencesの型はSeq[NormalizedSentence]だが、NormalizedSentenceの型にはここからアクセスできないため、型を明示的に書けない。

    assert(sentences.length == 4)
    assert(sentences.head.text == """2005年にカンザス州教育委員会では、公教育において進化論と同様にインテリジェントデザイン(ID説)の立場も教えなければいけないという決議が評決されることになっていた。""")
    assert(sentences(1).text   == """前年の教育委員の改選で委員6人中4人を保守派が占めており、可決は確実と見られていた。""")
    assert(sentences(2).text   == """これに抗議するために、2005年6月、アメリカ合衆国のオレゴン州立大学物理学科卒業生のボビーヘンダーソンは公開質問状を提出した。""")
    assert(sentences.last.text == """ヘンダーソンは自分のサイト "venganza.org" (スペイン語で復讐の意)において空飛ぶスパゲッティモンスターの概略を示して、明らかな証拠や、それに基づいて進化を説明できる十分な論理性整合性があると論じ、創造論の一部として「空飛ぶスパゲッティモンスター」を進化論やID説と同様に公立高校で教えることを公開質問状において提案した。""")
  }
}
