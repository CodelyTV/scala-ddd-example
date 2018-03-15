package tv.codely.scala_http_api.application.akkaHttp.controller.video

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.StandardRoute
import akka.http.scaladsl.server.Directives.complete
import spray.json.DefaultJsonProtocol
import tv.codely.scala_http_api.application.video.api.VideosSearcher
import tv.codely.scala_http_api.module.video.infrastructure.marshaller.VideoJsonFormatMarshaller._
import scala.concurrent.Future

final class VideoGetController(searcher: VideosSearcher[Future]) extends SprayJsonSupport with DefaultJsonProtocol {
  def get(): StandardRoute = complete(searcher.all())
}
