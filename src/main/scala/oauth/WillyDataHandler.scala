package oauth

import com.twitter.finagle.oauth2.{AccessToken, AuthInfo, DataHandler}
import com.twitter.util.Future
import models.{OAuthAccessToken, OAuthAuthorizationCode, OAuthClient, User}

object WillyDataHandler extends DataHandler[User] {

  override def validateClient(clientId: String, clientSecret: String, grantType: String): Future[Boolean] =
    OAuthClient.validate(clientId, clientSecret, grantType)

  override def getStoredAccessToken(authInfo: AuthInfo[User]): Future[Option[AccessToken]] =
    OAuthAccessToken.retrieve(authInfo)

  override def findAuthInfoByCode(code: String): Future[Option[AuthInfo[User]]] =
    OAuthAuthorizationCode.retrieveAuthInfo(code)

  override def findClientUser(clientId: String, clientSecret: String, scope: Option[String]): Future[Option[User]] =
    User.findByClient(clientId, clientSecret, scope)

  override def findAccessToken(token: String): Future[Option[AccessToken]] =
    OAuthAccessToken.retrieve(token)

  override def findAuthInfoByAccessToken(accessToken: AccessToken): Future[Option[AuthInfo[User]]] =
    OAuthAccessToken.retrieveAuthInfo(accessToken)

  /**
    * Only for Resource Owner Password Credentials Grant
    * @param username
    * @param password
    * @return
    */
  override def findUser(username: String, password: String): Future[Option[User]] = ???

  /**
    * We use other OAuth2 provider, so this method remains unimplemented
    * @param authInfo
    * @return
    */
  override def createAccessToken(authInfo: AuthInfo[User]): Future[AccessToken] = ???

  /**
    * Only for Refresh Token Grant
    * @param refreshToken
    * @return
    */
  override def findAuthInfoByRefreshToken(refreshToken: String): Future[Option[AuthInfo[User]]] = ???

  /**
    * We user other OAuth2 provider, so this remains unimplemented
    * @param authInfo
    * @param refreshToken
    * @return
    */
  override def refreshAccessToken(authInfo: AuthInfo[User], refreshToken: String): Future[AccessToken] = ???
}