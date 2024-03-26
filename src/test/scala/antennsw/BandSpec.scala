package antennsw

class BandTest extends AntennaSpec {
  "Band" should {
    "toString" in {
      val band = Band("40M", "7.1", "7.250")
      val string = band.toString
      string mustBe ("40M: 7.1-7.25")
    }

    "Illformed" in {
      val exception = intercept[IllegalArgumentException](Band("crap"))
      exception mustBe  a[IllegalArgumentException]
      exception.getMessage mustBe """Can't parse "crap"! Expecting something like "40M: 7.1-7.25""""
      
    }
  }
}
