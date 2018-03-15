package tv.codely.scala_http_api.application.video.api

import tv.codely.scala_http_api.application.user.api.UserId

trait VideoCreator[P[_]]{

  def create(
    id: VideoId,
    title: VideoTitle,
    duration: VideoDuration,
    category: VideoCategory,
    creatorId: UserId): P[Unit]
}
