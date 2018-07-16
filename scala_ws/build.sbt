import Dependencies._

import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(DoubleIndentConstructorArguments, true)
    .setPreference(DanglingCloseParenthesis, Preserve)

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "scala_ws",
    libraryDependencies += scalaTest,
    libraryDependencies += collUtils,
    libraryDependencies += coreUtils % Test,
    libraryDependencies += "com.newmotion" %% "akka-rabbitmq" % "5.0.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.8",
    libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.8"
  )
