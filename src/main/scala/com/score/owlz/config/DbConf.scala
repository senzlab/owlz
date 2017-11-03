package com.score.owlz.config

import com.typesafe.config.ConfigFactory

import scala.util.Try

/**
  * Load db configurations define in database.conf from here
  *
  * @author eranga herath(erangaeb@gmail.com)
  */
trait DbConf {
  // config object
  val dbConf = ConfigFactory.load("database.conf")

  // mongo config
  lazy val mongoHost = Try(dbConf.getString("db.mongo.host")).getOrElse("dev.localhost")
  lazy val mongoPort = Try(dbConf.getInt("db.mongo.port")).getOrElse(27017)
  lazy val dbName = Try(dbConf.getString("db.mongo.db-name")).getOrElse("owlz")
  lazy val collName = Try(dbConf.getString("db.mongo.coll-name")).getOrElse("items")
}
