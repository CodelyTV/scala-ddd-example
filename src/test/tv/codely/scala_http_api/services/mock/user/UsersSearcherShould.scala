package tv.codely.scala_http_api
package services.mock.user

import tv.codely.scala_http_api.application.mock.UnitTestCase
import tv.codely.scala_http_api.application.stubs.user.UserStub
import tv.codely.scala_http_api.effects.repositories.mock.UserRepositoryMock
import tv.codely.scala_http_api.application.repo_publisher.user.UsersSearcherRepo

final class UsersSearcherRepoShould extends UnitTestCase with UserRepositoryMock {
  private val searcher = new UsersSearcherRepo()(repository)

  "search all existing users" in {
    val existingUser        = UserStub.random
    val anotherExistingUser = UserStub.random
    val existingUsers       = Seq(existingUser, anotherExistingUser)

    repositoryShouldFind(existingUsers)

    searcher.all().futureValue shouldBe existingUsers
  }
}
