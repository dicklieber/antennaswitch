package antennsw

import scala.language.{dynamics, existentials, postfixOps}

object Frequency:
  /**
   *
   * @param mhz e.g 14.233
   * @return hz
   */
  def apply(mhz: String): Int = {
    try
      mhz match
        case regex(whole, null) =>
          println(whole)
          whole.toInt * MEGA

        case regex(whole, fraction) =>
          println(whole)
          val paddedFract = fraction.padTo(6, '0')
          val fract: Int = paddedFract.toInt
          whole.toInt * MEGA + fract

        case x =>
          throw new IllegalArgumentException(s"""Illformed MHz: "${mhz}"!""")

    catch
      case e: Exception =>
        throw e
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

  private val regex = """(\d+).?(\d+)?""".r

  private val MEGA = 1_000_000
