package antennsw

import antennsw.relay.{Relay, UsbCh341}
import com.fazecast.jSerialComm.SerialPort

import java.io.OutputStream

class UsbCh341Spec extends AntennaSpec {
  "UsbCh341" should {
    "define relay" when{
      "relay 1" in {
        UsbCh341.relay(1).toString mustBe("1 on: 0xa0, 0x01, 0x01, 0xa2 off: 0xa0, 0x01, 0x00, 0xa1")
      }
    }



/*    "1 on off" in {
      val commPorts: Array[SerialPort] = SerialPort.getCommPorts
      commPorts.foreach { serialPort =>
        println(s"${serialPort.getSystemPortName} ${serialPort.toString}")
      }
      //    val maybePort: Option[SerialPort] = commPorts.find(_.getSystemPortName == "tty.usbserial-A4002DNF")
      val maybePort: Option[SerialPort] = commPorts.find(_.getSystemPortName == "cu.usbserial-111240")
      //    val maybePort: Option[SerialPort] = commPorts.find(_.toString.contains("FIFO"))
      val fifoPort: SerialPort = maybePort.get

      val systemPortName: String = fifoPort.getSystemPortName
      val toString1: String = fifoPort.toString
      val descriptivePortName: String = fifoPort.getDescriptivePortName
      val systemPortPath: String = fifoPort.getSystemPortPath
      val portLocation: String = fifoPort.getPortLocation
      val baudRate: Int = fifoPort.getBaudRate
      fifoPort.openPort()
      val iso = fifoPort.isOpen

      val outputStream: OutputStream = fifoPort.getOutputStream

      var relay = usbrelay(1)
      println(relay)
      outputStream.write(relay.on)
      outputStream.flush()
      Thread.sleep(1000)
      outputStream.write(relay.off)
      outputStream.flush()
      outputStream.close()
      fifoPort.closePort()
      print('.')
      relay = usbrelay(2)
      println(relay)
      outputStream.write(relay.on)
      outputStream.flush()
      Thread.sleep(1000)
      outputStream.write(relay.off)
      outputStream.flush()
      outputStream.close()
      fifoPort.closePort()
      print('.')
    }
*/  }
}
