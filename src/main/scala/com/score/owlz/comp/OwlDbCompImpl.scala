package com.score.owlz.comp

import com.mongodb.casbah.commons.MongoDBObject
import com.score.owlz.config.{AppConf, DbConf}
import com.score.owlz.protocol.Owl

trait OwlDbCompImpl extends OwlDbComp {

  this: DbConf with AppConf =>

  val owlDB = new OwlDbImpl

  class OwlDbImpl extends OwlDb {
    def createOwl(owl: Owl) = {
      val zBuilder = MongoDBObject.newBuilder
      zBuilder += "user" -> "user"
      zBuilder += "from" -> "kandy"

      coll.insert(zBuilder.result())
    }

    def getOwl = {

    }

    def getOwls(from: String, to: String, date: String) = {
    }

  }

}
