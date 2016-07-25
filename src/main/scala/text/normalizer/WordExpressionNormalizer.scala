package text.normalizer

/**
  * @author ynupc
  *         Created on 2016/02/19
  */
object WordExpressionNormalizer extends DictionaryBasedNormalizer(StringOption("word_expression_dic.yml")) {
  override protected def sortNotationVariants(notationVariants: List[String]): List[String] = {
    super.sortNotationVariants(notationVariants).
    sortWith((a, b) => a.length > b.length)//length descending order
  }

  override protected def sortRepresentations(representations: List[String]): List[String] = {
    super.sortRepresentations(representations).
    sortWith((a, b) => a.length < b.length)//length ascending order
  }

  override protected def replaceAll(input: String, term: String, replacement: String): String = {
    input.replaceAll(term, replacement)
  }
}

