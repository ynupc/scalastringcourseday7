# 2.　文字の正規化とオプション
<h3>2.1　オプション</h3>
Optionは値があるのかないのかどうかわからない状態を表すものです。値をOptionで包むことで、その値がある（nullではない）場合はSome、ない（nullである）の場合はNoneという状態に移り、Someの場合は値を取り出せるという機構です。nullを記述せずにNullPointerExceptionを排除するために使えます。Optionで包まれた値を取り出す方法は主に３つあります。
<ol>
  <li>match caseを利用する方法（サンプルコード）</li>
  <li>isEmpty/nonEmptyでNoneを排除後に、getで値を取り出す方法</li>
  <li>getOrElseで取り出せなかった時（elseの時）に返すデフォルト値を事前に用意してから値を取り出す方法</li>
</ol>
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
***
<h3>2.2　文字列オプション（自作）</h3>
Optionはnullを書かずにNullPointerExceptionを排除するために使用できます。文字列処理においてStringはnullだけでなく空文字""も同時に排除したい場合がよくありますが、Optionでは空文字は排除されません。そこで、OptionのようにStringを包むことでnullと空文字を排除するためのStringOptionを自作しました。<a href="https://github.com/ynupc/scalastringcourseday7/blob/master/src/test/scala/text/StringOption.scala" target="_blank">StringOptionの実装</a>。
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
***
<h3>2.3　正規化文字列（自作）</h3>
値がnullや空文字ではなく正規化されていることを保証することができます。

NormalizedStringの正規化処理の流れ：
<ol>
  <li>Unicode正規化前の辞書による文字の正規化</li>
  <li>Unicode正規化</li>
  <li>Unicode正規化後の辞書による文字の正規化</li>
  <li>辞書による単語の異表記からの代表表記への置換</li>
</ol>
NormalizedStringを構成するファイル：
<ul>
  <li>辞書
    <ul>
      <li>character_dic_after_unicode_normalization.yml<br>「Unicode正規化後の辞書による文字の正規化」で使用する辞書</li>
      <li>character_dic_before_unicode_normalization.yml<br>「Unicode正規化前の辞書による文字の正規化」で使用する辞書</li>
      <li>word_expression_dic.yml<br>「辞書による単語の異表記からの代表表記への置換」で使用する辞書</li>
    </ul>
  </li>
  <li>プログラム
    <ul>
      <li>CharacterNormalizerAfterUnicodeNormalization.scala<br>「Unicode正規化後の辞書による文字の正規化」で使用するプログラム</li>
      <li>CharacterNormalizerBeforeUnicodeNormalization.scala<br>「Unicode正規化前の辞書による文字の正規化」で使用するプログラム</li>
      <li>DictionaryBasedNormalizer.scala<br>DictionaryBasedNormalizerは、CharacterNormalizerBeforeUnicodeNormalization、CharacterNormalizerAfterUnicodeNormalization、WordExpressionNormalizerが継承するクラス</li>
      <li>NormalizedString.scala<br>NormalizedStringの本体。「NormalizedStringの正規化処理の流れ」に従い、CharacterNormalizerBeforeUnicodeNormalization、java.text.Normalizer、CharacterNormalizerAfterUnicodeNormalization、WordExpressionNormalizerを順に実行する。</li>
      <li>WordExpressionNormalizer.scala<br>「辞書による単語の異表記からの代表表記への置換」で使用するプログラム</li>
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
 辞書の解説
 基本的なフォーマットについて、１行ごとに代表表記と異表記リストの紐付けている。
 Unicodeシーケンスが使える。フォントの見た目が似ている文字を人間が識別するため。
 コメントアウトの書き方
 word_expression_dic.ymlの単語異表記は正規表現による記述が可能
 変換順序について
 このやり方で異表記を代表表記に統一する場合の問題点について
<a href="https://github.com/ynupc/scalastringcourseday7/blob/master/src/test/scala/text/NormalizedString.scala" target="_blank">NormalizedStringの実装</a><br>
互換等価性に関するサンプルコード
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
```
正準等価性に関するサンプルコード
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
文字列の正規化に関するサンプルコード（単語異表記を代表表記に置換）
```scala
  private val wordVariants: String = "スパゲッティ,スパゲッティー,スパゲッテイ,スパゲティ,スパゲティー,スパゲテイ"
  
  @Test
  def testNormalizedString(): Unit = {
    NormalizedString(StringOption(wordVariants)).toStringOption match {
      case StringSome(str) =>
        assert(str == "スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ,スパゲッティ")
      case StringNone =>
        assert(false)
    }
  }
```
***
<h3>2.4　句点による文分割と文の正規化（自作）</h3>
文の定義
単文・重文・複文ではなく句点区切りによる文
句点・読点がUnicode正規化ではピリオド・カンマに変換されてしまう。
その問題点を具体的に挙げる
日本語のテキストを入力すると、句点・読点をピリオド・カンマに変換せずに文字の正規化が保証された文を持つクラスのシーケンスを返すSentenceParser.parseメソッドの解説。
<a href="https://github.com/ynupc/scalastringcourseday7/blob/master/src/test/scala/text/SentenceParser.scala" target="_blank">SentenceParserの実装</a>
```scala
  @Test
  def testNormalizedSentence(): Unit = {
    val text: String = """2005年にカンザス州教育委員会では、公教育において進化論と同様にインテリジェント・デザイン（ID説）の立場も教えなければいけないという決議が評決されることになっていた。前年の教育委員の改選で委員6人中4人を保守派が占めており、可決は確実と見られていた。これに抗議するために、2005年6月、アメリカ合衆国のオレゴン州立大学物理学科卒業生のボビー・ヘンダーソンは公開質問状を提出した。ヘンダーソンは自分のサイト "venganza.org" （スペイン語で復讐の意）において空飛ぶスパゲッティー・モンスターの概略を示して、明らかな証拠や、それに基づいて進化を説明できる十分な論理性・整合性があると論じ、創造論の一部として「空飛ぶスパゲティ・モンスター」を進化論やID説と同様に公立高校で教えることを公開質問状において提案した。"""
    val sentences: Seq[NormalizedSentence] = SentenceParser.parse(StringOption(text))

    assert(sentences.length == 4)
    assert(sentences.head.text == """2005年にカンザス州教育委員会では、公教育において進化論と同様にインテリジェントデザイン(ID説)の立場も教えなければいけないという決議が評決されることになっていた。""")
    assert(sentences(1).text   == """前年の教育委員の改選で委員6人中4人を保守派が占めており、可決は確実と見られていた。""")
    assert(sentences(2).text   == """これに抗議するために、2005年6月、アメリカ合衆国のオレゴン州立大学物理学科卒業生のボビーヘンダーソンは公開質問状を提出した。""")
    assert(sentences.last.text == """ヘンダーソンは自分のサイト "venganza.org" (スペイン語で復讐の意)において空飛ぶスパゲッティモンスターの概略を示して、明らかな証拠や、それに基づいて進化を説明できる十分な論理性整合性があると論じ、創造論の一部として「空飛ぶスパゲッティモンスター」を進化論やID説と同様に公立高校で教えることを公開質問状において提案した。""")
  }
```
