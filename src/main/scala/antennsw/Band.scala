package antennsw

import antennsw.Pi4j.logger
import ch.qos.logback.classic.spi.ILoggingEvent
import com.typesafe.scalalogging.LazyLogging

import scala.util.Try
import scala.util.matching.Regex

/**
 *
 * @param bandName e.g. 40 or 160M
 * @param range    start/end
 */
case class Band(bandName: String, range: Range):
  override def toString: String =
    val sMhzStart = Frequency(range.start)
    val sMhzEnd = Frequency(range.end)
    s"${bandName}: $sMhzStart-$sMhzEnd"

object Band  extends LazyLogging:
  def apply(bandName: String, start: Int, end: Int): Band =
    Band(bandName, Range.Inclusive(start, end, 1))

  def apply(s: String): Try[Band] =
    Try(s match
      case r(bandName, startMhz, endMHz) =>
        val start: Int = Frequency(startMhz)
        val end: Int = Frequency(endMHz)
          Band(bandName, start, end)
      case x =>
        val value = s"""Can't parse "$s"! Expecting something like "40M: 7.1-7.25""""
        logger.error(value) 
        throw new IllegalArgumentException(value)
    )

  val r: Regex = """([\w ]+):\s*([\d.]+)-([\d.]+)""".r
