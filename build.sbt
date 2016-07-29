import Dependencies._

lazy val commonSettings = Seq(
  name := "willyfog-api",
  organization := "popokis",
  version := "1.0",
  isSnapshot := true,
  scalaVersion := "2.11.8"
)

lazy val root = (project in file(".")).
  enablePlugins(PlayJava).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= backendDeps
  )
    