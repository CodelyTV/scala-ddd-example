package tv.codely.scala_http_api.entry_point.controller.status

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete

final class StatusGetController {
  def get(): StandardRoute = complete(HttpEntity(ContentTypes.`application/json`, """{"status":"ok"}"""))
}
