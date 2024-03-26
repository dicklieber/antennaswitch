package antennsw

class AntennaMapSpec extends WithConfigSpec {
  "AntennaMap" should {
    "init state" when {
      val antennaMap = new AntennaMap(config)
      "have all defined antennas" in {
        val state = antennaMap.state
        state must have length(5)
      }
      "report errors" in {
        val errors = antennaMap.errors
        errors must have length(2)
        errors(0) mustBe("""Band: "19" in antenna: "MA6B" (1) is not defined!""")
        errors(1) mustBe("""Band: "crap" in antenna: "Ant with undefeind band" (5) is not defined!""")
      }
    }
  }
}
