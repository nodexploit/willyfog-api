package controllers

import com.google.inject.Inject
import dao.AdminDAO
import play.api.libs.json.Json
import play.api.mvc._
import models.AdminFormatter._

import scala.concurrent.ExecutionContext.Implicits.global

class Application @Inject()(adminDAO: AdminDAO) extends Controller {

  def index = Action {
    Ok("Hello")
  }
}