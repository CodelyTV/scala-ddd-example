package tv.codely.shared.infrastructure.rabbitmq

trait MessagePurger {
  def purgeQueue(): Unit
}
