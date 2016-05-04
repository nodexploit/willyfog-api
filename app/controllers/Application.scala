package controllers

import com.google.inject.Inject
import models.{Admin, Admins}
import play.api.libs.json.Json
import play.api.mvc._
import models.Admin._

class Application @Inject () (admins: Admins) extends Controller {

  def index = Action {
    val all: List[Admin] = admins.all
    Ok(Json.toJson(all))
  }

}