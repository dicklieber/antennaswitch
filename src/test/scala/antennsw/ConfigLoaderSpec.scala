package antennsw

import play.api.libs.json.Json

class ConfigLoaderSpec extends AntennaSpec {

  "ConfigLoaderManager" should {
    "apply" in {
      val config: Config = ConfigLoader()
      val bands = config.bands
      bands must have length(15)

      val jsValue = Json.toJson(config)
      val sJson = Json.prettyPrint(jsValue)
      println(sJson)
      
    }
  }
}
