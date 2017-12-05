package tv.codely.scala_http_api

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

object Routes {
  private val systemUsers =
    """{
      |    "data":[
      |        {
      |            "id":"123",
      |            "name":"Edufasio"
      |        },
      |        {
      |            "id":"456",
      |            "name":"Edonisio"
      |        }
      |    ]
      |}""".stripMargin

  val all: Route = get {
    path("status") {
      complete(HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}"""))
    } ~
      path("ping") {
        complete(HttpEntity(ContentTypes.`application/json`, """{"data":"pong"}"""))
      } ~
      path("users") {
        complete(HttpEntity(ContentTypes.`application/json`, systemUsers))
      }
  }
}
