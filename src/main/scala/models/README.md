# willyfog - Models

In willyfog, our models have this structure:

* `case class` that defines a database row. This is the closest idea to an "entity" (thinking in a ORM way).
* `object` that implements the corresponding CRUD methods of the Model and the 
mapper that convert a row to an object.

## Naming convention

For example, and `Admin` model would be modeled as this:

* A `case class` must be named as singular: `Admin`
* A `object` must be in singular and camelcase: `Admin`
* The table name must be in plural and lowercase: `admins`

`src/main/scala/models/Admin.scala`

```scala
package models

import com.twitter.finagle.exp.mysql._
import com.twitter.util.Future

case class Admin(id: Long, email: String, digest: String)

object Admin {

  def all()(implicit client: Client): Future[Seq[Admin]] = client.select("SELECT * FROM admin")(convertToEntity)

  def find(id: Long)(implicit client: Client): Future[Option[Admin]] =
    client.prepare("SELECT * FROM admin WHERE id = ?")(id) map { result =>
      result.asInstanceOf[ResultSet].rows.map(convertToEntity).headOption
    }

  def create(email: String, digest: String)(implicit client: Client): Future[Long] =
    client.prepare("INSERT INTO admin (email, digest) VALUES(?, ?)")(email, digest) map { result =>
      result.asInstanceOf[OK].insertId
    }

  def convertToEntity(row: Row): Admin = {
    val LongValue(id)         = row("id").get
    val StringValue(email)    = row("email").get
    val StringValue(digest)   = row("digest").get

    Admin(id, email, digest)
  }
}
```
