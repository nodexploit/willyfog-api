package models

import play.api.libs.json.Json
import slick.driver.MySQLDriver.api._

case class Admin(id: Int, name: String)

class Admins(tag: Tag) extends Table[Admin](tag, "admin") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name) <> (Admin.tupled, Admin.unapply)
}

object AdminFormatter {
  implicit val adminFormat = Json.format[Admin]
}
