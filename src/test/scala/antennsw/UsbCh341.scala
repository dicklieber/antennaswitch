package antennsw

import scalafx.scene.effect.Light.Point

extension (sc: StringContext)
  def x(args: String*): Array[Byte] = {
    sc
    val parts = sc.parts
    val hex: Iterator[String] = parts.head.grouped(2)
    val bytes: Iterator[Byte] = hex.map { sx =>
      val value: Int = Integer.parseInt(sx, 16)
      value.toByte
    }
    val result = bytes.toArray
    result
  }

case class Relay(off: Array[Byte], on: Array[Byte]):
  def a2s(array: Array[Byte]):String =
    array.map{b =>
        val r: String = (b.toInt & 0xff)
          .toHexString
          .padTo(2, '0')
        val rr: String = "0x" + r
        rr
    }
      .mkString(", ")
  override def toString: String =
    s"on: ${a2s(on)} off: ${a2s(off)}"

object Relay:
  def crlf(bytes:Array[Byte]):Array[Byte]=
      bytes.prependedAll( Array(0x0d.toByte, 0x0a.toByte))

object UsbCh341 {

  val usbrelay: Seq[Relay] = Seq(
    //      FE0100200000FF
    Relay(x"FE0100200000FF", x"FE0100000010F1"), // status & status return
    //      FE0500000000FD
    Relay(x"FE0500000000FD", x"FE050000FF00FE"), // channel-1
    Relay(x"FE0500010000FC", x"FE050001FF00FD"), // channel-2
    Relay(x"FE0500020000FB", x"FE050002FF00FC"), // channel-3
    Relay(x"FE0500030000FA", x"FE050003FF00FB"), // channel-4
    Relay(x"FE0500040000F9", x"FE050004FF00FA"), // channel-5
    Relay(x"FE0500050000F8", x"FE050005FF00F9"), // channel-6
    Relay(x"FE0500060000F7", x"FE050006FF00F8"), // channel-7
    Relay(x"FE0500070000F6", x"FE050007FF00F7"), // channel-8
    Relay(x"FE0500080000F5", x"FE050008FF00F6"), // channel-9
    Relay(x"FE0500090000F4", x"FE050009FF00F5"), // channel-10
    Relay(x"FE05000A0000F3", x"FE05000AFF00F4"), // channel-11
    Relay(x"FE05000B0000F2", x"FE05000BFF00F3"), // channel-12
    Relay(x"FE05000C0000F1", x"FE05000CFF00F2"), // channel-13
    Relay(x"FE05000D0000F0", x"FE05000DFF00F1"), // channel-14
    Relay(x"FE05000E0000FF", x"FE05000EFF00F0"), // channel-15
    Relay(x"FE05000F0000FE", x"FE05000FFF00FF"), // channel-16
    Relay(x"FE0F00000010020000E1", x"FE0F0000001002FFFFE3") // all channels
  )
}
