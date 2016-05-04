package models

import java.sql.Timestamp

import anorm.{Macro, RowParser}
import com.google.inject.Inject
import play.api.db.DBApi
import play.api.libs.json.Json

case class Admin(
                  id: Int,
                  email: String,
                  digest: String,
                  created_at: Timestamp,
                  updated_at: Timestamp,
                  deleted_at: Option[Timestamp]
                )

class Admins @Inject() (override val dBApi: DBApi) extends AbstractModel[Admin] {}

object AdminFormatter {
  implicit val parser: RowParser[Admin] = Macro.namedParser[Admin]
  implicit val format = Json.format[Admin]
}
