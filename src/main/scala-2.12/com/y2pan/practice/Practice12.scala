package com.y2pan.practice

object Practice12 extends App {
  val f = (_: String).charAt(_: Int)
  println(f.apply("ser", 1))
  val f1: (String, Int) => Char = _.charAt(_)

  val triple = (x: Double) => 3 * x

  def valueAtOneQuarter(f: (Double) => Double): Double = f(0.25)

  def mulBy(factor: Double): (Double) => Double = (x: Double) => factor * x

  def multi(a: Int, b: Int): Int = a * b

  def multi(a: Int): (Int) => Int = (b: Int) => a * b

  def multi1(a: Int)(b: Int): Int = a * b

  println(multi(3, 4))
  println(multi(3)(4))

  def runInThread(block: () => Unit): Unit = {
    new Thread {
      override def run(): Unit = block()
    }.start()
  }

  runInThread { () => println("ss") }

  def runInThread1(block: => Unit): Unit = {
    new Thread {
      override def run(): Unit = block
    }.start()
  }

  runInThread1 {
    println("ss")
  }

  def values(fun: (Int) => Int, low: Int, high: Int): Array[(Int, Int)] = {
    (low to high).map(i => (i, fun(i))).toArray
  }

  val nums = Array(3, 4, 2, 2, 4, 6)

  println(nums.reduceLeft((a, b) => if (a < b) b else a))

  println((1 to 3).reduceLeft(_ * _))
  println(values(x => x * x, -5, 5).mkString(","))

  println((1 to 3).foldLeft(1)(_ * _))

  def largest(fun: (Int) => Int, inputs: Seq[Int]): Int = {
    fun(inputs.maxBy(fun(_)))
  }

  def largestAt(fun: (Int) => Int, inputs: Seq[Int]): Int = {
    inputs.maxBy(fun(_))
  }

  println(largest(x => 10 * x - x * x, 1 to 10))
  println(largestAt(x => 10 * x - x * x, 1 to 10))

  val pairs = (1 to 10) zip (11 to 20)
  println(pairs.mkString(","))

  def adjustToPair(fun: (Int, Int) => Int): (Int, Int) => Int = {
    (a: Int, b: Int) => fun(a, b)
  }

  println(adjustToPair(_ * _)(6, 7))

  val a = Array("Hello", "World")
  val b = Array(5, 6)
  println(a.corresponds(b)(_.length == _))

  def corresponds(a: Array[String], b: Array[Int], func: (Array[String], Array[Int]) => Boolean): Boolean = {
    func(a, b)
  }

  println(corresponds(a, b, _.length == _))

  def unless(condition: => Boolean)(block: => Unit) {
    if (!condition) {
      block
    }
  }

  val i = 3 * 12
  unless(i < 35) {
    println(i)
  }

}
