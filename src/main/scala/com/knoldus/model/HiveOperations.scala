package com.knoldus.model

import java.sql.{Connection, ResultSet}
import com.google.inject.Inject
import scala.reflect.runtime.universe._

class HiveOperations @Inject()(hiveConnection: HiveConnection) {

  val dPHClassName = "DPH"

  def classAccessors[T: TypeTag]: List[String] = typeOf[T].members.collect {
    case methodSymbol: MethodSymbol if methodSymbol.isCaseAccessor => methodSymbol.fullName.replace(dPHClassName + ".", "")
  }.toList

  def createDatabase(connection: Connection): Boolean = {
    val statement = connection.createStatement()
    statement.execute("create database if not exists quetzal")
  }

  def selectDatabase(connection: Connection): Boolean = {
    val statement = connection.createStatement()
    statement.execute("use quetzal")
  }

  def createDphTable(connection: Connection): Boolean = {
    val statement = connection.createStatement()
    statement.execute("create table if not exists dph (entity string, spill  int, pred1  string, pred2  string, pred3  string, pred4  " +
      "string, pred5  string, val1   string, val2   string, val3   string, val4   string, val5   string, domain string )")
  }

  def createPredicateTable(connection: Connection): Boolean = {
    val statement = connection.createStatement()
    statement.execute("create table if not exists predicateLookUp (predicate string, location string)")
  }

  def insertDphData(connection: Connection, dph: DPH): Boolean = {
    val statement = connection.createStatement()
    statement.execute(
      s"""insert into dph (entity, spill, pred1, pred2, pred3, pred4, pred5, val1, val2, val3, val4, val5, domain)
         |values ("${dph.entity}", ${dph.spill}, "${dph.pred1}",	"${dph.pred2}", "${dph.pred3}", "${dph.pred4}",	"${dph.pred5}",	"${dph.val1}",
         |"${dph.val2}",	"${dph.val3}", "${dph.val4}",	"${dph.val5}", "${dph.domain}")""".stripMargin)
  }

  def selectDphData(connection: Connection): List[DPH] = {
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery("select * from dph")
    getDphDataFromResultSet(resultSet, Nil)
  }

  def getDphDataFromResultSet(resultSet: ResultSet, listOfDph: List[DPH]): List[DPH] = {
    if (resultSet.next()) {
      val dPH = DPH(resultSet.getString("entity"), resultSet.getInt("spill"), resultSet.getString("pred1"),
        resultSet.getString("val1"), resultSet.getString("pred2"),
        resultSet.getString("val2"), resultSet.getString("pred3"),
        resultSet.getString("val3"), resultSet.getString("pred4"),
        resultSet.getString("val4"), resultSet.getString("pred5"),
        resultSet.getString("val5"), resultSet.getString("domain"))
      getDphDataFromResultSet(resultSet, dPH :: listOfDph)
    } else {
      listOfDph
    }
  }

}

