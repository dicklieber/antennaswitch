package antennsw

import com.typesafe.scalalogging.LazyLogging
import jakarta.inject.*

import scala.collection.concurrent.TrieMap
import scala.collection.mutable

/**
 * Maps [[Radio]]s to [[Antenna]] ports.
 * Handles logic rules:
 *  - [[Radio]] connected to zero or one [[Antenna]].
 *  - [[Antenna]] connected to exactly one [[Radio]].
 *
 * @param config what the user can change.
 */
@Singleton
class AntennaMap @Inject()(config: Config) extends Switcher with LazyLogging:
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

  var listeners: List[Seq[SwitchState] => Unit ] = Nil

  def listen(newState: Seq[SwitchState] => Unit) =
    listeners ::= newState

  def notify(ev: Seq[SwitchState]) = for (l <- listeners) l(ev)


  def switch(switchState: SwitchState): Unit =
    checkRules(switchState)
    theMap.put(switchState.radio, switchState.maybeAntenna)

  private def checkRules(candidate:SwitchState):Unit=
    // Is another radio connected to this antenna?
    for{
      (radio, maybeAntenna) <- theMap

      if radio != candidate.radio
      antenna <- maybeAntenna
      candidateAntenna <- candidate.maybeAntenna
      if antenna == candidateAntenna
    }yield{
      throw new RuleViolation(candidate)
    }
