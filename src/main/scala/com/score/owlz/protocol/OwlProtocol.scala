package com.score.owlz.protocol

import org.bson.types.ObjectId

case class User(username: String, phone: Option[String])

case class Owl(_id: ObjectId = null, user: User, from: String, to: String, date: String, desc: Option[String], recv: Boolean)

