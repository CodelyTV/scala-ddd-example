package tv.codely.scala_http_api.module.user

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}

import scala.concurrent.Future

protected[user] trait UserUnitTestCase extends UnitTestCase {
  // @ToDo: Use multiple inheritance in test suites extending from UnitTestCase and this UserUnitTestCase
  // in order to make more explicit what we have and avoid making the UnitTestCase extending from MockFactory
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
