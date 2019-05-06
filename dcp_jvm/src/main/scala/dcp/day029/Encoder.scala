package dcp.day029

object Encoder {
  def encode(text: String): String = {
    def encodeRec(remain: String): String = remain.headOption match {
      case None     => ""
      case Some(ch) =>
        val count = remain.takeWhile(_ == ch).length
        val rest  = remain.dropWhile(_ == ch)
        f"$count$ch${encodeRec(rest)}"
    }
    encodeRec(text)
  }
}
