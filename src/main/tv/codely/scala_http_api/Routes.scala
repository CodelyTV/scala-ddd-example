package tv.codely.scala_http_api

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.course.domain.Course
import tv.codely.scala_http_api.course.infrastructure.CourseMarshaller._
import tv.codely.scala_http_api.user.domain.User
import tv.codely.scala_http_api.user.infrastructure.UserMarshaller._
import tv.codely.scala_http_api.video.domain.Video
import tv.codely.scala_http_api.video.infrastructure.VideoMarshaller._

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
      category = "Screencast",
      course = Course(
        id = "0dee034b-2eb2-4f3f-b75f-90b6f785d78c",
        title = "Introducción a Scala"
      )
    ),
    Video(
      id = "7341b1fc-3d80-4f6a-bcde-4fef86b01f97",
      title = "🔝 Interview with Odersky",
      duration = 30.minutes,
      category = "Interview",
      course = Course(
        id = "eabb585b-4527-49e7-9529-4aa877bec55d",
        title = "API Http con Scala Akka"
      )
    ),
    Video(
      id = "cf296b95-63c3-48a2-b3fd-44a2e3ced29c",
      title = "4. JSON Parsing",
      duration = 16.minutes,
      category = "Lesson",
      course = Course(
        id = "eabb585b-4527-49e7-9529-4aa877bec55d",
        title = "API Http con Scala Akka"
      )
    )
  )

  private val systemCourses = Seq(
    Course(
      id = "0dee034b-2eb2-4f3f-b75f-90b6f785d78c",
      title = "Introducción a Scala"
    ),
    Course(
      id = "eabb585b-4527-49e7-9529-4aa877bec55d",
      title = "API Http con Scala Akka"
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
      } ~
      path("courses") {
        complete(systemCourses)
      }
  }
}
