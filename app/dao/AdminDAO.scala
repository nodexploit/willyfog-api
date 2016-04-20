package dao

import com.google.inject.Inject
import models.{Admin, Admins}
import play.api.db.slick.DatabaseConfigProvider
import slick.lifted.TableQuery

class AdminDAO @Inject()(override protected val dbConfigProvider: DatabaseConfigProvider) extends AbstractDAO[Admins, Admin]{
  override val tableQuery = TableQuery[Admins]
}