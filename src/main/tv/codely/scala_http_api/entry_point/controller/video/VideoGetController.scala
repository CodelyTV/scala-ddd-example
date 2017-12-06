package tv.codely.scala_http_api.entry_point.controller.video

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.module.video.application.VideosSearcher
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsonFormatMarshaller._

final class VideoGetController(searcher: VideosSearcher) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
