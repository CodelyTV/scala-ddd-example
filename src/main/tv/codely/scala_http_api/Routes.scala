package tv.codely.scala_http_api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import tv.codely.scala_http_api.domain.User
import tv.codely.scala_http_api.infrastructure.UserMarshaller._

object Routes {
  private val systemUsers = Seq(
    User("123", "Edufasio"),
    User("456", "Edonisio"),
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
      }
  }
}
