package antennsw.relay

import antennsw.Antenna

trait Relay :
  def switch(relay: Relay, antenna: Antenna):Unit


      
  