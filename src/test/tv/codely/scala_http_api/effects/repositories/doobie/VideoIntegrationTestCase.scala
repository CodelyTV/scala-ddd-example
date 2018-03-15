package tv.codely.scala_http_api.effects.repositories.doobie

import tv.codely.scala_http_api.effects.repositories.doobie.DoobieMySqlVideoRepository

trait VideoIntegrationTestCase extends IntegrationTestCase {
  protected val repository = DoobieMySqlVideoRepository[cats.effect.IO]
}
