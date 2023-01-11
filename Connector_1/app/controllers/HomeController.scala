package controllers

//import akka.http.impl.util.JavaAccessors.HttpRequest

import javax.inject._
import play.api._
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.api.libs.ws._

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import org.json4s._
import org.json4s.native.JsonMethods._

import scala.collection.mutable.ListBuffer
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
    var dataa :String = ""
    var mainData: String = ""
    var mainJson :Any = ""
    Await.result(ws.url("https://catfact.ninja/fact").get().map { response =>
      if (response.status == 200) {
        val json = response.json
//        println(json)
        val playload = Json.toJson(response.json)
//        val temp = JSON.parseFull(response.json)
//        println(temp )
//        println("hello")
//        println(playload \\ "fact")t
          println(playload)
//          println(Json.parse(playload))
          val data = playload \\ "fact"
          dataa = data.toString()

        val njson = playload \\ "fact"
        println(njson)

        println(playload("fact"))
        mainJson  = playload
        println((playload \ "fact").as[String])
        mainData = (playload \ "fact").as[String]
//        val test = parse(response.json)
//        println(test)
//        println(response.body)
        // do something with the json
      } else {
        // handle error
      }
    }.recover {
      case e: Exception => println(e)
      // handle exception
    },Duration.Inf)
//    val response = Await.result(ws.url("https://catfact.ninja/fact").get(), Duration.Inf).map{
//      res =>
//        if(res.status ==200)
//          {
//            val pay =  Json.toJson(res.json)
//
//
//          }
//    }

    println("----")
    println(mainJson)
//    println(mainData)
    Ok(mainData)
//    Ok(views.html.index())
  }
}
