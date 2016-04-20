package models

import play.api.libs.json._

/**
  * Model of a Pagination
  *
  * @param elements : Seq of elements of type T
  * @param page : Current page
  * @param pageSize : Amount of elements per page
  * @param total : Total of elements in the database
  * @tparam T : Type of Model to be paginated
  */
case class Pagination[T](elements: Seq[T], page: Int, total: Int, pageSize: Int = 10)

object PaginationFormatter {
  implicit def searchResultsWrites[T](implicit fmt: Writes[T]): Writes[Pagination[T]] = new Writes[Pagination[T]] {
    def writes(ts: Pagination[T]) = JsObject(Seq(
      "total" -> JsNumber(ts.total),
      "pageSize" -> JsNumber(ts.pageSize),
      "page" -> JsNumber(ts.page),
      "elements" -> JsArray(ts.elements.map(Json.toJson(_)))
    ))
  }
}