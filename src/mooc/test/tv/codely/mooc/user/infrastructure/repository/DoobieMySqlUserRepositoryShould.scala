package tv.codely.scala_http_api.module.user.infrastructure.repository

import tv.codely.scala_http_api.module.user.UserIntegrationTestCase
import tv.codely.scala_http_api.module.user.domain.UserStub
import doobie.implicits._
import org.scalatest.BeforeAndAfterEach

final class DoobieMySqlUserRepositoryShould extends UserIntegrationTestCase with BeforeAndAfterEach {
  private def cleanUsersTable(): Unit =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .map(_ => ())
      .futureValue

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    cleanUsersTable()
  }

  "return an empty sequence if there're no users" in {
    repository.all().futureValue shouldBe Seq.empty
  }

  "search all existing users" in {
    val users = UserStub.randomSeq

    users.foreach(u => repository.save(u).futureValue)

    repository.all().futureValue shouldBe users
  }
}
