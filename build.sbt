organization := "com.ygomi.asample"

name := "rdb-server-log-maker"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.slf4j" % "log4j-over-slf4j" % "1.7.22",
  "ch.qos.logback" % "logback-classic" % "1.1.8"
)