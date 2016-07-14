import WillyfogDependencies._


lazy val root = (project in file(".")).
  settings(
    name := "willyfog",
    version := "1.0",
    scalaVersion := "2.11.8"
  ).
  settings(
    libraryDependencies ++= backendDeps
  )