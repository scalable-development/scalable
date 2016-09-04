lazy val scalable = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := Settings.name,
    scalaVersion := Settings.versions.scala,
    version := Settings.version,
    scalacOptions += "-feature"
  )

libraryDependencies ++= Settings.libraryDependencies.value

jsDependencies ++= Settings.jsDependencies.value

skip in packageJSDependencies := false

persistLauncher in Compile := true
persistLauncher in Test := false

lazy val deploySite = taskKey[Unit]("Generates fully optimized JS and deploys to 'docs' folder")
deploySite := {
  val sourceDir = target.value / "scala-2.11"
  val targetDir = file("docs")
  Seq(
    "scalable-jsdeps.js",
    "scalable-launcher.js",
    "scalable-opt.js"
  ) foreach { f =>
    IO.copyFile(sourceDir / f, targetDir / f)
  }

}
deploySite <<= deploySite.dependsOn(fullOptJS in Compile)