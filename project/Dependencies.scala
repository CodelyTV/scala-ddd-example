import sbt._

object Dependencies {
  object Versions {
    val akka     = "2.5.7"
    val akkaHttp = "10.0.11"
  }

  val production = Seq(
    "com.github.nscala-time" %% "nscala-time"          % "2.18.0",
    "com.lihaoyi"            %% "pprint"               % "0.5.3",
    "com.typesafe.akka"      %% "akka-http"            % Versions.akkaHttp,
    "com.typesafe.akka"      %% "akka-actor"           % Versions.akka,
    "com.typesafe.akka"      %% "akka-stream"          % Versions.akka, // Explicit dependency due to: https://bit.ly/akka-http-25
    "com.typesafe.akka"      %% "akka-http-spray-json" % Versions.akkaHttp
  )

  val test = Seq(
    "org.scalatest"     %% "scalatest"         % "3.0.4"           % Test,
    "org.scalamock"     %% "scalamock"         % "4.0.0"           % Test,
    "com.typesafe.akka" %% "akka-testkit"      % Versions.akka     % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % Versions.akkaHttp % Test
  )
}
