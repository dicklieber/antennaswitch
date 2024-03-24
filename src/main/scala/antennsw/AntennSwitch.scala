package antennsw

import com.google.inject.*
import com.typesafe.scalalogging.LazyLogging
import scalafx.application.JFXApp3
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.*
import scalafx.scene.paint.Color.*
import scalafx.scene.text.Text
import net.codingwell.scalaguice.InjectorExtensions._
import java.util

object AntennSwitch extends JFXApp3 with LazyLogging{
  private val module = new MyModule()
  val injector: Injector = Guice.createInjector(module)
  private val allBindings: util.Map[Key[_], Binding[_]] = injector.getAllBindings
  def start(): Unit =
    stage = new JFXApp3.PrimaryStage {
      //    initStyle(StageStyle.Unified)
      title = "WA9NNN Antenna Switch"
      scene = new Scene {
        fill = Color.rgb(38, 38, 38)
        content = new HBox {
          padding = Insets(50, 80, 50, 80)
          children = Seq(
            new Text {
              text = "Antenna Switch"
              style = "-fx-font: normal bold 100pt sans-serif"
              fill = new LinearGradient(
                endX = 0,
                stops = Stops(Red, DarkRed))
            },
          )
        }
      }
    }

}
