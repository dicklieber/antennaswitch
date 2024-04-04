package antennsw.relay.lan

import antennsw.{Antenna, AntennaSpec, Radio, SwitchState}

class WebRelaySpec extends AntennaSpec {
  val antenna1 = Antenna("a1", 1)
  val antenna2 = Antenna("a2", 2)
  val radio1 = Radio("r1", "M")
  val radio2 = Radio("r2", "S")
  "WebRelaySpec" should {
    "switch1" in {
      val webRelay = WebRelay("8relay", "192.168.1.4")
      webRelay.switch(SwitchState(radio1, antenna1))
    }
    "sweep" in {
      val webRelay = WebRelay("8relay", "192.168.1.4")
      for(rn <- 1 to 8){
        webRelay.switch(SwitchState(radio1, Antenna("", rn)))
      }
      webRelay.switch(SwitchState(radio1, None))
    }
  }
}
