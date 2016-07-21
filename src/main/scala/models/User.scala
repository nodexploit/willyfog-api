package models

import java.time.LocalDateTime

import com.twitter.finagle.exp.mysql._
import com.twitter.util.Future
import controllers.MySql
import io.circe.{Encoder, Json}

case class User(
                 id: Long,
                 name: String,
                 surname: String,
                 nif: String,
                 email: String,
                 digest: String,
                 createdAt: LocalDateTime,
                 updatedAt: LocalDateTime
               )

object User extends MySql {

  val tableName = "user"

  implicit val encoder = Encoder.instance[User] { u: User =>
    Json.obj(
      ("id", Json.fromLong(u.id)),
      ("name", Json.fromString(u.name)),
      ("surname", Json.fromString(u.surname)),
      ("nif", Json.fromString(u.nif)),
      ("email", Json.fromString(u.email)),
      ("created_at", Json.fromString(u.createdAt.toString)),
      ("updated_at", Json.fromString(u.updatedAt.toString))
    )
  }

  def find(userId: Long): Future[Option[User]] =
    client.prepare(
      s"""
         |SELECT * FROM $tableName u
         |WHERE id = ?
       """.stripMargin)(userId)
    .map {
      case rs: ResultSet => rs.rows.map(toEntity).headOption
      case _ => None
    }

  def all(): Future[Seq[User]] =
    client.select(
      s"""
         |SELECT * FROM $tableName
       """.stripMargin)(toEntity)

  def create(name: String, surname: String, nif: String, email: String, digest: String): Future[Long] =
    client.prepare(
      s"""
         |INSERT INTO $tableName (name, surname, nif, email, digest) VALUES (?, ?, ?, ?, ?)
       """.stripMargin)(name, surname, nif, email, digest)
    .map { result =>
      result.asInstanceOf[OK].insertId
    }

  /**
    * TODO: manage scopes
    * @param clientId
    * @param clientSecret
    * @param scope
    * @return
    */
  def findByClient(clientId: String, clientSecret: String, scope: Option[String]): Future[Option[User]] =
    client.prepare(
      s"""
         |SELECT u.* FROM ${OAuthClient.tableName} oc
         |JOIN $tableName u ON oc.user_id = u.id
         |WHERE oc.client_id = ? AND oc.client_secret = ?
       """.stripMargin)(clientId, clientSecret)
    .map {
      case rs: ResultSet => rs.rows.map(toEntity).headOption
      case _ => None
    }

  def toEntity(row: Row): User = {
    val LongValue(id) = row("id").get
    val StringValue(name) = row("name").get
    val StringValue(surname) = row("surname").get
    val StringValue(nif) = row("nif").get
    val StringValue(email) = row("email").get
    val StringValue(digest) = row("digest").get
    val TimestampValue(createdAt) = row("created_at").get
    val TimestampValue(updatedAt) = row("updated_at").get

    User(
      id,
      name,
      surname,
      nif,
      email,
      digest,
      createdAt.toLocalDateTime,
      updatedAt.toLocalDateTime
    )
  }
}
