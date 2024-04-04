package antennsw

import play.api.libs.json.*

/**
 *
 * @param name  user defined name.
 * @param relayNumber  1-8 corresponds to relay
 * @param bands that this antenna can work with
 */
case class Antenna(name: String, relayNumber: Int, bandNames: String *)

object Antenna:
  implicit val fmtAntenna: Format[Antenna] = Json.format[Antenna]

