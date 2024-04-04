package antennsw

import antennsw.relay.usb.UsbCh341
import antennsw.relay.usb.UsbCh341.relayBoard
import antennsw.relay.{Relay, RelayBoard}
import com.fazecast.jSerialComm.SerialPort
import org.apache.commons.io.output.TeeOutputStream

import java.io.{OutputStream, PrintStream}

class UsbCh341Spec extends AntennaSpec {
  "UsbCh341" should {
    "define relay" when {
      "relay 1" in {
        UsbCh341.relay(1).toString mustBe ("1 on: 0xa0, 0x01, 0x01, 0xa2 off: 0xa0, 0x01, 0x00, 0xa1")
      }
    }

//    "relay Board" in {
//      relayBoard
//      relayBoard.name mustBe ("UsbCh341")
//      relayBoard.relays must have length (16)
//      val head = relayBoard.relays.head
//      head.number mustBe (1)
//      head.off.last mustBe (0xA1.toByte)
//      val last = relayBoard.relays.last
//      last.number mustBe (16)
//      last.on(0) mustBe (0xA0.toByte)
//      last.on(1) mustBe (16.toByte)
//      last.on(2) mustBe (1.toByte)
//      last.on.last mustBe (0xb1.toByte)
//    }
    "Operate relays" in {
      val fifoPort: SerialPort = SerialPort.getCommPort("tty.wchusbserial111220")
      val iso = fifoPort.openPort()
      for {
        rn <- 1 to 5
      } {
        flexRelay(fifoPort, rn)
      }
      fifoPort.closePort()

    }

    def flexRelay(fifoPort:SerialPort, rn: Int): Unit =
      throw new NotImplementedError() //todo
//      if(fifoPort.isOpen)
//        val relay: Relay = relayBoard.relays(rn - 1)
//        println(relay)
//        relay.sendOn(fifoPort)
//        Thread.sleep(1000)
//        relay.sendOff(fifoPort)
//      else
//        println("Port not open!")

    /**
     * The default baud rate is 9600 bps.
     * Commands for relay operation or status inquiry (in HEX):
     *
     * Open 1st channel USB: A0 01 01 A2
     * Close 1st channel USB: A0 01 00 A1
     * Open 2nd channel USB: A0 02 01 A3
     * Close 2nd channel USB: A0 02 00 A2
     * Open 3rd channel USB: A0 03 01 A4
     * Close 3rd channel USB: A0 03 00 A3
     * Open 4th channel USB: A0 04 01 A5
     * Close 4th channel USB: A0 04 00 A4
     * Status inquiry: FF
     * 2.2 Operation
     */
  }
}


