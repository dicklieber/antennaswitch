package antennsw

import scala.runtime.LazyVals.Names.state

class AntennaMapSpec extends WithConfigSpec {
  val antennas = config.antennas
  val radios = config.radios
  "AntennaMap" should {
    "init state" when {
      val antennaMap = new AntennaMap(config)
      "have all defined antennas" in {
        val state = antennaMap.state
        state must have length (2)
      }
      "report errors" in {
        val errors = antennaMap.errors
        errors must have length (2)
        errors(0) mustBe ("""Band: "19" in antenna: "MA6B" (1) is not defined!""")
        errors(1) mustBe ("""Band: "crap" in antenna: "Ant with undefeind band" (5) is not defined!""")
      }
    }
    "switch" when {
      val antennaMap = new AntennaMap(config)
      val anAntenna = antennas.head
      val aRadio = radios.head

      "change to a new radio" in {
        val antenna2 = antennas(2)
        val antenna1 = antennas(1)
        antennaMap.switch(aRadio, antenna2)
        antennaMap.switch(aRadio, antenna1)
      }
    }
  }
}
