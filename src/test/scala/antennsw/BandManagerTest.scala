package antennsw


class BandManagerTest extends AntennaSpec {
  "load default" should {
    "started" in {
      val bandManager = BandManager()
      bandManager.bands must have length (11)
    }
  }

}
