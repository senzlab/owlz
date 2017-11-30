package com.score.owlz.comp

import com.score.owlz.protocol.Owl


trait OwlDbComp {

  val owlDb: OwlDb

  trait OwlDb {
    def createOwl(owl: Owl): Owl

    def getOwls(from: String, to: String, date: String, receiver: Boolean): List[Owl]

    def getUsers(from: String, to: String, date: String, receiver: Boolean): List[String]
  }

}

