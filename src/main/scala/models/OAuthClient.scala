package models

import java.time.LocalDateTime
import com.twitter.finagle.exp.mysql.ResultSet
import com.twitter.util.Future
import controllers.MySql

case class OAuthClient(
                        clientId: String,
                        clientSecret: Option[String],
                        redirectUri: Option[String],
                        grantTypes: Option[String],
                        scope: Option[String],
                        userId: Option[Long],
                        createdAt: LocalDateTime,
                        updatedAt: LocalDateTime
                      )

object OAuthClient extends MySql{

  val tableName = "oauth_client"

  def validate(clientId: String, clientSecret: String, grantType: String): Future[Boolean] =
    client.prepare(
      s"""
         |SELECT * FROM $tableName
         |WHERE client_id = ? AND client_secret = ? AND grant_types = ?
       """.stripMargin)(clientId, clientSecret, grantType)
    .map {
      case rs: ResultSet => rs.rows.nonEmpty
      case _ => false
    }
}