package antennsw

import com.typesafe.scalalogging.LazyLogging

import java.io.{PrintWriter, StringWriter}

case class Bands(bands: Seq[Band]) extends LazyLogging:
  override def toString: String =
    val stringWriter = new StringWriter(1000)
    val writer: PrintWriter = new PrintWriter(stringWriter)
    bands.foreach { band =>
      writer.println(band.toString)
    }
    writer.close()
    stringWriter.toString

object Bands:
  def fromLines(lines: Seq[String]): Bands = {
    val seq: Seq[Band] = lines.map(line => Band(line))
    new Bands(
      seq
    )
  }



