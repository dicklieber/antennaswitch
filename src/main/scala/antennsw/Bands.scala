package antennsw

import com.typesafe.config.{Config, ConfigValue}
import com.typesafe.scalalogging.LazyLogging
import jakarta.inject.{Inject, Singleton}
import play.api.libs.json.*
import scalafx.beans.value

import java.util
import java.util.Map
import scala.collection.immutable.Range.Inclusive
import scala.util.matching.Regex

@Singleton
class Bands @Inject()(config: Config) extends LazyLogging:
  //  private val list: Config = config.getConfig("bands")
  //  private val set: util.Set[Map.Entry[String, ConfigValue]] = list.entrySet()
  //  private val value: Map.Entry[String, ConfigValue] = set.iterator().next()

  logger.info("hi")

case class Band(meter: Int, range: Range)

object Band:
  def apply(s:String):Band =
    throw new NotImplementedError() //todo
    
  implicit val fmtBand: Format[Band] = new Format[Band] {
    override def reads(json: JsValue): JsResult[Band] = {
      val sBand = json.as[String]
      try {
        JsSuccess(apply(sBand))
      }
      catch {
        case e: IllegalArgumentException => JsError(e.getMessage)
      }
    }

    override def writes(band: Band): JsValue = {
      JsString(s"${band.meter}: ${band.range.start}-${band.range.start}")
    }
  }

//  val r: Regex = """(\d+):\s*(\d+)\.(\d+)-(\d+)\.(\d+)""".r
end Band

