package tv.codely.scala_http_api
package services.mock.user

import tv.codely.scala_http_api.effects.bus.mock.MessagePublisherMock
import tv.codely.scala_http_api.application.mock.UnitTestCase
import tv.codely.scala_http_api.application.stubs.user.{UserRegisteredStub, UserStub}
import tv.codely.scala_http_api.effects.repositories.mock.UserRepositoryMock
import tv.codely.scala_http_api.application.repo_publisher.user.UserRegisterRepoPublisher
import scala.concurrent.{Future, ExecutionContext}, ExecutionContext.Implicits.global
import cats.instances.future._

final class UserRegisterShould extends UnitTestCase with UserRepositoryMock with MessagePublisherMock{
  private val registrar = UserRegisterRepoPublisher[Future]()(repository, messagePublisher,implicitly)

  "register a user" in {
    val user           = UserStub.random
    val userRegistered = UserRegisteredStub(user)

    repositoryShouldSave(user)

    publisherShouldPublish(userRegistered)

    registrar.register(user.id, user.name).map(_.shouldBe(()))
  }
}
