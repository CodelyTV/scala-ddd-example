package tv.codely.scala_http_api.module.user.application.search

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.user.domain.UserStub
import tv.codely.scala_http_api.module.user.infrastructure.repository.UserRepositoryMock

final class UsersSearcherShould extends UnitTestCase with UserRepositoryMock {
  private val searcher = new UsersSearcher(repository)

  "search all existing users" in {
    val existingUser        = UserStub.random
    val anotherExistingUser = UserStub.random
    val existingUsers       = Seq(existingUser, anotherExistingUser)

    repositoryShouldFind(existingUsers)

    searcher.all().futureValue shouldBe existingUsers
  }
}
