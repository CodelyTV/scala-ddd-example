package tv.codely.mooc.api

import tv.codely.mooc.api.controller.podcast.{PodcastGetController, PodcastPostController, PodcastPostRateController}
import tv.codely.mooc.api.controller.status.StatusGetController
import tv.codely.mooc.api.controller.user.{UserGetController, UserPostController}
import tv.codely.mooc.api.controller.video.{VideoGetController, VideoPostController}
import tv.codely.mooc.podcast.infrastructure.dependency_injection.PodcastModuleDependencyContainer
import tv.codely.mooc.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.mooc.video.infrastructure.dependency_injection.VideoModuleDependencyContainer

final class EntryPointDependencyContainer(
    userDependencies: UserModuleDependencyContainer,
    videoDependencies: VideoModuleDependencyContainer,
    podcastDependencies: PodcastModuleDependencyContainer
) {
  val statusGetController = new StatusGetController

  val userGetController  = new UserGetController(userDependencies.usersSearcher)
  val userPostController = new UserPostController(userDependencies.userRegistrar)

  val videoGetController  = new VideoGetController(videoDependencies.videosSearcher)
  val videoPostController = new VideoPostController(videoDependencies.videoCreator)

  val podcastGetController  = new PodcastGetController(podcastDependencies.podcastsSearcher)
  val podcastPostController = new PodcastPostController(podcastDependencies.podcastCreator)
  val podcastPostRateController = new PodcastPostRateController(podcastDependencies.podcastRater)
}