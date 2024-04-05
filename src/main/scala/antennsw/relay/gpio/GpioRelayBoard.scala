package antennsw.relay.gpio

import antennsw.SwitchState
import antennsw.relay.RelayBoard
import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.platform.{Platform, Platforms}
import com.pi4j.plugin.mock.provider.gpio.analog.{MockAnalogInputProvider, MockAnalogOutputProvider}
import com.pi4j.plugin.mock.provider.gpio.digital.MockDigitalOutputProvider
import org.checkerframework.checker.units.qual.s

import java.lang.System.console
import java.util
import scala.collection.mutable
import scala.jdk.CollectionConverters.*

class GpioRelayBoard extends RelayBoard:
  val name: String = "text"
  def switch(switchState: SwitchState): Unit =
    throw new NotImplementedError() //todo

  val pi4j: Context = Pi4J.newAutoContextAllowMocks
  private val platforms: Platforms = pi4j.platforms()
  private val m: mutable.Map[String, Platform] = platforms.all().asScala
  println(m)
