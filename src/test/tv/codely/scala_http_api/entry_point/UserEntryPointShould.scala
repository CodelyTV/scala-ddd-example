package tv.codely.scala_http_api.entry_point

import akka.http.scaladsl.model._
import cats.implicits._
import doobie.implicits._
import doobie.util.update.Update
import spray.json._
import tv.codely.scala_http_api.module.shared.infrastructure.persistence.doobie.TypesConversions._
import tv.codely.scala_http_api.module.user.domain.{User, UserStub}
import tv.codely.scala_http_api.module.user.infrastructure.marshaller.UserJsValueMarshaller

final class UserEntryPointShould extends AcceptanceSpec {
  private val expectedUsers = UserStub.randomSeq

  override def beforeAll(): Unit = {
    super.beforeAll()

    def insertExpectedUsers() =
      Update[User]("INSERT INTO users(user_id, name) values (?, ?)")
        .updateMany(expectedUsers.toVector)
        .transact(doobieDbConnection.transactor)
        .unsafeToFuture()
        .futureValue

    insertExpectedUsers()
  }

  override def afterAll(): Unit = {
    super.afterAll()

    def truncateUsersTable() =
      sql"TRUNCATE TABLE users".update.run
        .transact(doobieDbConnection.transactor)
        .unsafeToFuture()
        .futureValue

    truncateUsersTable()
  }

  "return all the system users" in getting("/users") {
    status shouldBe StatusCodes.OK
    contentType shouldBe ContentTypes.`application/json`
    entityAs[String].parseJson shouldBe UserJsValueMarshaller.marshall(expectedUsers)
  }
}
