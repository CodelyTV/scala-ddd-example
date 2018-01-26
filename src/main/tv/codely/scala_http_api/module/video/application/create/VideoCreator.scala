package tv.codely.scala_http_api.module.video.application.create

import tv.codely.scala_http_api.module.shared.bus.domain.MessagePublisher
import tv.codely.scala_http_api.module.shared.user.domain.UserId
import tv.codely.scala_http_api.module.video.domain._

final class VideoCreator(repository: VideoRepository, publisher: MessagePublisher) {
  def create(
      id: VideoId,
      title: VideoTitle,
      duration: VideoDuration,
      category: VideoCategory,
      creatorId: UserId
  ): Unit = {
    val video = Video(id, title, duration, category, creatorId)

    repository.save(video)

    publisher.publish(VideoCreated(video))
  }
}
