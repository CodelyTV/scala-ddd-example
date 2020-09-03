package tv.codely.mooc.api.controller.video

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import spray.json.DefaultJsonProtocol
import tv.codely.mooc.video.application.search.LongestVideoSearcher
import tv.codely.mooc.video.infrastructure.marshaller.VideoJsonFormatMarshaller._

final class LongestVideoGetController(searcher: LongestVideoSearcher)
    extends SprayJsonSupport
    with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.longestVideo())
}
