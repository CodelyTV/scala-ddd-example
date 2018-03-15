import sbt._

object Dependencies {
  object Versions {
    val akka     = "2.5.9"
    val akkaHttp = "10.0.11"
    val http4sVersion = "0.18.5"
    val circeVersion = "0.9.1"
  }

  val production = Seq(
    "com.github.nscala-time"     %% "nscala-time"             % "2.18.0",
    "com.lihaoyi"                %% "pprint"                  % "0.5.3",
    "com.typesafe.akka"          %% "akka-http"               % Versions.akkaHttp,
    "com.typesafe.akka"          %% "akka-actor"              % Versions.akka,
    "com.typesafe.akka"          %% "akka-stream"             % Versions.akka, // Explicit dependency due to: https://bit.ly/akka-http-25
    "com.typesafe.akka"          %% "akka-http-spray-json"    % Versions.akkaHttp,
    "org.tpolecat"               %% "doobie-core"             % "0.5.0",
    "mysql"                      % "mysql-connector-java"     % "5.1.45",
    "com.github.scopt"           %% "scopt"                   % "3.7.0", // Command Line Commands such as de DbTablesCreator
    "com.newmotion"              %% "akka-rabbitmq"           % "5.0.0",
    "ch.qos.logback"             % "logback-classic"          % "1.2.3", // Logging backend implementation
    "com.typesafe.scala-logging" %% "scala-logging"           % "3.7.2", // SLF4J Scala wrapper
    "net.logstash.logback"       % "logstash-logback-encoder" % "4.11", // Log JSON encoder
    "org.typelevel"              %% "cats-core"               % "1.0.1",
    "org.typelevel"              %% "cats-effect"             % "0.10",
    "org.http4s"                 %% "http4s-dsl"              % Versions.http4sVersion,
    "org.http4s"                 %% "http4s-blaze-server"     % Versions.http4sVersion,
    "org.http4s"                 %% "http4s-blaze-client"     % Versions.http4sVersion,
    "org.http4s"                 %% "http4s-circe"            % Versions.http4sVersion,
    "io.circe"                   %% "circe-generic"           % Versions.circeVersion,
    "io.circe"                   %% "circe-literal"           % Versions.circeVersion,
    "com.chuusai"                %% "shapeless"               % "2.3.3"
  )

  val test = Seq(
    "org.scalatest"     %% "scalatest"         % "3.0.5"           % Test,
    "org.scalamock"     %% "scalamock"         % "4.0.0"           % Test,
    "com.typesafe.akka" %% "akka-testkit"      % Versions.akka     % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % Versions.akkaHttp % Test
  )
}
