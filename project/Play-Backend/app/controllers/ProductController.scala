package controllers

import javax.inject._
import models._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n._
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

class ProductController @Inject()(productsRepo: ProductRepository, categoryRepo: CategoryRepository,
                                  cc: MessagesControllerComponents
                                )(implicit ec: ExecutionContext)
  extends MessagesAbstractController(cc) {

  /**
   * The mapping for the person form.
   */
  val productForm: Form[CreateProductForm] = Form {
    mapping(
      "name" -> nonEmptyText,
      "category" -> nonEmptyText,
      "quantity" -> number,
      "price" -> number,
      "imageFilename" -> nonEmptyText,
      "descriptionFilename" -> nonEmptyText,
    )(CreateProductForm.apply)(CreateProductForm.unapply)
  }

  /**
   * The index action.
   */
  def index = Action.async { implicit request =>
    val categories = categoryRepo.list()
    categories.map(cat => Ok(views.html.index(productForm,cat)))

      /*
      .onComplete{
      case Success(categories) => Ok(views.html.index(productForm,categories))
      case Failure(t) => print("")
    }*/
  }

  /**
   * The add person action.
   *
   * This is asynchronous, since we're invoking the asynchronous methods on PersonRepository.
   */
/*
  def addProduct = Action.async { implicit request =>
    Ok(views.html.addproduct())
  }
*/

  def addProduct = Action.async { implicit request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle succes.
    var a:Seq[Category] = Seq[Category]()
    val categories = categoryRepo.list().onComplete{
      case Success(cat) => a= cat
      case Failure(_) => print("fail")
    }

    productForm.bindFromRequest.fold(
      // The error function. We return the index page with the error form, which will render the errors.
      // We also wrap the result in a successful future, since this action is synchronous, but we're required to return
      // a future because the person creation function returns a future.
      errorForm => {
        Future.successful(
            Ok(views.html.index(errorForm,a))
          )
      },
      // There were no errors in the from, so create the person.
      success = product => {
        productsRepo.create(product.name, product.category, product.quantity, product.price, product.imageFilename, product.descriptionFilename).map { _ =>
          // If successful, we simply redirect to the index page.
          Redirect(routes.ProductController.index).flashing("success" -> "product.created")
        }
      }
    )
  }


  /**
   * A REST endpoint that gets all the people as JSON.
   */
  def getProducts = Action.async { implicit request =>
    productsRepo.list().map { products =>
      Ok(Json.toJson(products))
    }
  }

  def getByCategory(id: Integer) = Action.async { implicit  request =>
    productsRepo.getByCategory(id).map { products =>
      Ok(Json.toJson(products))
    }
  }

  def getByCategories = Action.async { implicit  request =>
    val categories: List[Int] = List(1,2,3)

    productsRepo.getByCategories(categories).map { products =>
      Ok(Json.toJson(products))
    }
  }

  def handlePost = Action.async { implicit request =>
    val name = request.body.asJson.get("name").as[String]
    val category = request.body.asJson.get("description").as[String]

    productsRepo.create(name,category,1,12,"/","/").map { product =>
      Ok(Json.toJson(product))
    }
  }



}

/**
 * The create person form.
 *
 * Generally for forms, you should define separate objects to your models, since forms very often need to present data
 * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
 * that is generated once it's created.
 */
case class CreateProductForm(name: String, category: String, quantity: Int, price: Int, imageFilename: String, descriptionFilename: String)
