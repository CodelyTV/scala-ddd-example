package tv.codely.scala_http_api.module.user.infrastructure.repository

import tv.codely.scala_http_api.module.user.UserIntegrationTestCase
import tv.codely.scala_http_api.module.user.domain.{User, UserStub}
import doobie.util.update.Update
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.TypesConversions._
import doobie.implicits._
import cats.implicits._

final class DoobieMySqlUserRepositoryTest extends UserIntegrationTestCase {
  private def insert(users: Seq[User]) =
    Update[User]("INSERT INTO users(user_id, name) values (?, ?)")
      .updateMany(users.toVector)
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  private def cleanUsersTable() =
    sql"TRUNCATE TABLE users".update.run
      .transact(doobieDbConnection.transactor)
      .unsafeToFuture()
      .futureValue

  "Doobie MySql user repository" should {
    "return an empty sequence if there're no users" in {
      repository.all().futureValue shouldBe Seq.empty
    }

    "search all existing users" in {
      val expectedUsers = UserStub.randomSeq

      insert(expectedUsers)

      repository.all().futureValue should be(expectedUsers)

      cleanUsersTable()
    }
  }
}
