package tv.codely.scala_http_api.module.user.infrastructure.repository

import org.scalamock.scalatest.MockFactory
import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}

import scala.concurrent.Future

protected[user] trait UserRepositoryMock extends MockFactory {
  this: UnitTestCase => // Make mandatory to also extend UnitTestCase in order to avoid using mocks in any other kind of test.

  protected val repository: UserRepository = mock[UserRepository]

  protected def repositoryShouldSave(user: User): Unit =
    (repository.save _)
      .expects(user)
      .returning(Future.unit)

  protected def repositoryShouldFind(users: Seq[User]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(users))
}
