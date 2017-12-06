package tv.codely.scala_http_api.module.user.application.search

import tv.codely.scala_http_api.module.user.UserUnitTestCase
import tv.codely.scala_http_api.module.user.domain.UserStub

final class UsersSearcherTest extends UserUnitTestCase {
  private val searcher = new UsersSearcher(repository)

  "Users Searcher" should {
    "search all existing users" in {
      val existingUser        = UserStub.random
      val anotherExistingUser = UserStub.random
      val existingUsers       = Seq(existingUser, anotherExistingUser)

      shouldSearchAllUsers(existingUsers)

      searcher.all() should be(existingUsers)
    }
  }
}
