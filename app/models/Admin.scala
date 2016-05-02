package models

import java.sql.Timestamp

import play.api.libs.json._
import slick.driver.MySQLDriver.api._
import slick.profile.SqlProfile.ColumnOption.SqlType

case class Admin(id: Long, email: String, digest: String, created_at: Timestamp, updated_at: Timestamp)

class Admins(tag: Tag) extends Table[Admin](tag, "admin") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def email = column[String]("email")
  def digest = column[String]("digest")
  def created_at = column[Timestamp]("created_at", SqlType("TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"))
  def updated_at = column[Timestamp]("updated_at", SqlType("TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"))

  def * = (id, email, digest, created_at, updated_at) <> (Admin.tupled, Admin.unapply)
}

object AdminFormatter {
  implicit val adminFormat = Json.format[Admin]
}
