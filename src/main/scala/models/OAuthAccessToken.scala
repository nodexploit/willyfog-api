package models

import java.time.{LocalDateTime, ZoneId}
import java.util.Date
import com.twitter.finagle.exp.mysql.{LongValue, ResultSet, Row, StringValue}
import com.twitter.finagle.oauth2.{AccessToken, AuthInfo}
import com.twitter.util.Future
import controllers.MySql

case class OAuthAccessToken(
                           accessToken: String,
                           clientId: String,
                           userId: Option[Long],
                           expires: Long,
                           scope: Option[String],
                           createdAt: LocalDateTime
                           )

object OAuthAccessToken extends MySql {

  val tableName = "oauth_access_token"

  /**
    * TODO: check expiration
    *
    * @param authInfo
    * @return
    */
  def retrieve(authInfo: AuthInfo[User]): Future[Option[AccessToken]] =
    client.prepare(
      s"""
         |SELECT * FROM $tableName
         |WHERE client_id = ? AND user_id = ?
       """.stripMargin)(authInfo.clientId, authInfo.user.id)
    .map {
      case rs: ResultSet => rs.rows.map(toAccessToken).headOption
      case _ => None
    }

  def retrieve(token: String): Future[Option[AccessToken]] =
    client.prepare(
      s"""
         |SELECT * FROM $tableName
         |WHERE access_token = ?
       """.stripMargin)(token)
    .map {
      case rs: ResultSet => rs.rows.map(toAccessToken).headOption
      case _ => None
    }

  /**
    * TODO: handle scopes and expiration
    *
    * @param accessToken
    * @return
    */
  def retrieveAuthInfo(accessToken: AccessToken): Future[Option[AuthInfo[User]]] =
    client.prepare(
      s"""
         |SELECT * FROM $tableName oat
         |JOIN ${User.tableName} u ON oat.user_id = u.id
         |WHERE oat.access_token = ?
       """.stripMargin)(accessToken.token)
    .map {
      case rs: ResultSet => rs.rows.map(toAuthInfo).headOption
      case _ => None
    }

  def toAccessToken(row: Row): AccessToken = {
    val StringValue(accessToken) = row("access_token").get
    val scope = row("scope").collect{ case StringValue(s) => s }
    val LongValue(expires) = row("expires").get
    val StringValue(createdAt) = row("created_at").get
    val parsedCreatedAt = LocalDateTime.parse(createdAt)

    AccessToken(
      accessToken,
      None,
      scope,
      Some(expires),
      Date.from(parsedCreatedAt.atZone(ZoneId.systemDefault()).toInstant)
    )
  }

  /**
    * TODO: do we need here redirect_uri ?
    * @param row
    * @return
    */
  def toAuthInfo(row: Row): AuthInfo[User] = {
    val user = User.toEntity(row)
    val StringValue(clientId) = row("oat.client_id").get
    val scope = row("oat.scope").collect{ case StringValue(s) => s }

    AuthInfo(
      user,
      clientId,
      scope,
      None
    )
  }
}
