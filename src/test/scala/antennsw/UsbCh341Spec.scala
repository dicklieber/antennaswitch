package antennsw

import antennsw.Relay.crlf
import antennsw.UsbCh341.usbrelay
import com.fazecast.jSerialComm.SerialPort

import java.io.OutputStream

class UsbCh341Spec extends AntennaSpec {

  "UsbCh341" should {
    "usbrelay" in {
      val usbrelay: Seq[Relay] = UsbCh341.usbrelay
      usbrelay
    }


    "all On" in {
      val commPorts: Array[SerialPort] = SerialPort.getCommPorts
      commPorts.foreach { serialPort =>
        println(s"${serialPort.getSystemPortName} ${serialPort.toString}")
      }
      //    val maybePort: Option[SerialPort] = commPorts.find(_.getSystemPortName == "tty.usbserial-A4002DNF")
      val maybePort: Option[SerialPort] = commPorts.find(_.getSystemPortName == "cu.wchusbserial111240")
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

      val relay = usbrelay(0)
      println(relay)
      outputStream.write(crlf(relay.on))
      outputStream.flush()
      Thread.sleep(1000)
      outputStream.write(crlf(relay.off))
      outputStream.flush()
      outputStream.close()
      fifoPort.closePort()
      print('.')

    }
  }
}
