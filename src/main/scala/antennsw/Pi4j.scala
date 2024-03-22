package antennsw

import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.{DigitalOutput, DigitalOutputBuilder, DigitalState}
import com.typesafe.scalalogging.LazyLogging

import scala.jdk.CollectionConverters.*

object Pi4j extends App with LazyLogging{
  private val PIN_BUTTON: Int = 24 // PIN 18 = BCM 24

  private val PIN_LED: Int = 22 // PIN 15 = BCM 22

  val pi4j: Context = Pi4J.newAutoContext

  private val builder: DigitalOutputBuilder = DigitalOutput.newBuilder(pi4j)
  val digOut0: DigitalOutput = builder.id("p0")
    .name("Pin 0")
    .address(17)
    .shutdown(DigitalState.LOW)
    .initial(DigitalState.LOW)
    .provider("pigpio-digital-output")
    .build()

  private val output: DigitalOutput = digOut0.high()
  logger.info(output.toString)


}
