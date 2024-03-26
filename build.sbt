name := "antennaswitch"
organization := "wa9nnn.com"

scalaVersion := "3.3.1"
val logbackVersion = "1.4.11"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "21.0.0-R32",
  "org.scalatest" %% "scalatest" % "3.2.17" % "test",
  "com.typesafe" % "config" % "1.4.3",
  "com.fazecast" % "jSerialComm" % "2.10.4",
  "com.fazecast" % "jSerialComm" % "2.10.4",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5",
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "ch.qos.logback" % "logback-core" % logbackVersion,
  "com.google.inject" % "guice" % "7.0.0",
  "net.codingwell" %% "scala-guice" % "7.0.0",
  "org.playframework" %% "play-json" % "3.0.2",
  "com.beachape" %% "enumeratum" % "1.7.3",

  //  "com.pi4j" % "pi4j-core" % "1.4",
  //  "com.pi4j" % "pi4j-gpio-extension" % "1.3",

  "com.pi4j" % "pi4j-core" % "2.5.0",
  "com.pi4j" % "pi4j-gpio-extension" % "1.3",
  "com.pi4j" % "pi4j-plugin-pigpio" % "2.5.0",
)

// Fork a new JVM for 'run' and 'test:run' to avoid JavaFX double initialization problems
fork := true

// set the main class for the main 'run' task
// change Compile to Test to set it for 'test:run'
Compile / run / mainClass := Some("antennsw.ScalaFXHelloWorld")
