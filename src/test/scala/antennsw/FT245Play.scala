
package antennsw

import com.fazecast.jSerialComm.SerialPort
import com.typesafe.scalalogging.LazyLogging

import java.io.{FileOutputStream, OutputStream}
import scala.math.Ordered.orderingToOrdered

object FT245Play extends App with LazyLogging:
  private val commPorts: Array[SerialPort] = SerialPort.getCommPorts
  commPorts.foreach { serialPort =>
    logger.info(s"${serialPort.getSystemPortName} ${serialPort.toString}")
  }
  private val maybePort: Option[SerialPort] = commPorts.find(_.toString.contains("FIFO"))
  private val fifoPort: SerialPort = maybePort.get
  private val systemPortName: String = fifoPort.getSystemPortName
  private val toString1: String = fifoPort.toString
  private val descriptivePortName: String = fifoPort.getDescriptivePortName
  private val systemPortPath: String = fifoPort.getSystemPortPath
  private val portLocation: String = fifoPort.getPortLocation
  private val baudRate: Int = fifoPort.getBaudRate
  fifoPort.openPort()
  val iso = fifoPort.isOpen

  private val array:  Array[Byte] = Array[Byte](-1.toByte, 1, 1)
  fifoPort.writeBytes(array, 3)
  fifoPort.closePort()
//  private val outputStream: OutputStream = fifoPort.getOutputStream
//
//  outputStream.write(0xff)
//  outputStream.write(2)
//  outputStream.write(1)
//  outputStream.flush()
//  outputStream.close()
//  fifoPort.closePort()
//  print('.')
//  Thread.sleep(500)


object DevPlay extends App:
  private val array:  Array[Byte] = Array[Byte](-1.toByte, 1, 1)
  val stream = new FileOutputStream("/dev/cu.usbserial-A4002DNF")
  stream.write(array)
  stream.close()

  
