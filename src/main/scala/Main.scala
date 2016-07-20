import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.oauth2.AuthInfo
import com.twitter.finagle.{Http, Service}
import com.twitter.util.Await
import io.finch.circe._
import io.circe.generic.auto._
import io.finch._
import models.User
import oauth.WillyDataHandler
import com.twitter.finagle.oauth2._
import controllers.UserController
import io.finch.oauth2._

object Main {

  val auth: Endpoint[AuthInfo[User]] = authorize(WillyDataHandler)

  val homeEndpoint: Endpoint[User] = get(/ :: auth) { ai: AuthInfo[User] =>
    Ok(ai.user)
  }

  val api: Service[Request, Response] = (homeEndpoint :+: UserController.endpoints).toServiceAs[Application.Json]

  def main(args: Array[String]): Unit = {
    Await.ready(Http.serve(":7000", api))
  }
}
