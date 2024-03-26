package antennsw

import antennsw.Band.r
import antennsw.Radio.RadioPort
import jakarta.inject.Singleton

import scala.collection.concurrent.TrieMap
import scala.collection.mutable

@Singleton
class AntennaManager(config: Config, antennaMap: AntennaMap):

  private var mode: Mode = Mode.Automatic

  def mode(mode: Mode): Unit =
    this.mode = mode

  /**
   * Will change mode to [[Mode.Manual]]
   *
   * @param antenna
   */
  def selectAntenna(antenna: Int): Unit =
    throw new NotImplementedError() //todo

  /**
   *
   * @param hz from radio
   */
  def newFrequency(hz: Int, radio: Radio): Unit =
    if (mode == Mode.Automatic)
      for {
        band: Band <- config.bands.find(_.contains(hz))
        antenna <- config.antennas.find(_.bandNames.contains(band.bandName))
      } {
        antennaMap.switch(
          antenna = antenna,
          radio = radio
        )
      }
