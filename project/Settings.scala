import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import sbt.{Def, _}

object Settings {

  val name = "scalable"
  val version = "0.1.0-SNAPSHOT"

  object versions {
    val scala = "2.11.8"

    // libraryDependencies
    val scalaJsReact = "0.11.1"
    val diode = "1.0.0"

    // jsDependencies
    val react = "15.2.1"
  }

  val libraryDependencies = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalaJsReact,
    "me.chrons" %%% "diode" % versions.diode,
    "me.chrons" %%% "diode-react" % versions.diode
  ))

  val jsDependencies = Def.setting(Seq(
    "org.webjars.bower" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
    "org.webjars.bower" % "react" % versions.react / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM"
  ))

}