package com.score.owlz.util

import com.mongodb.casbah.MongoClient
import com.score.owlz.config.DbConf

object DbFactory extends DbConf {

  // mongo
  lazy val client = MongoClient(mongoHost, mongoPort)
  lazy val senzDb = client(dbName)
  lazy val coll = senzDb(collName)

  val initDb = () => {
    // todo create tables etc
  }

}
