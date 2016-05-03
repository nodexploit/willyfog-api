package models

import play.api.db.DBApi
import anorm._

abstract class AbstractModel[T] {
  val dBApi: DBApi
  val tableName: String

  def all(implicit parser: RowParser[T]): List[T] = {
    dBApi.database("default").withConnection { implicit c =>
      SQL"SELECT * FROM #$tableName".as(parser.*)
    }
  }
}
