package antennsw.relay.gpio

import antennsw.AntennaSpec

class GpioRelayBoardSpec extends AntennaSpec {

  "GpioRelayBoard" should {
    "p4jContext" in {
      val board = new GpioRelayBoard()
      board
    }
  }
}
