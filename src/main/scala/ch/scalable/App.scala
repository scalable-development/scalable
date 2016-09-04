package ch.scalable

import japgolly.scalajs.react.{ReactComponentB, ReactDOM}
import org.scalajs.dom

import scala.scalajs.js
import japgolly.scalajs.react.vdom.prefix_<^._
import scala.scalajs.js.annotation.JSExport

object App extends js.JSApp {

  val Hello = ReactComponentB[Unit]("Hello")
    .render($ => <.div("Hello world"))
    .build

  @JSExport
  override def main() = {
    ReactDOM.render(Hello(), dom.document.getElementById("root"))
  }

}
