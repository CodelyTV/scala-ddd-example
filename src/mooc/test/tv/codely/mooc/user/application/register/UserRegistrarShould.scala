package tv.codely.mooc.user.application.register

import tv.codely.mooc.user.domain.{UserMother, UserRegisteredMother}
import tv.codely.mooc.user.infrastructure.repository.UserRepositoryMock
import tv.codely.shared.infrastructure.rabbitmq.MessagePublisherMock
import tv.codely.shared.infrastructure.unit.UnitTestCase
import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller.MessageMarshaller

final class UserRegistrarShould extends UnitTestCase with UserRepositoryMock with MessagePublisherMock {
  private val registrar = new UserRegistrar(repository, messagePublisher)

  "register a user" in {
    val user           = UserMother.random
    val userRegistered = UserRegisteredMother(user)

    repositoryShouldSave(user)

    publisherShouldPublish(userRegistered)(MessageMarshaller)

    registrar.register(user.id, user.name).shouldBe(())
  }
}
