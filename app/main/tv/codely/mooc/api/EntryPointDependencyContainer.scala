package tv.codely.mooc.api

import tv.codely.mooc.api.controller.status.StatusGetController
import tv.codely.mooc.api.controller.user.{UserGetController, UserPostController}
import tv.codely.mooc.api.controller.video.{VideoGetController, VideoPostController, VideoGetShortestController}
import tv.codely.mooc.user.infrastructure.dependency_injection.UserModuleDependencyContainer
import tv.codely.mooc.video.infrastructure.dependency_injection.VideoModuleDependencyContainer

final class EntryPointDependencyContainer(
    userDependencies: UserModuleDependencyContainer,
    videoDependencies: VideoModuleDependencyContainer
) {
  val statusGetController = new StatusGetController

  val userGetController  = new UserGetController(userDependencies.usersSearcher)
  val userPostController = new UserPostController(userDependencies.userRegistrar)

  val videoGetController  = new VideoGetController(videoDependencies.videosSearcher)
  val videoPostController = new VideoPostController(videoDependencies.videoCreator)

  val videoGetShortestController  = new VideoGetShortestController(videoDependencies.shortestVideoSearcher)
}
