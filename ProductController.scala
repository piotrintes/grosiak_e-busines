package controllers

import javax.inject._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def product = Action {
    Ok();
  }
  def addProduct = Action {
    Ok();
  }
  def addProduct_ = Action {
    Ok();
  }
  def editProduct = Action {
    Ok();
  }
  def editProduct_ = Action {
    Ok();
  }
  def addToCart = Action {
    Ok();
  }
  def buyCart = Action {
    Ok();
  }
  def buyProduct = Action {
    Ok();
  }
}
