package antennsw

import org.scalatest.matchers.must.Matchers.mustBe
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}

class FieldKeyToString extends AntennaSpec with TableDrivenPropertyChecks:
  val mHz2Hz: TableFor2[String, Int] =
    Table(
      ("Mhz", "hz"), // First tuple defines column names
      ("7.125", 7_125_000),
      ("7", 7_000_000),
      ("7.0", 7_000_000),
      ("14.2", 14_200_000),
      ("14.2", 14_200_000),

    )
  val hzToMhz: TableFor2[Int, String] =
    Table(
      ("hz", "Mhz"), // First tuple defines column names
      (7_125_000, "7.125"),
      (7_000_000, "7."),
      (14_200_000, "14.2")
    )

  "Mhz to Hz" in {
    forAll(mHz2Hz) { (mhz: String, hz: Int) =>
      val n = Frequency(mhz)
      n mustBe (hz)
    }
  }
  "Hz to MHz" in {
    forAll(hzToMhz) { (hz: Int, mhz: String) =>
      val str = Frequency(hz)
      str mustBe (mhz)
    }
  }
