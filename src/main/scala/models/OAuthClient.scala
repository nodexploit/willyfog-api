package models

import com.twitter.finagle.exp.mysql.ResultSet
import com.twitter.util.Future
import controllers.MySql
import org.joda.time.DateTime

case class OAuthClient(
                        clientId: String,
                        clientSecret: Option[String],
                        redirectUri: Option[String],
                        grantTypes: Option[String],
                        scope: Option[String],
                        userId: Option[String],
                        createdAt: DateTime,
                        updatedAt: DateTime
                      )

object OAuthClient extends MySql {

  val tableName = "oauth_clients"

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