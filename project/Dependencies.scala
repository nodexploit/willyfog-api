import sbt._

object Dependencies {
  // Versions
  lazy val finchVersion = "0.11.0-M2"

  // Libraries
  val scalaReflect = "org.scala-lang" % "scala-reflect" % "2.11.8"
  val finagleMysql = "com.twitter" %% "finagle-mysql" % "6.35.0"
  val finchCore = "com.github.finagle" %% "finch-core" % finchVersion
  val finchOAuth = "com.github.finagle" %% "finch-oauth2" % finchVersion
  val finchCirce = "com.github.finagle" %% "finch-circe" % finchVersion
  val circeGeneric = "io.circe" %% "circe-generic" % "0.5.0-M2"

  // Project
  val backendDeps = Seq(scalaReflect, finagleMysql, finchCore, finchOAuth, finchCirce, circeGeneric)
}