package antennsw

import play.api.libs.json.*

import java.io.InputStream
import scala.util.Using

object ConfigLoader:
  def apply(configFile:String="/config.json"):Config =
    Using.resource(getClass.getResourceAsStream(configFile)){ (inputStream: InputStream) =>
      val jsValue = Json.parse(inputStream)
      jsValue.as[Config]
    }
    


/*  def apply(): Config =
    Config(
      bands = Seq(
        Band("160: 1.8-2"),
        Band("80: 3.5-4"),
        Band("60: 5.3-5.5"),
        Band("40: 7-7.3"),
        Band("30: 10.1-10.15"),
        Band("20: 14-14.35"),
        Band("17: 18-18.168"),
        Band("15: 21-21.45"),
        Band("12: 24-24.99"),
        Band("10: 28-29.7"),
        Band("6: 50-54"),
      ),
      antennas = Seq(
        Antenna("MA6B", 1, "20", "17", "15", "12", "19", "6")
      ),
      radios = Seq(
        Radio("Flex", "M"),
        Radio("IC-9700", "S")
      )

    )
*/
case class Config(bands: Seq[Band], antennas: Seq[Antenna], radios: Seq[Radio] = Seq.empty)

object Config:
  implicit val fmtConfig: Format[Config] = Json.format[Config]
  implicit val fmtAntenna: Format[Antenna] = Json.format[Antenna]
  implicit val fmtRadio: Format[Radio] = Json.format[Radio]
  implicit val fmtBand: Format[Band] = Json.format[Band]
end Config
