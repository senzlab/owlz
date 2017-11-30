package com.score.owlz.comp

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.commons.MongoDBObject
import com.score.owlz.config.{AppConf, DbConf}
import com.score.owlz.protocol.{Owl, User}
import salat._
import salat.global._

trait OwlDbCompImpl extends OwlDbComp {

  this: DbConf with AppConf =>

  val owlDb = new OwlDbImpl()

  class OwlDbImpl extends OwlDb {
    def createOwl(owl: Owl): Owl = {
      val uBuilder = MongoDBObject.newBuilder
      uBuilder += "username" -> owl.user.username
      uBuilder += "phone" -> owl.user.phone

      val oBuilder = MongoDBObject.newBuilder
      oBuilder += "user" -> uBuilder.result()
      oBuilder += "from" -> owl.from
      oBuilder += "to" -> owl.to
      oBuilder += "date" -> owl.date
      oBuilder += "date" -> owl.date
      oBuilder += "desc" -> owl.desc
      oBuilder += "recv" -> owl.recv

      val dBObject = grater[Owl].asDBObject(owl)
      coll.save(dBObject, WriteConcern.Safe)

      grater[Owl].asObject(dBObject)
    }

    def getOwls(from: String, to: String, date: String, recv: Boolean): List[Owl] = {
      val q = MongoDBObject("from" -> from, "to" -> to, "date" -> date, "recv" -> recv)
      val cursor = coll.find(q)
      val s = for {
        x <- cursor
      } yield grater[Owl].asObject(x)

      s.toList
    }

    def getUsers(from: String, to: String, date: String, recv: Boolean): List[String] = {
      val q = MongoDBObject("from" -> from, "to" -> to, "date" -> date, "recv" -> recv)
      val cursor = coll.distinct("user.username", q).toList
      val s = for {
        x <- cursor
      } yield x

      s.map(_.toString)
    }
  }

}

object M extends App with OwlDbCompImpl with DbConf with AppConf {
  owlDb.createOwl(Owl(user = User("sani", Option("8999")), from = "matale", to = "kand", date = "15/12/2017", desc = Option("hoooo"), recv = false))
  //owlDb.getOwls("colo", "kand", "15/12/2017", false)
  //owlDb.getUsers("colo", "kand", "15/12/2017", false)
}

// db.owls.find({"user": {"username": "eraga", "phone": "02323"}}) check both fields
// db.owls.find({'from': 'colo', 'to': 'kand'}) // check both fields
// db.owls.find({'owls.user.username': 'kasun'}) // one filed
// db.owls.distinct('user.username') // return only distinct usernames [eranga, kasun]
// db.owls.distinct('user.username', {'from': 'colo'}) // return distinct usernames where from = colo