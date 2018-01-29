package com.y2pan.practice

import scala.collection.mutable.ListBuffer
import scala.collection.{Iterable, mutable}

object Practice13 extends App {
  def indexes(str: String): mutable.Map[Char, mutable.SortedSet[Int]] = {
    (mutable.Map[Char, mutable.SortedSet[Int]]() /: str.zipWithIndex) {
      (m, c) =>
        m + (c._1 -> m.getOrElse(c._1, mutable.SortedSet {
          c._2
        }).+(c._2))
    }
  }

  def indexes1(str: String): Map[Char, mutable.SortedSet[Int]] = {
    (Map[Char, mutable.SortedSet[Int]]() /: str.zipWithIndex) {
      (m, c) =>
        m + (c._1 -> m.getOrElse(c._1, mutable.SortedSet {
          c._2
        }).+(c._2))
    }
  }

  println(indexes("Mississippi"))
  println(indexes1("Mississippi"))

  def removeEven(list: ListBuffer[Int]): Unit = {
    for (n <- list.indices reverse) {
      if (n % 2 != 0) {
        list.remove(n)
      }
    }
  }

  def removeEven1(list: ListBuffer[Int]): List[Int] = {
    var l: List[Int] = List[Int]()
    for (n <- list.indices reverse) {
      if (n % 2 == 0) {
        l = l.::(list(n))
      }
    }
    l
  }

  val list = ListBuffer(3, 4, 1, 2, 3, 4, 5)
  removeEven(list)
  println(list.mkString(","))
  val list1 = ListBuffer(3, 4, 1, 2, 3, 4, 5)
  val list2 = removeEven1(list1)
  println(list2.mkString(","))

  def findArray(array: Array[String], map: mutable.Map[String, Int]): Array[Int] = {
    array.flatMap(str => map.get(str))
  }

  println(findArray(Array("Tom", "Fred", "Harry"), mutable.Map("Tom" -> 3, "Dick" -> 4, "Harry" -> 5)).mkString(","))

  def cmkString(list: Iterable[String], separate: String): String = {
    list.reduceLeft(_ + separate + _)
  }

  println(cmkString(List("Tom", "Fred", "Harry"), ","))

  val lst: List[Int] = List(3, 4, 5, 6)
  val rst = List(7, 8, 9, 10)
  println((lst :\ List[Int]()) (_ :: _))
  println((List[Int]() /: lst) (_ :+ _))
  println((lst :\ List[Int]()) (_ :: _).reverse)

  val prices = List(5.0, 20.0, 9.95)
  val quantities = List[Double](10, 2, 1)

  def multi: ((Double, Double)) => Double = Function.tupled((a1: Double, a2: Double) => a1 * a2)

  val c = (prices zip quantities) map { p => multi(p) }

  println(c.mkString(","))

  def toDim(array: Array[Int], n: Int): Iterable[Array[Int]] = {
    array.groupBy(i => (i - 1) / n).values
  }

  for (elem <- toDim(Array(1, 2, 3, 4, 5, 6), 3)) {
    println(elem.mkString(","))
  }

  val r1 = for (i <- 1 to 10; j <- 1 to i) yield i * j
  println(r1.mkString(","))

  val zones = java.util.TimeZone.getAvailableIDs

  println(zones.mkString(","))

  val zonesG = zones.groupBy(zone => {
    if (zone.indexOf("/") != -1) {
      zone.substring(0, zone.indexOf("/"))
    } else {
      zone
    }
  })
  val zonesG1 = zonesG.map((a) => a._1 -> a._2.size)
  println(zonesG1.mkString(","))
  println(zonesG1.maxBy(a => a._2))

  val str = "SSSSSFDKLWRWERWELKASF"
  val b = str.aggregate(Map[Char, Int]())((a, b) => {
    a + (b -> (a.getOrElse(b, 0) + 1))
  }, _ ++ _)
  print(b.mkString(","))
}
