package tv.codely.mooc.api.controller.video

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.mooc.video.application.search.ShortestVideoSearcher
import tv.codely.mooc.video.infrastructure.marshaller.VideoJsonFormatMarshaller._

final class VideoGetShortestController(searcher: ShortestVideoSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.shortest())
}
