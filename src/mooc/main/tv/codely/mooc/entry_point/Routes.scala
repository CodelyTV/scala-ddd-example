package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol._
import spray.json.JsValue

import scala.concurrent.duration._

final class Routes(container: EntryPointDependencyContainer) {
  private val status = get {
    path("status")(container.statusGetController.get())
  }

  private val user = get {
    path("users")(container.userGetController.get())
  } ~
    post {
      path("users") {
        jsonBody { body =>
          container.userPostController.post(
            body("id").convertTo[String],
            body("name").convertTo[String]
          )
        }
      }
    }

  private val video = get {
    path("videos")(container.videoGetController.get())
  } ~
    post {
      path("videos") {
        jsonBody { body =>
          container.videoPostController.post(
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
