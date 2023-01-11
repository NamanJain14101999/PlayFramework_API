package controllers

import javax.inject._
import play.api._
import play.api.http.Writeable.wByteArray
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
import play.api.libs.ws._
import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
@Singleton
class HomeController @Inject()(ws: WSClient,val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
//    Redirect("/connector2")
    Await.result(ws.url("http://localhost:9000/connector1").get().map { response =>
//      val playload = Json.toJson(response.json)
////      val njson = playload \\ "fact"
////      println(njson)
//      println((playload \ "fact").as[String])
      Ok(response.body)
    },Duration.Inf)
//    Redirect("/connector1",303).flashing("success" -> "The item was created")
//    Ok(views.html.index())
  }
}
