package com.score.owlz.protocol

case class User(username: String, phone: Option[String])

case class Owl(user: User, from: String, to: String, date: String, desc: Option[String], receiver: Boolean)

