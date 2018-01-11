package tv.codely.scala_http_api.module.shared.infrastructure.config

import com.typesafe.config.Config

object DbConfig {
  def apply(dbConfig: Config): DbConfig = DbConfig(
    driver = dbConfig.getString("driver"),
    url = dbConfig.getString("url"),
    user = dbConfig.getString("user"),
    password = dbConfig.getString("password")
  )
}

final case class DbConfig(driver: String, url: String, user: String, password: String)
