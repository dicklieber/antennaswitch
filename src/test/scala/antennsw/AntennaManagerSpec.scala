package antennsw

class AntennaManagerSpec extends WithConfigSpec {

  "AntennaManager" should {
    "newFrequency" in {
      val antennaMap = new AntennaMap(config)
      val antennaManager = new AntennaManager(config, antennaMap)
      antennaManager.newFrequency(7_200_000, Radio("", "M"))

    }
  }
}
