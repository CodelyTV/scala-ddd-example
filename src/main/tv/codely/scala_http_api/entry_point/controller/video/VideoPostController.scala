package tv.codely.scala_http_api.entry_point.controller.video

import scala.concurrent.duration.Duration

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.StandardRoute
import tv.codely.scala_http_api.module.video.application.create.VideoCreator
import akka.http.scaladsl.server.Directives.complete
import tv.codely.scala_http_api.module.video.domain.{VideoCategory, VideoDuration, VideoId, VideoTitle}

final class VideoPostController(creator: VideoCreator) {
  def post(id: String, title: String, duration: Duration, category: String): StandardRoute = {
    creator.create(VideoId(id), VideoTitle(title), VideoDuration(duration), VideoCategory(category))

    complete(HttpResponse(NoContent))
  }
}
