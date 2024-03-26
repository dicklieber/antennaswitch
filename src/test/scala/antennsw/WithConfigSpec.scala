package antennsw

abstract class WithConfigSpec extends AntennaSpec:
  val config: Config = ConfigLoader.apply("/config.test.json")
