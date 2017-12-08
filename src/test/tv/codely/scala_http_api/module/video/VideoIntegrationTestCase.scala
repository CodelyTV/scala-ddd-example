package tv.codely.scala_http_api.module.video

import tv.codely.scala_http_api.module.IntegrationTestCase
import tv.codely.scala_http_api.module.video.infrastructure.dependency_injection.VideoModuleDependencyContainer
import tv.codely.scala_http_api.module.video.infrastructure.repository.InMemoryVideoRepository

protected[video] trait VideoIntegrationTestCase extends IntegrationTestCase {
  private val container = new VideoModuleDependencyContainer(doobieDbConnection)

  protected val repository: InMemoryVideoRepository = container.repository
}
