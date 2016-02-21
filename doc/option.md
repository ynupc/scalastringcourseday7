# 2.　オプション
<h3>2.1　オプション</h3>
```scala
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
  }
```
<h3>2.2　文字列オプション（自作）</h3>
<a href="https://github.com/ynupc/scalastringcourseday7/blob/master/src/test/scala/text/StringOption.scala" target="_blank">StringOptionの実装</a>
```scala
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
```
<h3>2.3　正規化文字列（自作）</h3>
NormalizedStringの正規化処理の流れ：
<ol>
  <li>Unicode正規化前の辞書による文字の正規化</li>
  <li>Unicode正規化</li>
  <li>Unicode正規化後の辞書による文字の正規化</li>
  <li>辞書による単語の異表記からの代表表記への置換</li>
</ol>
NormalizedStringを構成するファイル：
<ul>
  <li>辞書ファイル
    <ul>
      <li>character_dic_after_unicode_normalization.yml</li>
      <li>character_dic_before_unicode_normalization.yml</li>
      <li>word_expression_dic.yml</li>
    </ul>
  </li>
  <li>プログラム
    <ul>
      <li>CharacterNormalizerAfterUnicodeNormalization.scala</li>
      <li>CharacterNormalizerBeforeUnicodeNormalization.scala</li>
      <li>DictionaryBasedNormalizer.scala</li>
      <li>NormalizedString.scala</li>
      <li>WordExpressionNormalizer.scala</li>
    </ul>
  </li>
</ul>
```
scalastringcourseday7/
 ├ src/
 │　└ test/
 │     ├ resources/
 │　　　│    ├ character_dic_after_unicode_normalization.yml
 │　　　│    ├ character_dic_before_unicode_normalization.yml
 │　　　│    └ word_expression_dic.yml
 │　　　└ scala/
 │        ├ text/
 │　　　　　│    ├ CharacterNormalizerAfterUnicodeNormalization.scala
 │　　　　　│    ├ CharacterNormalizerBeforeUnicodeNormalization.scala
 │　　　　　│    ├ DictionaryBasedNormalizer.scala
 │　　　　　│    ├ NormalizedString.scala
 │　　　　　│    ├ WordExpressionNormalizer.scala
 │　　　　　│    └ …
 │　　　　　└ …
 …
 ```
<a href="https://github.com/ynupc/scalastringcourseday7/blob/master/src/test/scala/text/NormalizedString.scala" target="_blank">NormalizedStringの実装</a>
```scala
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
```
<h3>2.4　正規化文字列オプション（自作）</h3>
<a href="https://github.com/ynupc/scalastringcourseday7/blob/master/src/test/scala/text/NormalizedStringOption.scala" target="_blank">NormalizedStringOptionの実装</a>
```scala
  private val wordVariants: String = "スパゲッティ,スパゲッティー,スパゲッテイ,スパゲティ,スパゲティー,スパゲテイ"
  private val nullString: String = null
  private val emptyString: String = ""
  
  @Test
  def testNormalizedStringOption(): Unit = {
    assert(NormalizedStringOption(NormalizedString(StringOption(wordVariants))).nonEmpty)
    assert(NormalizedStringOption(NormalizedString(StringOption(nullString))).isEmpty)
    assert(NormalizedStringOption(NormalizedString(StringOption(emptyString))).isEmpty)

    text.NormalizedStringOption(NormalizedString(StringOption(wordVariants))) match {
      case NormalizedStringSome(ns) =>
        ns.toStringOption match {
          case StringSome(str) =>
            assert(str == "スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ")
          case StringNone =>
            assert(false)
        }
      case NormalizedStringNone =>
        assert(false)
    }
  }
```
<h3>2.5　句点による文分割と正規化文字列オプション（自作）</h3>
