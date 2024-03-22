package antennsw

import scala.Console.in

class FrequencyTest extends AntennaSpec {
  "Frequency" should {
    "s2int" in {
      Frequency("14.2") mustBe 14_200_000
      Frequency("7.0") mustBe 7_000_000
    }

    "int2s" in {
      Frequency(14_200_000) mustBe ("14.2")
    }

    "round Trip" in {
      val str = "10.123"
      val hz = Frequency(str)
      hz mustBe (10_123_000)
      Frequency(hz) mustBe (str)
    }
  }
}