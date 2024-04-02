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
        antennaMap.switch(SwitchState(radio1, antenna2))
        antennaMap.switch(SwitchState(radio1, antenna1))
        val state: Seq[SwitchState] = antennaMap.state
      }
      "switch 2 radios to same antenna" in {
        antennaMap.switch(SwitchState(radio1, antenna1))
        val exception = intercept[RuleViolation](
          antennaMap.switch(SwitchState(radio2, antenna1))
        )
        exception mustBe a[RuleViolation]
        exception.getMessage mustBe  "Aleady a connection to antenna: Some(Antenna(EndFed,2,List(160, 80, 40, 30)))!"
      }
    }
    "notify" when{
      "Get notified" in {
        val antennaMap = new AntennaMap(config)
        var notification: Seq[SwitchState] = Seq.empty
        antennaMap.listen{(newState:Seq[SwitchState]) =>
          println(newState)
          notification = newState
        }
        antennaMap.switch(SwitchState(radio1, antenna1))
        notification.nonEmpty mustBe(true)
        val st0: SwitchState = notification(0)
        st0.radio mustBe(radio1)
        st0.maybeAntenna mustBe Option(antenna1)

        val st1: SwitchState = notification(1)
        st1.radio mustBe(radio2)
        st1.maybeAntenna mustBe None
      }
    }
  }
}
