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

---|---
NFD (Normalization Form Canonical Decomposition, 正規化形式D）|文字は正準等価性によって分解されます。
NFC（Normalization Form Connonical Composition, 正規化形式C）|文字は正準等価性によって分解され、
再度合成されます。
（結果として文字の並びが変換前と変わることがありえます。）
NFKD（Normalization Form Compatibility Decomposition, 正規化形式KD）|文字は互換等価性によって分解されます。
NFKC（Normalization Form Compatibility Composition, 正規化形式KC）|文字は互換等価性によって分解され、
正準等価性によって再度合成されます。
<h3>字種の判定</h3>
<h3>字種の変換</h3>
