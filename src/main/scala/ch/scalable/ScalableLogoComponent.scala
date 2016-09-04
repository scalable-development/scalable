package ch.scalable

import japgolly.scalajs.react.extra.{EventListener, OnUnmount}
import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, ReactComponentB, Ref}
import org.scalajs.dom
import org.scalajs.dom.MouseEvent
import org.scalajs.dom.raw.SVGSVGElement

import scala.scalajs.js

object ScalableLogoComponent {

  val outerDim = 100
  val outerRadius = radius(outerDim)
  val innerPadding = 10
  val minInnerDim = 20

  def radius(dim: Double) = 0.1 * dim

  case class State(innerDim: Double = 20)

  val svgRef = Ref[SVGSVGElement]("svgRef")

  class Backend($: BackendScope[Unit, State]) extends OnUnmount {

    def handleWindowScroll(e: MouseEvent) =
      $.setState {
        val scrollTop = if (js.isUndefined(e.srcElement))  {
          dom.document.documentElement.scrollTop // Firefox
        } else {
          dom.document.body.scrollTop // Chrome & Safari
        }
        val innerDim = (scrollTop / dom.window.innerHeight * 60) + minInnerDim
        val constrainedInnerDim = Math.min(outerDim - 2 * innerPadding, innerDim)
        State(constrainedInnerDim)
      }

    def render(s: State) = {

      val outerBox = <.svg.rect(
        ^.svg.fill := "#580058",
        ^.svg.x := 0, ^.svg.y := 0,
        ^.svg.width := outerDim, ^.svg.height := outerDim,
        ^.svg.rx := outerRadius, ^.svg.ry := outerRadius
      )

      val innerRadius = radius(s.innerDim)

      val innerBox = <.svg.rect(
        ^.svg.fill := "darkorange",
        ^.svg.x := innerPadding, ^.svg.y := outerDim - innerPadding - s.innerDim,
        ^.svg.width := s.innerDim, ^.svg.height := s.innerDim,
        ^.svg.rx := innerRadius, ^.svg.ry := innerRadius
      )

      <.svg.svg(
        ^.ref := svgRef,
        ^.svg.viewBox := s"0 0 100 100",
        outerBox,
        innerBox
      )
    }
  }

  private val component = ReactComponentB[Unit](ScalableLogoComponent.getClass.getSimpleName)
    .initialState(State())
    .renderBackend[Backend]
    .configure(
      EventListener[MouseEvent].install("scroll", _.backend.handleWindowScroll, _ => dom.window)
    )
    .build

  def apply() = component()

}
