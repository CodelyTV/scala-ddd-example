package tv.codely.mooc.api.controller.video

import scala.concurrent.duration.Duration

import akka.http.scaladsl.model.HttpResponse
import akka.http.scaladsl.model.StatusCodes.NoContent
import akka.http.scaladsl.server.Directives.complete
import akka.http.scaladsl.server.StandardRoute
import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.video.application.create.VideoCreator
import tv.codely.mooc.video.domain.{VideoCategory, VideoDuration, VideoId, VideoTitle}

final class VideoPostController(creator: VideoCreator) {
  def post(id: String, title: String, duration: Duration, category: String, creatorId: String): StandardRoute = {
    creator.create(VideoId(id), VideoTitle(title), VideoDuration(duration), VideoCategory(category), UserId(creatorId))

    complete(HttpResponse(NoContent))
  }
}
