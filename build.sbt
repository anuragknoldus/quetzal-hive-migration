name := """quetzal-hive-integration"""

version := "1.0"

scalaVersion := "2.12.3"

organization := "com.knoldus"

//Source

val hiveJDBC = "org.apache.hive" % "hive-jdbc" % "2.3.0"
val loggerCore = "org.apache.logging.log4j" % "log4j-core" % "2.9.1"
val loggerApi = "org.apache.logging.log4j" % "log4j-api" % "2.9.1"


//Test

val scalaTest = "org.scalatest" % "scalatest_2.12" % "3.2.0-SNAP9" % "test"

libraryDependencies ++= Seq(hiveJDBC, loggerCore, loggerApi)
libraryDependencies ++= Seq(scalaTest)
