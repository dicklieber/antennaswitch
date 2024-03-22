package antennsw

import scala.language.{dynamics, postfixOps}

object Frequency:
  /**
   *
   * @param mhz e.g 14.233
   * @return hz
   */
  def apply(mhz: String): Int = {
    val tokens = mhz.split('.')
    val wholeMhz = tokens.head.toInt
    val right: String = tokens(1)
    val paddedFract = right.padTo(6, '0')
    val fract: Int = paddedFract.toInt
    val r: Int = wholeMhz * MEGA + fract
    r
  }

  /**
   *
   * @param hz
   * @return MHz with decimal point.
   */
  def apply(hz: Int): String =
    val whoMhz: Int = hz / MEGA
    val fractMhz = hz % MEGA
    val str = s"$whoMhz.$fractMhz"
    val reverse = str.reverse
    val fixed = reverse.dropWhile(_ == '0')
    val result = fixed.reverse

    result

val MEGA = 1_000_000
