package tv.codely.mooc.api.controller.user

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.mooc.user.application.search.UsersSearcher
import tv.codely.mooc.user.infrastructure.marshaller.UserJsonFormatMarshaller._

final class UserGetController(searcher: UsersSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
