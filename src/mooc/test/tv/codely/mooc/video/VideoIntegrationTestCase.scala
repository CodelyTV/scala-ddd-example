package tv.codely.mooc.video

import tv.codely.mooc.video.domain.VideoRepository
import tv.codely.mooc.video.infrastructure.dependency_injection.VideoModuleDependencyContainer
import tv.codely.shared.infrastructure.integration.IntegrationTestCase

protected[video] trait VideoIntegrationTestCase extends IntegrationTestCase {
  private val container = new VideoModuleDependencyContainer(doobieDbConnection, messagePublisher)

  protected val repository: VideoRepository = container.repository
}
