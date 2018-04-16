package tv.codely.scala_http_api.effects.bus.rabbitmq

trait MessagePurger {
  def purgeQueue(): Unit
}
