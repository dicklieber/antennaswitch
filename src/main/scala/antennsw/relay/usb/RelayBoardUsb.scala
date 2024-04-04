package antennsw.relay.usb

import antennsw.relay.{Relay, RelayBoard}

case class RelayBoardUsb(name:String, relays:Seq[Relay]) extends RelayBoard{


  def write(bytes: Array[Byte]): Array[Byte] =
    throw new NotImplementedError() //todo
}
