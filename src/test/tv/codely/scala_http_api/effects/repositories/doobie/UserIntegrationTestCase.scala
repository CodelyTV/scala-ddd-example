package tv.codely.scala_http_api.effects.repositories.doobie

import tv.codely.scala_http_api.effects.repositories.doobie.DoobieMySqlUserRepository

trait UserIntegrationTestCase extends IntegrationTestCase {
  protected val repository = DoobieMySqlUserRepository[cats.effect.IO]
}
