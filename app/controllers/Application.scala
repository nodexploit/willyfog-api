package controllers

import anorm.{Macro, RowParser}
import com.google.inject.Inject
import models.{User, Users}
import play.api.libs.json.Json
import play.api.mvc._
import models.Users._

class Application @Inject () (users: Users) extends Controller {

  def index = Action {
    val all: List[User] = users.all
    Ok(Json.toJson(all))
  }

}