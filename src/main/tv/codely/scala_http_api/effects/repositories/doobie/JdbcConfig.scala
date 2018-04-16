package tv.codely.scala_http_api.effects.repositories.doobie

import com.typesafe.config.Config

object JdbcConfig {
  def apply(config: Config): JdbcConfig = JdbcConfig(
    driver = config.getString("driver"),
    url = config.getString("url"),
    user = config.getString("user"),
    password = config.getString("password")
  )
}

final case class JdbcConfig(driver: String, url: String, user: String, password: String)
