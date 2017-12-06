package tv.codely.scala_http_api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.user.domain.User
import tv.codely.scala_http_api.module.user.infrastructure.UserMarshaller._
import tv.codely.scala_http_api.module.video.domain.Video
import tv.codely.scala_http_api.module.video.infrastructure.VideoMarshaller._

import scala.concurrent.duration._

object Routes extends SprayJsonSupport with DefaultJsonProtocol {
  private val systemUsers = Seq(
    User(id = "deacd129-d419-4552-9bfc-0723c3c4f56a", name = "Edufasio"),
    User(id = "b62f767f-7160-4405-a4af-39ebb3635c17", name = "Edonisio")
  )

  private val systemVideos = Seq(
    Video(
      id = "3dfb19ee-260b-420a-b08c-ed58a7a07aee",
      title = "🎥 Scala FTW vol. 1",
      duration = 1.minute,
      category = "Screencast"
    ),
    Video(
      id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f97",
      title = "🔝 Interview with Odersky",
      duration = 30.minutes,
      category = "Interview"
    )
  )

  val all: Route = get {
    path("status") {
      complete(HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}"""))
    } ~
      path("ping") {
        complete(HttpEntity(ContentTypes.`application/json`, """{"data":"pong"}"""))
      } ~
      path("users") {
        complete(systemUsers)
      } ~
      path("videos") {
        complete(systemVideos)
      }
  }
}
