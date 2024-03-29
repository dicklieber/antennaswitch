
package antennsw

import com.fazecast.jSerialComm.SerialPort
import com.typesafe.scalalogging.LazyLogging

import java.io.{FileOutputStream, OutputStream}
import scala.math.Ordered.orderingToOrdered

object FT245Play extends App with LazyLogging:

  for(i <- 0 to 255){
    doOne(i.toByte)
    Thread.sleep(1000)
  }

  def doOne(byte:Byte): Unit =
    val commPorts: Array[SerialPort] = SerialPort.getCommPorts
    commPorts.foreach { serialPort =>
      logger.info(s"${serialPort.getSystemPortName} ${serialPort.toString}")
    }
//    val maybePort: Option[SerialPort] = commPorts.find(_.getSystemPortName == "tty.usbserial-A4002DNF")
    val maybePort: Option[SerialPort] = commPorts.find(_.getSystemPortName == "cu.usbserial-A4002DNF")
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

    val array: Array[Byte] = Array[Byte](0xFF.toByte, 0x01, 0x01) //A0 01 01
    val outputStream: OutputStream = fifoPort.getOutputStream

    outputStream.write(0xff)
    outputStream.write(2)
    outputStream.write(byte)
    outputStream.flush()
    outputStream.close()
    fifoPort.closePort()
    print('.')

  object DevPlay extends App:
    private val array: Array[Byte] = Array[Byte](0xfe.toByte, 1, 1)
    val stream = new FileOutputStream("/dev/cu.usbserial-111240")
    //  val stream = new FileOutputStream("/dev/cu.usbserial-A4002DNF")
    //  val stream = new FileOutputStream("/dev/ttyUSB0")
    stream.write(array)
    stream.close()

  
