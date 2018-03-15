package tv.codely.scala_http_api.effects.repositories.doobie

import tv.codely.scala_http_api.application.stubs.user.UserStub
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
    repository.all().unsafeToFuture.futureValue shouldBe Seq.empty
  }

  "search all existing users" in {
    val users = UserStub.randomSeq

    users.foreach(u => repository.save(u).unsafeToFuture.futureValue)

    repository.all().unsafeToFuture.futureValue shouldBe users
  }
}
