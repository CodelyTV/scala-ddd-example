package tv.codely.scala_http_api.module.shared.infrastructure.config

import com.typesafe.config.Config

object DbConfig {
  def apply(config: Config): DbConfig = DbConfig(
    driver = config.getString("driver"),
    url = config.getString("url"),
    user = config.getString("user"),
    password = config.getString("password")
  )
}

final case class DbConfig(driver: String, url: String, user: String, password: String)
