package antennsw.relay

import antennsw.Antenna
import antennsw.Band.r
import com.fazecast.jSerialComm.SerialPort
import com.typesafe.scalalogging.LazyLogging

import java.io.OutputStream
import scala.runtime.LazyLong

//trait Relay :
//  def switch(relay: Relay, antenna: Antenna):Unit

/**
 * Defines a relay
 *
 * @param number which relay. 1-N
 * @param on     bytes to turn the relay on.
 * @param off    bytes to thuen the relay off.
 */
case class Relay(number: Int, on: Array[Byte], off: Array[Byte]) extends LazyLogging:
  def a2s(array: Array[Byte]): String =
    array.map { b =>
        val byte = (b.toInt & 0xff).toByte
        f"0x$byte%02x"
      }
      .mkString(", ")

  override def toString: String =
    s"$number on: ${a2s(on)} off: ${a2s(off)}"

  def sendOn(fifoPort: SerialPort): Unit =
    logger.whenTraceEnabled {
      logger.trace("On: {}", a2s(on))
    }
    fifoPort.writeBytes(on, on.length)

  def sendOff(fifoPort: SerialPort): Unit =
    logger.whenTraceEnabled {
      logger.trace("Off: {}", a2s(off))
    }
    fifoPort.writeBytes(off, off.length)

object Relay:
  def crlf(bytes: Array[Byte]): Array[Byte] =
    bytes.prependedAll(Array(0x0d.toByte, 0x0a.toByte))

/**
 * Something that can send bytes to a [[RelayBoard]]
 */
trait RelayWriter:
  def write(array: Array[Byte]):Unit
  
