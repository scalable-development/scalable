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

relativeSourceMaps := true