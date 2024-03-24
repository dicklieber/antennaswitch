package antennsw

import scala.collection.immutable.Range.Inclusive

class BandTest extends AntennaSpec {
  "Band" should {
    "toString" in {
      val band = Band("40M", Range.Inclusive(7_100_000, 7_250_000, 1))
      val string = band.toString
      string mustBe ("40M: 7.1-7.25")
    }

    "Round trip" in {
      val band = Band("40M: 7.1-7.25")
      val sBand = band.toString
      val backAgain = Band(sBand)
      backAgain mustBe band
    }
    "Illformed" in {
      val exception = intercept[IllegalArgumentException](Band("crap"))
      exception mustBe a[IllegalArgumentException]
      exception.getMessage mustBe """Can't parse "crap"! Expecting something like "40M: 7.1-7.25""""
    }
  }
}
