package tv.codely.scala_http_api.module.shared.bus.infrastructure.rabbit_mq

import com.typesafe.config.Config

object RabbitMqConfig {
  def apply(config: Config): RabbitMqConfig = RabbitMqConfig(
    host = config.getString("host"),
    port = config.getInt("port"),
    user = config.getString("user"),
    password = config.getString("password")
  )
}

final case class RabbitMqConfig(host: String, port: Int, user: String, password: String)
