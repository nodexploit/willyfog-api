package controllers

import com.twitter.finagle.exp.Mysql

trait MySql {

  implicit val client = Mysql.client
    .withCredentials("root", "root")
    .withDatabase("willyfog")
    .newRichClient("127.0.0.1:3306")

}
