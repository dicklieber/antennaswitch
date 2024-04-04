package antennsw.relay

import antennsw.relay.Relay

abstract class RelayBoard extends AutoCloseable:
  def open: Unit =
    throw new NotImplementedError() //todo

  def close: Unit =
    throw new NotImplementedError() //todo
    
  def write(bytes: Array[Byte]): Array[Byte]

  val name: String
  val relays: Seq[Relay]
