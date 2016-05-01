package dao

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.Future

abstract class AbstractDAO[T <: Table[R], R] extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  val tableQuery : TableQuery[T]

  def all(nElements: Int = 10): Future[Seq[R]] = dbConfig.db.run(tableQuery.take(nElements).result)

  def count(): Future[Int] = dbConfig.db.run(tableQuery.length.result)
}
