package tv.codely.scala_http_api.module.user

import tv.codely.scala_http_api.module.UnitTestCase
import tv.codely.scala_http_api.module.user.domain.{User, UserRepository}

import scala.concurrent.Future

protected[user] trait UserUnitTestCase extends UnitTestCase {
  protected val repository: UserRepository = mock[UserRepository]

  protected def shouldSearchAllUsers(users: Seq[User]): Unit =
    (repository.all _)
      .expects()
      .returning(Future.successful(users))
}
