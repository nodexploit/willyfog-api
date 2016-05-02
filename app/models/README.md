# willyfog - Models

In willyfog, our models have this structure:

* `case class` that defines a database row. This is the closest idea to an "entity" (thinking in a ORM way).
* `class` that maps the entity to the database. Kind of a table definition. Don't confuse yourself with migrations.
* `object` that implements the corresponding formatter to parse a model into JSON or vice versa.

## Naming convention

For example, and `Admin` model would be modeled as this:

* A `case class` must be named as singular: `Admin`
* A `class` (table definition) must be named as plural: `Admins`
* A formatter `object` must be in singular and camelcase: `AdminFormatter`
* The table name must be in singular and lowercase: `admin`

`app/models/Admin.scala`

```scala
case class Admin(id: Int, name: String)

class Admins(tag: Tag) extends Table[Admin](tag, "admin") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name) <> (Admin.tupled, Admin.unapply)
}

object AdminFormatter {
  implicit val adminFormat = Json.format[Admin]
}
```

We assume that you use [Slick](https://github.com/playframework/play-slick) as your preferred ORM.