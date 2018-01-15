package com.y2pan.practice

object Practice12 extends App {
  val f = (_: String).charAt(_: Int)
  print(f.apply("ser", 1))
  val f1: (String, Int) => Char = _.charAt(_)

  val triple = (x: Double) => 3 * x
}