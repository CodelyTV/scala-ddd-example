package tv.codely.scala_http_api.application.repo_publisher.video

import tv.codely.scala_http_api.application.video.api._
import tv.codely.scala_http_api.effects.bus.api.MessagePublisher
import tv.codely.scala_http_api.application.user.api.UserId
import tv.codely.scala_http_api.effects.repositories.api._
import cats.Apply, cats.syntax.apply._

final case class VideoCreatorRepoPublisher[P[_]]()(implicit
  repository: VideoRepository[P], 
  publisher: MessagePublisher[P],
  Ap: Apply[P])
extends VideoCreator[P]{
  def create(
      id: VideoId,
      title: VideoTitle,
      duration: VideoDuration,
      category: VideoCategory,
      creatorId: UserId
  ): P[Unit] = {
    val video = Video(id, title, duration, category, creatorId)

    repository.save(video) *>
    publisher.publish(VideoCreated(video))
  }
}
