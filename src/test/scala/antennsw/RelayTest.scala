package antennsw

import antennsw.relay.Relay
import antennsw.x

class RelayTest extends AntennaSpec {

  "RelayTest" should {
    "toString" in {
      val relay: Relay = Relay(1, x"A00101A2", x"A00100A1")
      val onArray: Array[Byte] = relay.on
      onArray(0) mustBe(0xA0.toByte)
      onArray(1) mustBe(0x01.toByte)
      onArray(2) mustBe(0x01.toByte)
      onArray(3) mustBe(0xA2.toByte)

      val string = relay.toString
      string mustBe("1 on: 0xa0, 0x01, 0x01, 0xa2 off: 0xa0, 0x01, 0x00, 0xa1")
    }
  }
}
