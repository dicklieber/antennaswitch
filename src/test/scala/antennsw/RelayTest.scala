package antennsw

class RelayTest extends AntennaSpec {

  "RelayTest" should {
    "toString" in {
      val relay: Relay = Relay(x"A00101A2", x"A00100A1")
      val onArray: Array[Byte] = relay.on
      onArray(0) mustBe(0xA0.toByte)
      onArray(1) mustBe(0x01.toByte)
      onArray(2) mustBe(0x01.toByte)
      onArray(3) mustBe(0xA2.toByte)
    }
  }
}
