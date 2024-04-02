package antennsw

/**
 * Lets you build an [[Array[Byte]] from a string like:
 * {{{
 *   x"F101E3"
 * }}}
 */
extension (sc: StringContext)
  def x(args: String*): Array[Byte] = {
    val parts = sc.parts
    val hex: Iterator[String] = parts.head.grouped(2)
    val bytes: Iterator[Byte] = hex.map { sx =>
      val value: Int = Integer.parseInt(sx, 16)
      value.toByte
    }
    val result = bytes.toArray
    result
  }
