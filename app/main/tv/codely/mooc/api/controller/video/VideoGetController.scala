package tv.codely.mooc.api.controller.video

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.mooc.video.application.search.VideosSearcher
import tv.codely.mooc.video.infrastructure.marshaller.VideoJsonFormatMarshaller._

final class VideoGetController(searcher: VideosSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
