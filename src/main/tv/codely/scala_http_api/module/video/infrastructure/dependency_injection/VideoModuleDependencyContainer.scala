package tv.codely.scala_http_api.module.video.infrastructure.dependency_injection

import tv.codely.scala_http_api.module.video.application.VideosSearcher

final class VideoModuleDependencyContainer {
  val videosSearcher = new VideosSearcher
}
