ThisBuild / scalaVersion := "2.13.10"

ThisBuild / version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """Connector_1""",
    libraryDependencies ++= Seq(
      ws,
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "org.json4s" %% "json4s-native" % "4.0.6",
      "org.scalatest" %% "scalatest" % "3.2.14" % "test",
      "com.typesafe.akka" %% "akka-testkit" % "2.7.0" % Test,
    )
  )