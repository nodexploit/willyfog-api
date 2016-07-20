package controllers

import io.finch.{Endpoint, _}
import shapeless.{:+:, CNil}
import models.User

object UserController extends MySql {

  case class UserParams(
                         name: String,
                         surname: String,
                         nif: String,
                         email: String,
                         digest: String
                       )

  val userParams: Endpoint[UserParams] = (
    param("name") :: param("surname") :: param("nif") :: param("email") :: param("digest")
    ).as[UserParams]

  val listUser: Endpoint[Seq[User]] = get("users") {
    Ok(User.all())
  }

  val showUser: Endpoint[User] = get("users" :: long) { id: Long =>
    User.find(id).map {
      case Some(user) => Ok(user)
      case _ => NotFound(new Exception("Record Not Found"))
    }
  }

  val createUser: Endpoint[User] = post("users" :: userParams) { p: UserParams =>
    (for {
      id    <- User.create(p.name, p.surname, p.nif, p.email, p.digest)
      user <- User.find(id)
    } yield user) map {
      case Some(user) => Created(user)
      case _ => NotFound(new Exception("Record Not Found"))
    }
  }

  val endpoints: Endpoint[Seq[User] :+: User :+: User :+: CNil] = listUser :+: showUser :+: createUser
}
