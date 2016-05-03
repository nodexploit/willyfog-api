package models

import anorm.{Macro, RowParser}
import com.google.inject.Inject
import play.api.db.DBApi
import play.api.libs.json.Json

case class User(id: Int, name: String)

class Users @Inject() (override val dBApi: DBApi) extends AbstractModel[User] {
  override val tableName: String = "users"
}

object Users {
  implicit val parser: RowParser[User] = Macro.namedParser[User]
  implicit val format = Json.format[User]
}
