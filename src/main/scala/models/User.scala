package models

import java.time.LocalDateTime
import com.twitter.finagle.exp.mysql.{LongValue, ResultSet, Row, StringValue}
import com.twitter.util.Future
import controllers.MySql

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

  def find(userId: String): Future[Option[User]] =
    client.prepare(
      s"""
         |SELECT * FROM $tableName
         |WHERE id = ?
       """.stripMargin)(userId)
    .map {
      case rs: ResultSet => rs.rows.map(toEntity).headOption
      case _ => None
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
    val LongValue(id) = row("u.id").get
    val StringValue(name) = row("u.name").get
    val StringValue(surname) = row("u.surname").get
    val StringValue(nif) = row("u.nif").get
    val StringValue(email) = row("u.email").get
    val StringValue(digest) = row("u.digest").get
    val StringValue(createdAt) = row("u.created_at").get
    val StringValue(updatedAt) = row("u.updated_at").get

    User(
      id,
      name,
      surname,
      nif,
      email,
      digest,
      LocalDateTime.parse(createdAt),
      LocalDateTime.parse(updatedAt)
    )
  }
}
