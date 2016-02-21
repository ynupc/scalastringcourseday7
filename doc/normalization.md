# 1.　文字の正規化
<h3>正準等価性・互換等価性</h3>
<h4>正準等価性</h4>
ダイアクリティカルマークを合成済みの文字「が」＝文字「か」＋合成用ダイアクリティカルマーク「゛」<br>
<ul>
  <li>左辺から右辺への変換＝「分解」（1対1なので、変換可能）</li>
  <li>右辺から左辺への変換＝「合成」（1対1なので、変換可能）</li>
</ul>
例：<br>
<a href="https://ja.wikipedia.org/wiki/%E3%83%80%E3%82%A4%E3%82%A2%E3%82%AF%E3%83%AA%E3%83%86%E3%82%A3%E3%82%AB%E3%83%AB%E3%83%9E%E3%83%BC%E3%82%AF" target="_blank">ダイアクリティカルマーク</a>
<h4>互換等価性</h4>
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
<h3>EUC-JP/Shift-JISでの正規化</h3>
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
<h3>Unicode正規化</h3>
正規化形式の種類
<ul>
  <li>NFD (Normalization Form Canonical Decomposition, 正規化形式D）<br>文字は正準等価性によって分解されます。</li>
  <li>NFC（Normalization Form Connonical Composition, 正規化形式C）<br>文字は正準等価性によって分解され、
再度合成されます。<br>（結果として文字の並びが変換前と変わることがありえます。）</li>
  <li>NFKD（Normalization Form Compatibility Decomposition, 正規化形式KD）<br>文字は互換等価性によって分解されます。</li>
  <li><strong>NFKC（Normalization Form Compatibility Composition, 正規化形式KC）<br>文字は互換等価性によって分解され、
正準等価性によって再度合成されます。</strong></li>
</ul>

<h3>字種の判定</h3>
<table>
<caption>Characterクラスの字種判定（引数はcharでもint コードポイントでも可、ただしcharだとBMPしか格納できないので、補助文字は判定不能。）</caption>
<tr><td>isDefined</td><td>Unicodeで定義されている</td></tr>
<tr><td>isDigit</td><td><strong>半角数字・全角数字など</strong>の数字である。<br>（Character.getType(code_point)がDECIMAL_DIGIT_NUMBERである。）</td></tr>
<tr><td>isLetter</td><td>汎用文字である。<br>（Character.getType(code_point)がUPPERCASE_LETER, LOWERCASE_LETTER, TITLECASE_LETTER, MODIFIER_LETTER, OTHER_LETTERのいずれかである。）</td></tr>
<tr><td>isLetterOrDigit</td><td>isLetter || isDigit</td></tr>
<tr><td>isLowerCase</td><td>小文字である。Character.getType(code_point)がLOWERCASE_LETTERであるか、Unicode標準で規定された寄与プロパティOther_Lowercaseを持つ。</td></tr>
<tr><td>isTitleCase</td><td>タイトルケース文字である。Character.getType(code_point)がTITLECASE_LETTERである。</td></tr>
<tr><td>isUpperCase</td><td>大文字である。Character.getType(code_point)がUPPERCASE_LETTERであるか、Unicode標準で規定された寄与プロパティOther_Uppercaseを持つ。</td></tr>
<tr><td>isSpaceChar</td><td>Unicode標準の空白文字である。Character.getType(code_point)がSPACE_SEPARATOR, LINE_SEPARATOR, PARAGRAPH_SEPARATORのいずれかである。</td></tr>
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
<li>Character.getType(code_point)がFORMATであるすべての文字</li>
</ul>
</td></tr>
<tr><td>isUnicodeIdentifierStart</td><td>指定された文字 (Unicode コードポイント) を Unicode 識別子の最初の文字として指定可能かどうかを判定します。次の条件のどれかに当てはまる場合にだけ、その文字を Unicode 識別子の最初に指定できます。
<ul>
<li>isLetter(codePoint) が true を返す。</li>
<li>getType(codePoint) が LETTER_NUMBER を返す。</li>
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
<li>この文字の isIdentifierIgnorable が true を返す。</li>
</ul>
</td></tr>
<tr><td>isJavaIdentifierStart</td><td>
文字 (Unicode コードポイント) を Java 識別子の最初の文字として指定可能かどうかを判定します。次の条件のどれかに当てはまる場合にだけ、その文字を Java 識別子の最初に指定できます。
<ul>
<li>isLetter(codePoint) が次を返す: true</li>
<li>getType(codePoint) が次を返す: LETTER_NUMBER</li>
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
<li>文字の isIdentifierIgnorable(codePoint) が true を返す。</li>
</ul>
</td></tr>
</table>


<h3>字種の変換</h3>
