package antennsw

import play.api.libs.json.{Format, Json}

case class BandAntenna(bandName:String, antenna:Int)

object BandAntenna:
  implicit val fmtBandAntenna: Format[BandAntenna] = Json.format[BandAntenna]
