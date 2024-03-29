package antennsw

import scala.runtime.LazyVals.Names.state

//noinspection ZeroIndexToHead
class AntennaMapSpec extends WithConfigSpec {
  val antennas = config.antennas
  val radios = config.radios
  val radio1 = radios(0)
  val radio2 = radios(1)
  val antenna1 = antennas(1)
  val antenna2 = antennas(2)

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

      "change to a new antenna" in {
        antennaMap.switch(radio1, antenna2)
        antennaMap.switch(radio1, antenna1)
        val state: Seq[SwitchState] = antennaMap.state
        checkDuplicateConnection(state)
      }
      "switch 2 radios to same antenna" in {
        antennaMap.switch(radio1, antenna1)
        antennaMap.switch(radio2, antenna1)
        val state: Seq[SwitchState] = antennaMap.state
        state must have length(2)
        checkDuplicateConnection(state)
      }
    }
  }

  def checkDuplicateConnection(state: Seq[SwitchState]):Unit =
    state must have length (2)

    val antennasInUse: Seq[Int] = (for{
      switchState <- state
      antenna <- switchState.maybeAntenna
    }yield{
      antenna.port
    })
    if( antennasInUse.length > 1)
//      antennasInUse(0) must not equal(antennasInUse(1))
      assert(antennasInUse(0) != antennasInUse(1), "Radio connected to multiple antennas!")
}
