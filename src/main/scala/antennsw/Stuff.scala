package antennsw

import com.typesafe.scalalogging.LazyLogging
import jakarta.inject.{Inject, Singleton}
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.{BorderPane, HBox, VBox}
@Singleton
class Stuff @Inject()(bands: Bands) extends LazyLogging:
// List for serial port messages
// Find Band
// Determine antenna
// Operate replay.
 logger.info("Hello Stuff")

 private val borderPane = new BorderPane {
  
  padding = Insets(25)
  private val buttons = new HBox() {
   alignment = Pos.BottomCenter
   spacing = 8
   padding = Insets(10)
   children = List(Button("Button1"), Button("Button2"))
  }
 }
