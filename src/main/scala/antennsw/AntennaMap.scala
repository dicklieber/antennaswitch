package antennsw

import com.typesafe.scalalogging.LazyLogging
import jakarta.inject.*

/**
 * Maps [[Radio]]s to [[Antenna]] ports.
 *
 * @param config what the user can change.
 */
@Singleton
class AntennaMap @Inject()(config: Config) extends LazyLogging:
  private var antennaMap: Map[Antenna, Option[Radio]] =
    val builder = Map.newBuilder[Antenna, Option[Radio]]
    config.antennas.foreach { antenna =>
      builder.addOne(antenna, None)
    }
    builder.result()

  private val bandNames = config.bands.map(_.bandName)
  val errors: Seq[String] = for {
    antenna <- config.antennas
    bandName <- antenna.bandNames
    if !bandNames.contains(bandName)
  } yield {
    s"""Band: "$bandName" in antenna: "${antenna.name}" (${antenna.port}) is not defined!"""
  }

  def state: Seq[(Antenna, Option[Radio])] =
    antennaMap
      .toSeq
      .sortBy(_._1.port)

  def switch(antenna: Antenna, radio: Radio): Unit =
    val builder = Map.newBuilder[Antenna, Option[Radio]]

    antennaMap.foreach { (a, current) =>
      builder.addOne(
        if (a == antenna)
          antenna -> Option(radio)
        else
          antenna -> None
      )
    }
    antennaMap = builder.result()
