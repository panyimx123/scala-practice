package com.y2pan.practice

/**
  * Created by test on 2017/11/22.
  */
object Practice2 extends App {
  def signum(num: Int): Int = {
    if (num < 0) -1 else if (num > 0) 1 else 0
  }

  val y = {}
  val x = signum(23)
  println(x)
  println(y)

  for (i <- 0 to 10 reverse) println(i)

  def countdown(n: Int) {
    (0 to n reverse) foreach print
  }

  countdown(4)

  def multiply(s: String): Long = {
    var result: Long = 1
    for (i <- s) {
      result = result * i.toLong
    }
    result
  }

  def multiply2(s: String): Long = {
    if (s.length == 1) s.charAt(0).toLong
    else s.take(1).charAt(0).toLong * multiply2(s.drop(1))
  }

  println(multiply2("Hello"))

  var t: Long = 1
  "Hello".foreach(t *= _.toLong)
  println(t)


  def multiply3(x: Int, n: Int): Int = {
    if (n > 0) {
      if (n % 2 == 0)
        multiply3(x, n / 2) * multiply3(x, n / 2)
      else
        x * multiply3(x, n - 1)
    } else if(n < 0) {
      1/multiply3(x, -n)
    }else{
      1
    }
  }

  println(multiply3(3, 4))
}
