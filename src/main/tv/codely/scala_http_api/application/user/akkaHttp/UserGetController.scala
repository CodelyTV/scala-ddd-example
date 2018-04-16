package tv.codely.scala_http_api.application.akkaHttp.controller.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.application.user.api.UsersSearcher
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserJsonFormatMarshaller._
import scala.concurrent.Future

final class UserGetController(searcher: UsersSearcher[Future]) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
