package oauth

import com.twitter.finagle.oauth2.DataHandler
import com.twitter.util.Future
import models.Admin

class WillyDataHandler extends DataHandler[Admin] {

  override def validateClient(clientId: String, clientSecret: String, grantType: String): Future[Boolean] = {

  }

  def findUser(request: AuthorizationRequest): Future[Option[User]] = ???

  def createAccessToken(authInfo: AuthInfo[User]): Future[AccessToken] = ???

  def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AccessToken]] = ???

  def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AccessToken] = ???

  def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] = ???

  def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = ???

  def deleteAuthCode(code: String): Future[Unit] = ???

  def findAccessToken(token: String): Future[Option[AccessToken]] = ???

  def findAuthInfoByAccessToken(accessToken: AccessToken): Future[Option[AuthInfo[User]]] = ???
}
