package antennsw

import jakarta.inject.Singleton

import java.io.InputStream
import java.net.URL
import scala.io.BufferedSource
import scala.util.Using.*

@Singleton
class BandManager:
  private val stream: InputStream = getClass.getResourceAsStream("/bands.config.default")
  private var _bands: Seq[Band] =
    resource(new BufferedSource(stream)) { (bs: BufferedSource) =>
      (for {
        line <- bs.getLines()
        trimmed = line.trim
        if !trimmed.startsWith("#")
        band <- Band(trimmed).toOption
      } yield {
        band
      }).toSeq
    }

  def bands: Seq[Band] = _bands



