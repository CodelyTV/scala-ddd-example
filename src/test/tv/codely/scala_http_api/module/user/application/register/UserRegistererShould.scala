package tv.codely.scala_http_api.module.user.application.register

import tv.codely.scala_http_api.module.shared.infrastructure.MessagePublisherMock
import tv.codely.scala_http_api.module.user.UserUnitTestCase
import tv.codely.scala_http_api.module.user.domain.{UserRegisteredStub, UserStub}

final class UserRegistererShould extends UserUnitTestCase with MessagePublisherMock {
  private val registerer = new UserRegisterer(repository, messagePublisher)

  "register a user" in {
    val user           = UserStub.random
    val userRegistered = UserRegisteredStub(user)

    repositoryShouldSave(user)

    publisherShouldPublish(userRegistered)

    registerer.register(user.id, user.name).shouldBe(())
  }
}
