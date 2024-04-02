package antennsw.relay

import antennsw.Antenna
import antennsw.Band.r

//trait Relay :
//  def switch(relay: Relay, antenna: Antenna):Unit

/**
 * Defines a relay
 *
 * @param number which relay. 1-N
 * @param on     bytes to turn the relay on.
 * @param off    bytes to thuen the relay off.
 */
case class Relay(number: Int, on: Array[Byte], off: Array[Byte]):
  def a2s(array: Array[Byte]): String =
    array.map { b =>
        val byte = (b.toInt & 0xff).toByte
        f"0x$byte%02x"
      }
      .mkString(", ")

  override def toString: String =
    s"$number on: ${a2s(on)} off: ${a2s(off)}"

object Relay:
  def crlf(bytes: Array[Byte]): Array[Byte] =
    bytes.prependedAll(Array(0x0d.toByte, 0x0a.toByte))
