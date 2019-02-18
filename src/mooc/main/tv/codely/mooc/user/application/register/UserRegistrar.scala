package tv.codely.mooc.user.application.register

import tv.codely.mooc.shared.domain.user.UserId
import tv.codely.mooc.shared.infrastructure.marshaller.DomainEventsMarshaller
import tv.codely.mooc.user.domain._
import tv.codely.shared.domain.bus.MessagePublisher

final class UserRegistrar(repository: UserRepository, publisher: MessagePublisher) {
  def register(id: UserId, name: UserName): Unit = {
    val user = User(id, name)

    repository.save(user)

    publisher.publish(UserRegistered(user))(new DomainEventsMarshaller)
  }
}
