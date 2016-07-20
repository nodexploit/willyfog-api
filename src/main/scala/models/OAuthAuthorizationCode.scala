package models

import java.time.LocalDateTime
import com.twitter.finagle.exp.mysql.{ResultSet, Row, StringValue}
import com.twitter.finagle.oauth2.AuthInfo
import com.twitter.util.Future
import controllers.MySql

case class OAuthAuthorizationCode(
                                 authorizationCode: String,
                                 clientId: String,
                                 userId: Option[Long],
                                 redirectUri: Option[String],
                                 expires: Long,
                                 scope: Option[String],
                                 idToken: String,
                                 createdAt: LocalDateTime
                                 )

object OAuthAuthorizationCode extends MySql {

  val tableName = "oauth_authorization_code"

  def retrieveAuthInfo(code: String): Future[Option[AuthInfo[User]]] =
    client.prepare(
      s"""
         |SELECT * from $tableName oc
         |JOIN ${User.tableName} u ON oac.user_id = u.id
         |WHERE oc.authorization_code = ?
       """.stripMargin)(code)
    .map {
      case rs: ResultSet => rs.rows.map(toAuthInfo).headOption
      case _ => None
    }

  /**
    * TODO: Handle possible empty user_id (???). Comment future handling
    * @param row
    * @return
    */
  def toAuthInfo(row: Row): AuthInfo[User] = {
    val user = User.toEntity(row)
    val StringValue(clientId) = row("oc.client_id").get
    val scope = row("oc.scope").collect{ case StringValue(s) => s }
    val redirectUri = row("oc.redirect_uri").collect{ case StringValue(s) => s }

    AuthInfo(
      user,
      clientId,
      scope,
      redirectUri
    )
  }
}
