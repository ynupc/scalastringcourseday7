# 1.　文字の正規化
<h3>1.1　正準等価性・互換等価性</h3>
<h4>1.1.1　正準等価性</h4>
ダイアクリティカルマークを合成済みの文字「が」＝文字「か」＋合成用ダイアクリティカルマーク「゛」<br>
<ul>
  <li>左辺から右辺への変換＝「分解」（1対1なので、変換可能）</li>
  <li>右辺から左辺への変換＝「合成」（1対1なので、変換可能）</li>
</ul>
例：<br>
<a href="https://ja.wikipedia.org/wiki/%E3%83%80%E3%82%A4%E3%82%A2%E3%82%AF%E3%83%AA%E3%83%86%E3%82%A3%E3%82%AB%E3%83%AB%E3%83%9E%E3%83%BC%E3%82%AF" target="_blank">ダイアクリティカルマーク</a>
***
<h4>1.1.2　互換等価性</h4>
上付き文字「¹」＝普通の文字「1」<br>
下付き文字「₁」＝普通の文字「1」<br>
半角カナ文字「ｱ」＝全角カナ文字「ア」<br>
全角英字「Ｃ」＝半角英字「C」<br>
全角数字「１」＝半角数字「1」<br>
etc.<br>
<ul>
  <li>左辺から右辺への変換＝「分解」（多対1を許す。変換可能）</li>
  <li>右辺から左辺への変換＝「合成」（1対多を許すので、<strong>変換不可能</strong>）</li>
</ul>
例：<br>
<a href="https://ja.wikipedia.org/wiki/%E4%B8%8A%E4%BB%98%E3%81%8D%E6%96%87%E5%AD%97" target="_blank">上付き文字</a><br>
<a href="https://ja.wikipedia.org/wiki/%E4%B8%8B%E4%BB%98%E3%81%8D%E6%96%87%E5%AD%97" target="_blank">下付き文字</a><br>
<a href="https://ja.wikipedia.org/wiki/%E5%85%A8%E8%A7%92%E3%81%A8%E5%8D%8A%E8%A7%92" target="_blank">全角と半角</a>
***
<h3>1.2　EUC-JP/Shift-JISでの正規化</h3>
例：EUC-JPを表す正規表現
```
(
                  [\\x00-\\x7F]|//コードセット０ (ASCII/JIS ローマ字)
     [\\xA1-\\xFE][\\xA1-\\xFE]|//コードセット１（JIS X 0208:1997）
             \\x8E[\\xA0-\\xDF]|//コードセット２（半角片仮名）
\\x8F[\\xA1-\\xFE][\\xA1-\\xFE] //コードセット３（JIS X 0212-1990）
)
```
例：Shift-JISを表す正規表現
```
(
                                   [\\x00-\\x7F]|//ASCII/JIS ローマ字
[\\x81-\\x9F\\xE0-\\xFC][\\x40-\\x7E\\x80-\\xFC]|//JIS X 0208:1997
                                   [\\xA0-\\xDF] //半角片仮名
)
```
***
<h3>1.3　Unicode正規化</h3>
正規化形式の種類：
<ul>
  <li>NFD (Normalization Form Canonical Decomposition, 正規化形式D）<br>文字は正準等価性によって分解されます。</li>
  <li>NFC（Normalization Form Connonical Composition, 正規化形式C）<br>文字は正準等価性によって分解され、
再度合成されます。<br>（結果として文字の並びが変換前と変わることがありえます。）</li>
  <li>NFKD（Normalization Form Compatibility Decomposition, 正規化形式KD）<br>文字は互換等価性によって分解されます。</li>
  <li><strong>NFKC（Normalization Form Compatibility Composition, 正規化形式KC）<br>文字は互換等価性によって分解され、
正準等価性によって再度合成されます。</strong></li>
</ul>
***
<h3>1.4　文字の字種情報の取得</h3>
<h4>1.4.1　Character.getName</h4>
```scala
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
```
<h4>1.4.2　Character.getType</h4>
```scala
  private val hiraganaChar: Char = 'か'
  private val katakanaChar: Char = 'カ'
  private val alphabetChar: Char = 'C'
  private val symbolChar:   Char = '+'

  private val hiraganaCodePoint: Int = Character.codePointAt(Array[Char](hiraganaChar), 0)
  private val katakanaCodePoint: Int = Character.codePointAt(Array[Char](katakanaChar), 0)
  private val alphabetCodePoint: Int = Character.codePointAt(Array[Char](alphabetChar), 0)
  private val symbolCodePoint:   Int = Character.codePointAt(Array[Char](symbolChar),   0)
  
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
```
<h4>1.4.3　Character.getDirectionality</h4>
```scala
  private val hiraganaChar: Char = 'か'
  private val katakanaChar: Char = 'カ'
  private val alphabetChar: Char = 'C'
  private val symbolChar:   Char = '+'

  private val hiraganaCodePoint: Int = Character.codePointAt(Array[Char](hiraganaChar), 0)
  private val katakanaCodePoint: Int = Character.codePointAt(Array[Char](katakanaChar), 0)
  private val alphabetCodePoint: Int = Character.codePointAt(Array[Char](alphabetChar), 0)
  private val symbolCodePoint:   Int = Character.codePointAt(Array[Char](symbolChar),   0)

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
```

***
<h3>1.5　文字の字種判定</h3>
Characterクラスのメソッドで字種の判定を行います。引数はCharでもコードポイント（Int）でも可です。ただし引数がCharだとCharにはBMP領域の文字しか格納できないので、補助文字は判定できません。
<table>
<tr><td>isDefined</td><td>Unicodeで定義されている。</td></tr>
<tr><td>isDigit</td><td><strong>半角数字・全角数字など</strong>の数字である。<br><strong>「〇」以外の漢数字は数字として判定されない。</strong><br>（Character.getType(codePoint)がDECIMAL_DIGIT_NUMBERである。）</td></tr>
<tr><td>isLetter</td><td>汎用文字である。<br>（Character.getType(codePoint)がUPPERCASE_LETER, LOWERCASE_LETTER, TITLECASE_LETTER, MODIFIER_LETTER, OTHER_LETTERのいずれかである。）</td></tr>
<tr><td>isLetterOrDigit</td><td>Character.isLetter || Character.isDigit</td></tr>
<tr><td>isLowerCase</td><td>小文字である。Character.getType(codePoint)がLOWERCASE_LETTERであるか、Unicode標準で規定された寄与プロパティOther_Lowercaseを持つ。</td></tr>
<tr><td>isTitleCase</td><td>タイトルケース文字である。Character.getType(codePoint)がTITLECASE_LETTERである。</td></tr>
<tr><td>isUpperCase</td><td>大文字である。Character.getType(codePoint)がUPPERCASE_LETTERであるか、Unicode標準で規定された寄与プロパティOther_Uppercaseを持つ。</td></tr>
<tr><td>isSpaceChar</td><td>Unicode標準の空白文字である。Character.getType(codePoint)がSPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATORのいずれかである。</td></tr>
<tr><td>isWhiteSpace</td><td>空白文字である。次のいずれかを満たす場合、空白文字とみなされます。
<ul>
<li>Unicode の空白文字 (SPACE_SEPARATOR、LINE_SEPARATOR、または PARAGRAPH_SEPARATOR) であるが、改行なしの空白 ('\u00A0'、'\u2007'、'\u202F') ではない。</li>
<li>'\t' (U+0009 水平タブ) である</li>
<li>'\n' (U+000A 改行) である。</li>
<li>'\u000B' (U+000B 垂直タブ) である</li>
<li>'\f' (U+000C フォームフィード) である。</li>
<li>'\r' (U+000D 復帰) である。</li>
<li>'\u001C' (U+001C ファイル区切り文字) である。</li>
<li>'\u001D' (U+001D グループ区切り文字) である。</li>
<li>'\u001E' (U+001E レコード区切り文字) である。</li>
<li>'\u001F' (U+001F 単位区切り文字) である。</li>
</ul>
</td></tr>
<tr><td>isMirrored</td><td>Unicode 仕様に従って、指定された文字 (Unicode コードポイント) をミラー化するかどうかを判定します。テキスト内で右から左に文字が描画される場合、文字のミラー化により、グリフが水平方向にミラー化されます。たとえば、'\u0028' LEFT PARENTHESIS は、セマンティクスでは開き括弧として定義されています。これは、左から右に描画されるテキストでは「(」になり、右から左に描画されるテキストでは「)」になります。</td></tr>
<tr><td>isISOControl</td><td>参照された文字 (Unicode コードポイント) が ISO 制御文字かどうかを判定します。コードが '\u0000' - '\u001F' の範囲、または '\u007F' - '\u009F' の範囲の場合は、ISO 制御文字と見なされます。</td></tr>
<tr><td>isIdentifierIgnorable</td><td>指定された文字 (Unicode コードポイント) が、Java 識別子または Unicode 識別子内で無視可能な文字かどうかを判定します。次の Unicode 文字は、Java 識別子や Unicode 識別子内で無視できます。
<ul><li>空白以外の ISO 制御文字
<ul>
<li>'\u0000' - '\u0008'</li>
<li>'\u000E' - '\u001B'</li>
<li>'\u007F' - '\u009F'</li>
</ul></li>
<li>Character.getType(codePoint)がFORMATであるすべての文字</li>
</ul>
</td></tr>
<tr><td>isUnicodeIdentifierStart</td><td>指定された文字 (Unicode コードポイント) を Unicode 識別子の最初の文字として指定可能かどうかを判定します。次の条件のどれかに当てはまる場合にだけ、その文字を Unicode 識別子の最初に指定できます。
<ul>
<li>Character.isLetter(codePoint) が true を返す。</li>
<li>Character.getType(codePoint) が LETTER_NUMBER を返す。</li>
</ul>
</td></tr>
<tr><td>isUnicodeIdentifierPart</td><td>指定された文字 (Unicode コードポイント) を Unicode 識別子の最初の文字以外に使用可能かどうかを判定します。次の文のどれかに当てはまる場合にだけ、その文字を Unicode 識別子の一部に使用できます。
<ul>
<li>汎用文字である</li>
<li>連結句読点文字である ('_' など)</li>
<li>数字である</li>
<li>数値汎用文字である (ローマ数字文字など)</li>
<li>連結マークである</li>
<li>非スペーシングマークである</li>
<li>この文字の Character.isIdentifierIgnorable が true を返す。</li>
</ul>
</td></tr>
<tr><td>isJavaIdentifierStart</td><td>
文字 (Unicode コードポイント) を Java 識別子の最初の文字として指定可能かどうかを判定します。次の条件のどれかに当てはまる場合にだけ、その文字を Java 識別子の最初に指定できます。
<ul>
<li>Character.isLetter(codePoint) が次を返す: true</li>
<li>Character.getType(codePoint) が次を返す: LETTER_NUMBER</li>
<li><u>参照される文字が通貨記号である ('$' など)</u></li>
<li><u>参照文字が連結句読点文字である ('_' など)</u></li>
</ul>
</td></tr>
<tr><td>isJavaIdentifierPart</td><td>文字 (Unicode コードポイント) を Java 識別子の最初の文字以外に使用可能かどうかを判定します。次のどれかに当てはまる場合にだけ、その文字を Java 識別子の一部に指定できます。
<ul>
<li>汎用文字である</li>
<li><u>通貨記号である ('$' など)</u></li>
<li>連結句読点文字である ('_' など)</li>
<li>数字である</li>
<li>数値汎用文字である (ローマ数字文字など)</li>
<li>連結マークである</li>
<li>非スペーシングマークである</li>
<li>文字の Character.isIdentifierIgnorable(codePoint) が true を返す。</li>
</ul>
</td></tr>
</table>
***
<h3>1.6　Unicode書体</h3>
<h3>1.7　Unicodeブロック</h3>
<h3>1.8　Unicodeカテゴリ</h3>
<h3>1.9　正規表現による字種のマッチング</h3>
<h4>1.9.1　POSIX文字クラス(US-ASCIIのみ)</h4>
<table>
<tr><td>\p{Lower}</td><td>小文字の英字</td><td>[a-z]</td></tr>
<tr><td>\p{Upper}</td><td>大文字の英字</td><td>[A-Z]</td></tr>
<tr><td>\p{ASCII}</td><td>すべてのASCII文字</td><td>[\x00-\x7F]</td></tr>
<tr><td>\p{Alpha}</td><td>英字</td><td>[\p{Lower}\p{Upper}]</td></tr>
<tr><td>\p{Digit}</td><td>10 進数字</td><td>[0-9]</td></tr>
<tr><td>\p{Alnum}</td><td>英数字</td><td>[\p{Alpha}\p{Digit}]</td></tr>
<tr><td>\p{Punct}</td><td>句読文字</td><td>!"#$%&amp;'()*+,-./:;&lt;=&gt;?@[\]^_`{|}~のうちのひとつ</td></tr>
<tr><td>\p{Graph}</td><td>表示できる文字</td><td>[\p{Alnum}\p{Punct}]</td></tr>
<tr><td>\p{Print}</td><td>プリント可能文字</td><td>[\p{Graph}\x20]</td></tr>
<tr><td>\p{Blank}</td><td>空白またはタブ</td><td>[\t]</td></tr>
<tr><td>\p{Cntrl}</td><td>制御文字</td><td>[\x00-\x1F\x7F]</td></tr>
<tr><td>\p{XDigit}</td><td>16進数字</td><td>[0-9a-fA-F]</td></tr>
<tr><td>\p{Space}</td><td>空白文字</td><td>[\t\n\x0B\f\r]</td></tr>
</table>

<h4>1.9.2　java.lang.Characterクラス(単純なjava文字タイプ)</h4>
<table>
<tr><td>\p{javaLowerCase}</td><td>java.lang.Character.isLowerCase()と等価</td></tr>
<tr><td>\p{javaUpperCase}</td><td>java.lang.Character.isUpperCase()と等価</td></tr>
<tr><td>\p{javaWhitespace}</td><td>java.lang.Character.isWhitespace()と等価</td></tr>
<tr><td>\p{javaMirrored}</td><td>java.lang.Character.isMirrored()と等価</td></tr>
</table>

<h4>1.9.3　Unicode書体、ブロック、カテゴリ、バイナリ・プロパティのクラス</h4>
<table>
<tr><td>\p{IsLatin}</td><td>Latin 書体文字(書体)</td></tr>
<tr><td>\p{InGreek}</td><td>Greek ブロックの文字(ブロック)</td></tr>
<tr><td>\p{Lu}</td><td>大文字(カテゴリ)</td></tr>
<tr><td>\p{IsAlphabetic}</td><td>英字(バイナリ・プロパティ)</td></tr>
<tr><td>\p{Sc}</td><td>通貨記号</td></tr>
<tr><td>\P{InGreek}</td><td>ギリシャ語ブロック以外の文字(否定)</td></tr>
<tr><td>[\p{L}&&[^\p{Lu}]]</td><td>大文字以外の文字(減算)</td></tr>
</table>

***
<h3>1.10　字種の変換</h3>
<h4>1.10.1　アルファベットのletter case</h4>
<table>
<tr><th>letter case</th><th>例</th><th>説明</th></tr>
<tr><td>lower case</td><td>abc</td><td>全部小文字</td></tr>
<tr><td>title case</td><td>Abc</td><td>先頭文字が大文字で残りは小文字<br>１文字で複数文字あるように見える文字には、<br>title caseを持っている文字があります。<br>例：<ul><li>upper case:「Ǉ」（U+01C7）</li><li>title case:「ǈ」（U+01C8）</li><li>lower case:「	ǉ」（U+01C9）</li></ul></td></tr>
<tr><td>upper case</td><td>ABC</td><td>全部大文字</td></tr>
</table>
***
<h4>1.10.2　文字のletter caseの変換</h4>
```scala
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
```
***
<h4>1.10.3　文字列のletter caseの変換</h4>
```scala
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
```
***
<h4>1.10.4　カタカナとひらがなの相互変換（自作）</h4>
<a href="https://github.com/ynupc/scalastringcourseday7/blob/master/src/test/scala/text/JapaneseCharacterCaseConverter.scala" target="_blank">JapaneseCharacterCaseConverterの実装</a>
自作のNormalizedStringOption、NormalizedString、StringOptionについては次章で取り扱う。
```scala
  private val nullPoGa: NormalizedStringOption = NormalizedStringOption(NormalizedString(StringOption("「ぬるぽ」「ガッ」")))

  @Test
  def testKatakana2Hiragana(): Unit = {
    assert(JapaneseCharacterCaseConverter.convertKatakana2Hiragana(nullPoGa).get.toString == "「ぬるぽ」「がっ」")
  }

  @Test
  def testHiragana2Katakana(): Unit = {
    assert(JapaneseCharacterCaseConverter.convertHiragana2Katakana(nullPoGa).get.toString == "「ヌルポ」「ガッ」")
  }
```
