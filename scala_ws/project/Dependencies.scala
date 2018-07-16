import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val collUtils = "com.twitter" %% "util-collection" % "17.12.0"
  lazy val coreUtils = "com.twitter" %% "util-core" % "17.12.0"
}
