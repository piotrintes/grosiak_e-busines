package models

import play.api.libs.json._

case class Product(id: Long, name: String, category: String, quantity: Int, price: Int, imageFilename: String, descriptionFilename: String)
object Product {
  implicit val productFormat = Json.format[Product]
}
