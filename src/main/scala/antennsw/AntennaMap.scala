package antennsw

import com.typesafe.scalalogging.LazyLogging
import jakarta.inject.*

import scala.collection.concurrent.TrieMap
import scala.collection.mutable

/**
 * Maps [[Radio]]s to [[Antenna]] ports.
 *
 * @param config what the user can change.
 */
@Singleton
class AntennaMap @Inject()(config: Config) extends LazyLogging:
  private var theMap: TrieMap[Radio, Option[Antenna]] = {
    val builder = TrieMap.newBuilder[Radio, Option[Antenna]]
    config.radios.foreach { radio =>
      builder.addOne(radio, None)
    }
    builder.result()
  }
  private val bandNames = config.bands.map(_.bandName)
  val errors: Seq[String] = for {
    antenna <- config.antennas
    bandName <- antenna.bandNames
    if !bandNames.contains(bandName)
  } yield {
    s"""Band: "$bandName" in antenna: "${antenna.name}" (${antenna.port}) is not defined!"""
  }

  def state: Seq[SwitchState] =
    theMap
      .toSeq
      .map(entry => SwitchState(entry))
      .sortBy(_._1.port)


  def switch(radio: Radio, antenna: Antenna): Unit =
    theMap.put(radio, Option(antenna))

case class SwitchState(radio: Radio, maybeAntenna: Option[Antenna]) extends Ordered[SwitchState]:
  def compare(that: SwitchState): Int = radio.port compareTo that.radio.port

object SwitchState:
  def apply(entry: (Radio, Option[Antenna])): SwitchState =
    SwitchState(entry._1, entry._2)
