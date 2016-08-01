import sbt._
import play.sbt.Play.autoImport._

object Dependencies {

  val guice = "com.google.inject" % "guice" % "4.1.0"
  val gson = "com.google.code.gson" % "gson" % "2.7"
  val mysql = "mysql" % "mysql-connector-java" % "5.1.39"
  val sql2o = "org.sql2o" % "sql2o" % "1.5.4"
  val typeSafeConfig = "com.typesafe" % "config" % "1.3.0"

  val backendDeps = Seq(guice, gson, mysql, sql2o, typeSafeConfig, filters)
}