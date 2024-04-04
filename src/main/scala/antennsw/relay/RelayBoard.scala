package antennsw.relay

import antennsw.{Radio, SwitchState}
import antennsw.relay.Relay

abstract class RelayBoard extends AutoCloseable:
  def open: Unit =
    throw new NotImplementedError() //todo

  def close: Unit =
    throw new NotImplementedError() //todo
    
  def switch(switchState: SwitchState):Unit

  val name: String
