package antennsw

class BandsTest extends AntennaSpec {
  val text: String =
    """40: 7.-7.3
      |14: 14.-14.4
      |""".stripMargin

  "Bands" should {
    "toString" in {
      val bands: Bands = Bands(Seq(
        Band("40", 7_000_000, 7_300_000),
        Band("14", 14_000_000, 14_400_000),
      ))
      bands.toString mustBe text
    }
    "from lines" in {
      val lines: Seq[String] = text.split("\n")
      val bands = Bands.fromLines(lines)
      bands
    }

  }
}
