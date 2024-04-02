package antennsw

import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.TextField
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{GridPane, HBox}
import scalafx.scene.paint.Color.*
import scalafx.scene.paint.*
import scalafx.scene.text.Text

object ScalaFXHelloWorld extends JFXApp3 {

  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      //    initStyle(StageStyle.Unified)
      title = "Antenna Switch"
      scene = new Scene(400,400) {
        val tf= new TextField()
        fill = Color.rgb(38, 38, 38)
        content = List(
          tf
        )

      }
    }
  }
}
