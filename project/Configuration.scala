import sbt.{Tests, _}
import Keys.{excludeFilter, exportJars, _}
import org.scalafmt.sbt.ScalafmtPlugin.autoImport._

object Configuration {
  val commonSettings = Seq(
    organization := "tv.codely",
    scalaVersion := "2.12.8",
    scalacOptions := {
      val default = Seq(
        "-Xlint", // more warnings when compiling
        "-Xfatal-warnings", // warnings became errors
        "-unchecked", // more warnings. Strict
        "-deprecation", // warnings deprecation
        "-feature", // advise features
        "-language:higherKinds",
        "-Ypartial-unification"
      )
      if (version.value.endsWith("SNAPSHOT")) {
        default :+ "-Xcheckinit"
      } else { default } // check against early initialization
    },
    scalacOptions in (Test, console) --= Seq("-Ywarn-unused:imports", "-Xfatal-warnings"),
    scalacOptions in (Test, console) ++= Seq("-Ywarn-unused:-imports"),
    javaOptions += "-Duser.timezone=UTC",
    fork in Test := false,
    parallelExecution in Test := false,
    testOptions in Test ++= Seq(
      Tests.Argument(TestFrameworks.ScalaTest, "-u", "target/test-reports"),
      Tests.Argument("-oDF")
    ),
    cancelable in Global := true,
    // Scalafmt
    scalafmtConfig := Some(file(".scalafmt.conf")),
    // OneJar
    exportJars := true
  )
}
