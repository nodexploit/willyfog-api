package controllers

import io.finch.{Endpoint, _}
import shapeless.{:+:, CNil}
import models.Admin

object AdminController extends MySql{

  case class AdminParams(email: String, digest: String)

  val adminParams: Endpoint[AdminParams] = (param("email") :: param("digest")).as[AdminParams]

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

  val endpoints: Endpoint[Seq[Admin] :+: Admin :+: Admin :+: CNil] = listAdmin :+: showAdmin :+: createAdmin
}
