package tv.codely.scala_http_api.entry_point

import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import spray.json.JsValue
import scala.concurrent.duration._

final class Routes(container: EntryPointDependencyContainer) {
  val all: Route =
    get {
      path("status")(container.statusGetController.get()) ~
      path("users")(container.userGetController.get()) ~
      path("videos")(container.videoGetController.get())
    } ~
    post {
      path("videos") {
        entity(as[JsValue]) { json =>
          val bodyParams = json.asJsObject.fields

          container.videoPostController.post(
            bodyParams("id").convertTo[String],
            bodyParams("title").convertTo[String],
            bodyParams("duration_in_seconds").convertTo[Int].seconds,
            bodyParams("category").convertTo[String]
          )
        }
      }
    }
}
