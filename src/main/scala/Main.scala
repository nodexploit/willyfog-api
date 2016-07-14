import com.twitter.finagle.exp.Mysql
import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import models.Admin

object Main {

  implicit val client = Mysql.client
    .withCredentials("root", "root")
    .withDatabase("willyfog_db")
    .newRichClient("127.0.0.1:3306")

  case class AdminParams(email: String, digest: String)

  val adminParams: Endpoint[AdminParams] = (param("email") :: param("digest")).as[AdminParams]

  val homeEndpoint: Endpoint[String] = get(/) {
    Ok("Hello world!")
  }

  val listAdmin: Endpoint[Seq[Admin]] = get("admins") {
    Ok(Admin.all())
  }

  val showAdmin: Endpoint[Admin] = get("admins" :: long) { id: Long =>
      Admin.find(id).map {
        case Some(admin) => Ok(admin)
        case _ => NotFound(new Exception("Record Not Found"))
      }
  }

  val createAdmin: Endpoint[Admin] = post("admins" :: adminParams) { p: AdminParams =>
    (for {
      id    <- Admin.create(p.email, p.digest)
      admin <- Admin.find(id)
    } yield admin) map {
      case Some(admin) => Created(admin)
      case _ => NotFound(new Exception("Record Not Found"))
    }
  }

  val api: Service[Request, Response] = (homeEndpoint :+: listAdmin :+: showAdmin :+: createAdmin).toServiceAs[Application.Json]

  def main(args: Array[String]): Unit = {
    Await.ready(Http.serve(":8080", api))
  }
}
