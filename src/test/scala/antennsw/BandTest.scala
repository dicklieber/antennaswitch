package antennsw

import scala.collection.immutable.Range.Inclusive
import scala.util.{Failure, Success, Try}

class BandTest extends AntennaSpec {
  "Band" should {
    "toString" in {
      val band = Band("40M", Range.Inclusive(7_100_000, 7_250_000, 1))
      val string = band.toString
      string mustBe ("40M: 7.1-7.25")
    }

    "Round trip" in {
      Band("40M: 7.1-7.25") match
        case Failure(exception) =>
          exception.getMessage mustBe """Can't parse "crap"! Expecting something like "40M: 7.1-7.25""""
        case Success(value) =>


    }
    "Illformed" in {
      val tried: Try[Band] = Band("crap")
      tried.failed.get.getMessage mustBe """Can't parse "crap"! Expecting something like "40M: 7.1-7.25""""
    }
  }
}
