package tv.codely.mooc.user.application.search

import tv.codely.mooc.user.domain.UserMother
import tv.codely.mooc.user.infrastructure.repository.UserRepositoryMock
import tv.codely.shared.infrastructure.unit.UnitTestCase

final class UsersSearcherShould extends UnitTestCase with UserRepositoryMock {
  private val searcher = new UsersSearcher(repository)

  "search all existing users" in {
    val existingUser        = UserMother.random
    val anotherExistingUser = UserMother.random
    val existingUsers       = Seq(existingUser, anotherExistingUser)

    repositoryShouldFind(existingUsers)

    searcher.all().futureValue shouldBe existingUsers
  }
}
