package ch.scalable

import japgolly.scalajs.react.ReactDOM
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

object App extends js.JSApp {

  @JSExport
  override def main() = {
    ReactDOM.render(ScalableLogoComponent(), dom.document.getElementById("logo"))
  }

}
