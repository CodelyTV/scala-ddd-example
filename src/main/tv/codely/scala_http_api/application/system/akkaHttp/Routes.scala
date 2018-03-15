package tv.codely.scala_http_api.application.akkaHttp.controller

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol._
import spray.json.JsValue
import scala.concurrent.duration._
import tv.codely.scala_http_api.application.api.System
import tv.codely.scala_http_api.application.akkaHttp.controller.status.StatusGetController
import tv.codely.scala_http_api.application.akkaHttp.controller.user.{UserGetController, UserPostController}
import tv.codely.scala_http_api.application.akkaHttp.controller.video.{VideoGetController, VideoPostController}

final class Routes(implicit 
  system: System[Future],
  executionContext: ExecutionContext
){
  val statusGetController = new StatusGetController

  val userGetController  = new UserGetController(system.UsersSearcher)
  val userPostController = new UserPostController(system.UserRegister)

  val videoGetController  = new VideoGetController(system.VideosSearcher)
  val videoPostController = new VideoPostController(system.VideoCreator)

  private val status = get {
    path("status")(statusGetController.get())
  }

  private val user = get {
    path("users")(userGetController.get())
  } ~
    post {
      path("users") {
        jsonBody { body =>
          userPostController.post(
            body("id").convertTo[String],
            body("name").convertTo[String]
          )
        }
      }
    }

  private val video = get {
    path("videos")(videoGetController.get())
  } ~
    post {
      path("videos") {
        jsonBody { body =>
          videoPostController.post(
            body("id").convertTo[String],
            body("title").convertTo[String],
            body("duration_in_seconds").convertTo[Int].seconds,
            body("category").convertTo[String],
            body("creator_id").convertTo[String]
          )
        }
      }
    }

  val all: Route = status ~ user ~ video

  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))
}
