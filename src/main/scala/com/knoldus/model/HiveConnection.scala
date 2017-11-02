package com.knoldus
package model

import java.sql.{Connection, DriverManager}

class HiveConnection {

  def getHiveConnection: Connection = {

    try {
      Class.forName(driverName)
    } catch {
      case exception: ClassNotFoundException =>
        exception.printStackTrace()
        System.exit(1)
    }

    DriverManager.getConnection(dataBaseUrl)
  }
}

