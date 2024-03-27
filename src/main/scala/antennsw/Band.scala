package antennsw

import antennsw.Band.BandName
import antennsw.Pi4j.logger
import com.typesafe.scalalogging.{LazyLogging, Logger}

import java.util.UUID
import scala.util.matching.Regex
import UuidFormat.*
import play.api.libs.json.{Format, Json}
/**
 *
 * @param bandName e.g. 40 or 160M
 * @param startMhz e.g. 14. 7.1 
 * @param endMhz   e.g. 14.233
 */
case class Band(bandName: BandName, startMhz: String, endMhz: String, id:UUID=UUID.randomUUID()):
  val range: Range = Range.inclusive(Frequency(startMhz), Frequency(endMhz), 1)

  override def toString: String =
    s"${bandName}: ${Frequency(range.start)}-${Frequency(range.end)}"

  def contains(hz: Int): Boolean =
    range.contains(hz)
    
object Band extends LazyLogging:
  /**
   *
   * @param str
   * @return
   */
  @throws[IllegalArgumentException]
  def apply(str: String): Band =
    str match
      case r(bandName, startMhz, endMHz) =>
        Band(bandName, startMhz, endMHz)
      case x =>
        val value = s"""Can't parse "$str"! Expecting something like "40M: 7.1-7.25""""
        logger.error(value)
        throw new IllegalArgumentException(value)

  val r: Regex = """([\w ]+):\s*([\d.]+)-([\d.]+)""".r

  type BandName = String
  implicit val fmtBand: Format[Band] = Json.format[Band]
