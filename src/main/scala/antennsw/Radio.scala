package antennsw

import antennsw.Radio.RadioPort

/**
 * 
 * @param name user defined.
 * @param port usually 'M' or 'S' main or sub
 */
case class Radio(name:String, port:RadioPort)

object Radio:
  type RadioPort = String