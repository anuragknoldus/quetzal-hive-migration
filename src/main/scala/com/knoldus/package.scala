package com

import com.typesafe.config.{Config, ConfigFactory}

package object knoldus {

  private val configFactory: Config = ConfigFactory.load()
  val databaseName: String = configFactory.getString("database.name")
  val driverName: String = configFactory.getString("database.driverName")
  val dataBaseUrl: String = configFactory.getString("database.url")

}
