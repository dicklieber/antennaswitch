package antennsw

import jdk.javadoc.internal.tool.Start
import play.api.libs.json.{JsResult, JsString, JsSuccess, JsValue}

import scala.util.matching.Regex

/**
 *
 * @param meter e.g. 40 or 160
 * @param range start/end
 */
case class Band(meter: Int, range: Range):
  override def toString: String =
    val sMhzStart = Frequency(range.start)
    val sMhzEnd = Frequency(range.end)
    s"${meter}M: $sMhzStart-$sMhzEnd"

object Band:
  def apply(s: String): Band =
    s match
      case r(meter, startMhz, endMHz) =>
        val start: Int = Frequency(startMhz)
        val end: Int = Frequency(endMHz)
        Band(meter.toInt, start, end)
      case x =>
        throw new IllegalArgumentException(s"""Can't parse "$s"! Expecting something like "40M: 7.1-7.25"""")
  def apply(meter:Int, start:Int, end:Int):Band=
    Band(meter, Range.Inclusive(start, end, 1))


  val r: Regex = """(\d+)M:\s*([\d\.]+)-([\d\.]+)""".r
