import sbt.{Tests, _}
import sbt.Keys._

object Configuration {
  val settings = Seq(
    organization := "tv.codely",
    scalaVersion := "2.12.4",

    // Custom folders path (/src/main/scala and /src/test/scala by default)
    scalaSource in Compile := baseDirectory.value / "/src/main",
    scalaSource in Test := baseDirectory.value / "/src/test",

    // Compiler options
    scalacOptions ++= Seq(
      "-deprecation", // Warnings deprecation
      "-feature", // Advise features
      "-unchecked", // More warnings. Strict
      "-Xlint", // More warnings when compiling
      "-Ywarn-dead-code",
      "-Ywarn-unused",
      "-Ywarn-unused-import",
      "-Xcheckinit" // Check against early initialization
    ),
    scalacOptions in run in Compile -= "-Xcheckinit", // Remove it in production because it's expensive
    javaOptions += "-Duser.timezone=UTC",

    // Test options
    parallelExecution in Test := false,
    testForkedParallel in Test := false,
    fork in Test := true,
    testOptions in Test ++= Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"), // Save test reports
      Tests.Argument("-oDF") // Show full stack traces and time spent in each test
    )
  )
}
