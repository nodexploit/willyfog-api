package controllers

import com.google.inject.Inject
import dao.AdminDAO
import play.api.libs.json.Json
import play.api.mvc._
import models.AdminFormatter._

import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(adminDAO: AdminDAO) extends Controller {

  def index = Action.async {
    val queries = for {
      all <- adminDAO.all()
      count <- adminDAO.count()
    } yield (all, count)

    queries map { case (c,a) =>
      Ok(Json.toJson(c.head))
    }
  }
}