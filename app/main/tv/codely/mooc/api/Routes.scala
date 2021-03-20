package tv.codely.mooc.api

import scala.concurrent.duration._

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol._
import spray.json.JsValue

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

//  private val podcast = get {
//    path("podcasts")(container.podcastGetController.get())
//  }

  private val podcast = get {
    path("podcasts")(container.podcastGetController.get())
  } ~
    post {
      concat(
        path("podcasts") {
          jsonBody { body =>
            container.podcastPostController.post(
              body("id").convertTo[String],
              body("title").convertTo[String],
              body("duration_in_seconds").convertTo[Int].seconds,
              body("description").convertTo[String]
            )
          }
        },
        path("podcasts" / "rates") {
          jsonBody { body =>
            container.podcastPostRateController.post(
              body("id").convertTo[String],
              body("rate").convertTo[Int]
            )
          }
        }
      )
    }
//    post {
//      path("podcasts") {
//        jsonBody { body =>
//          container.podcastPostController.post(
//            body("id").convertTo[String],
//            body("title").convertTo[String],
//            body("duration_in_seconds").convertTo[Int].seconds,
//            body("description").convertTo[String]
//          )
//        }
//      }
//    } ~
//    post {
//      path("podcasts/rate") {
//        jsonBody { body =>
//          container.podcastPostRateController.post(
//            body("id").convertTo[String],
//            body("rate").convertTo[Int]
//          )
//        }
//      }
//    }

  val all: Route = status ~ user ~ video ~ podcast

  private def jsonBody(handler: Map[String, JsValue] => Route): Route =
    entity(as[JsValue])(json => handler(json.asJsObject.fields))
}