import com.twitter.finagle.exp.Mysql
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.Await
import controllers.AdminController
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

object Main {

  val homeEndpoint: Endpoint[String] = get(/) {
    Ok("Hello world!")
  }

  val api: Service[Request, Response] = (homeEndpoint :+: AdminController.endpoints).toServiceAs[Application.Json]

  def main(args: Array[String]): Unit = {
    Await.ready(Http.serve(":8080", api))
  }
}
