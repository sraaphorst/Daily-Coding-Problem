package dcp.day029

object Decoder {
  def decode(encoding: String): String = {
    def pickoff(rem: String): Option[(Int, Char, String)] = {
      val number = rem.takeWhile(_.isDigit)
      val rest   = rem.dropWhile(_.isDigit)
      if (number.nonEmpty)
        Some(number.toInt, rest.head, rest.tail)
      else None
    }

    def decodeAux(rem: String): String = pickoff(rem) match {
      case None => ""
      case Some((ct, ch, rest)) =>
        f"${ch.toString * ct}${decodeAux(rest)}"
    }
    decodeAux(encoding)
  }
}
