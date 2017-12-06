package tv.codely.scala_http_api.module.video.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.video.application.VideosSearcher
import tv.codely.scala_http_api.module.video.infrastructure.repository.InMemoryVideoRepository

final class VideoModuleDependencyContainer {
  private val repository = new InMemoryVideoRepository

  val videosSearcher = new VideosSearcher(repository)
}
