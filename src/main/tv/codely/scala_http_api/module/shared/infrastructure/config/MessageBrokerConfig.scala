package tv.codely.scala_http_api.module.shared.infrastructure.config

import com.typesafe.config.Config

object MessageBrokerConfig {
  def apply(config: Config): MessageBrokerConfig = MessageBrokerConfig(
    host = config.getString("host"),
    port = config.getInt("port"),
    user = config.getString("user"),
    password = config.getString("password")
  )
}

final case class MessageBrokerConfig(host: String, port: Int, user: String, password: String)
