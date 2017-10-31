package com.knoldus.model

import java.sql.{Connection, DriverManager}

class HiveConnection {

  val driverName = "org.apache.hive.jdbc.HiveDriver"

  def getHiveConnection: Connection = {

    try {
      Class.forName(driverName)
    } catch {
      case exception: ClassNotFoundException =>
        exception.printStackTrace()
        System.exit(1)
    }

    DriverManager.getConnection("jdbc:hive2://localhost:10000/default")
  }
}

