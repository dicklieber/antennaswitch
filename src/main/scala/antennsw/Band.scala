package antennsw

import scala.util.matching.Regex

/**
 *
 * @param bandName e.g. 40 or 160M
 * @param range start/end
 */
case class Band(bandName: String, range: Range):
  override def toString: String =
    val sMhzStart = Frequency(range.start)
    val sMhzEnd = Frequency(range.end)
    s"${bandName}: $sMhzStart-$sMhzEnd"

object Band:
  def apply(bandName: String, start: Int, end: Int): Band =
    Band(bandName, Range.Inclusive(start, end, 1))

  def apply(s: String): Band =
    s match
      case r(bandName, startMhz, endMHz) =>
        val start: Int = Frequency(startMhz)
        val end: Int = Frequency(endMHz)
        Band(bandName, start, end)
      case x =>
        throw new IllegalArgumentException(s"""Can't parse "$s"! Expecting something like "40M: 7.1-7.25"""")


  val r: Regex = """([\w ]+):\s*([\d.]+)-([\d.]+)""".r
