package tv.codely.scala_http_api.module.user.application.register

import tv.codely.scala_http_api.module.user.UserUnitTestCase
import tv.codely.scala_http_api.module.user.domain.UserStub

final class UserRegistererShould extends UserUnitTestCase {
  private val registerer = new UserRegisterer(repository)

  "save a user" in {
    val user = UserStub.random

    repositoryShouldSave(user)

    registerer.register(user.id, user.name) shouldBe ()
  }
}
