package models

import slick.driver.MySQLDriver.api._

class Admin(tag: Tag) extends Table[(Int, String, String)](tag, "admin"){
  def id = column[Int]("id", O.PrimaryKey)
  def email = column[String]("email")
  def digest = column[String]("digest")

  def * = (id, email, digest)
}


