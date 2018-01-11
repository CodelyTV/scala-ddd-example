package tv.codely.scala_http_api.module.video

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.video.domain.VideoRepository
import tv.codely.scala_http_api.module.video.infrastructure.dependency_injection.VideoModuleDependencyContainer

protected[video] trait VideoIntegrationTestCase extends IntegrationTestCase {
  private val container = new VideoModuleDependencyContainer(doobieDbConnection)

  protected val repository: VideoRepository = container.repository
}
