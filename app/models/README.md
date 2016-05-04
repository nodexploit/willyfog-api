# willyfog - Models

In willyfog, our models have this structure:

* `case class` that defines a database row. This is the closest idea to an "entity" (thinking in a ORM way).
* `class` that extends `models.AbstractModel` and implement the required methods to query the database (kind of DAO).
* `object` that implements the corresponding formatter to parse a model into JSON or vice versa and the
[anorm `Macro`](https://www.playframework.com/documentation/2.5.x/ScalaAnorm#generated-parsers).

## Naming convention

For example, and `Admin` model would be modeled as this:

* A `case class` must be named as singular: `Admin`
* A `class` (table definition) must be named as plural: `Admins`
* A formatter `object` must be in singular and camelcase: `AdminFormatter`
* The table name must be in plural and lowercase: `admins`

`app/models/Admin.scala`

```scala
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
```
