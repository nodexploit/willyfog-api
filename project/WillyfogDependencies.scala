import sbt._

object WillyfogDependencies {
  // Versions
  lazy val finchVersion = "0.11.0-M1"

  // Libraries
  val scalaReflect = "org.scala-lang" % "scala-reflect" % "2.11.8"
  val finagleMysql = "com.twitter" %% "finagle-mysql" % "6.35.0"
  val finchCore = "com.github.finagle" %% "finch-core" % finchVersion
  val finchOAuth = "com.github.finagle" %% "finch-oauth2" % finchVersion

  // Project
  val backendDeps = Seq(scalaReflect, finagleMysql, finchCore, finchOAuth)
}