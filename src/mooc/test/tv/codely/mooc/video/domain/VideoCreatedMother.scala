package tv.codely.mooc.video.domain

import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.user.domain.UserIdMother

object VideoCreatedMother {
  def apply(
      id: VideoId = VideoIdMother.random,
      title: VideoTitle = VideoTitleMother.random,
      duration: VideoDuration = VideoDurationMother.random,
      category: VideoCategory = VideoCategoryMother.random,
      creatorId: UserId = UserIdMother.random
  ): VideoCreated = VideoCreated(id, title, duration, category, creatorId)

  def apply(video: Video): VideoCreated = apply(video.id, video.title, video.duration, video.category, video.creatorId)

  def random: VideoCreated = apply()
}
