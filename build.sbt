enablePlugins(JavaAppPackaging)

name := "wake-scala-example"
organization := "com.wunderlist"
version := "1.0"
scalaVersion := "2.11.7"

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.3.12",
    "com.typesafe.akka" %% "akka-stream-experimental" % "1.0",
    "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0",
    "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
    "com.typesafe.akka" %% "akka-http-testkit-experimental" % "1.0",
    "org.scalatest" %% "scalatest" % "2.2.5" % "test"
  )
}

Revolver.settings
