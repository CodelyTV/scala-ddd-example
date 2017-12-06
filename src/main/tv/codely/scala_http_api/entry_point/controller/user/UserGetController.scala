package tv.codely.scala_http_api.entry_point.controller.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.user.application.UsersSearcher
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserJsonFormatMarshaller._

final class UserGetController(searcher: UsersSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = {
    complete(searcher.searchAll())
  }
}
