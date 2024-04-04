package antennsw.relay.usb

import antennsw.SwitchState
import antennsw.relay.{Relay, RelayBoard}

case class RelayBoardUsb(name:String, relays:Seq[Relay]) extends RelayBoard{

  def switch(switchState: SwitchState): Unit =
    throw new NotImplementedError() //todo

 //todo
}
