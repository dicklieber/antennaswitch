package antennsw

import play.api.libs.json.Json

import scala.collection.immutable.Range.Inclusive

class BandTest extends AntennaSpec {

  "JSON" should {
    "round trip" in {
      val range: Range = Range(14_000_000, 14_240_000)
      val band: Band = Band(20, range)
      val value1 = Json.toJson(band)
      val sBand = Json.prettyPrint(value1)
      val backAgain = Json.parse(sBand).as[Band]
      backAgain mustBe (band)
    }
  }

}
