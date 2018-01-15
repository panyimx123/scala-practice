package com.y2pan.practice

import java.io.File
import java.util
import java.util.Scanner

import scala.collection.mutable

object Practice4 extends App {
  val eq = Map("big head" -> 20, "small sword" -> 110)
  val eq1 = for ((k, v) <- eq) yield (k, 0.9 * v)
  println(eq1.mkString(" and "))

  val in = new Scanner(new File("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile.txt"))
  val words = mutable.HashMap[String, Int]()
  while (in.hasNext()) {
    val key: String = in.next()
    words(key) = words.getOrElse(key, 0) + 1
  }
  println(words.mkString(" and "))

  val in1 = new Scanner(new File("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile.txt"))
  var words1 = Map[String, Int]()
  while (in1.hasNext()) {
    val key: String = in1.next()
    words1 += (key -> (words1.getOrElse(key, 0) + 1))
  }
  println(words1.mkString(" and "))

  val in2 = new Scanner(new File("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile.txt"))
  var words2 = mutable.SortedMap[String, Int]()
  while (in2.hasNext()) {
    val key: String = in2.next()
    words2(key) = words2.getOrElse(key, 0) + 1
  }
  println(words2.mkString(" and "))

  val in3 = new Scanner(new File("/Users/test/project/spark/rdb-server-log-maker/src/main/resources/myfile.txt"))
  val word3: scala.collection.mutable.Map[String, Int] = scala.collection.
    JavaConverters.mapAsScalaMap(new util.TreeMap[String, Int])
  while (in3.hasNext()) {
    val key: String = in3.next()
    word3(key) = word3.getOrElse(key, 0) + 1
  }
  println(word3.mkString(" and "))

  val props: scala.collection.mutable.Map[String, String] = scala.collection.JavaConverters
    .propertiesAsScalaMap(System.getProperties)
  val keys = props.keySet

  val keyLengths = for (key <- keys) yield key.length

  val maxKeyLength = keyLengths.max

  for (key <- keys) {
    print(key)
    print(" " * (maxKeyLength - key.length))
    print(" | ")
    println(props(key))
  }

  def minmax(values: Array[Int]): scala.collection.Map[Int, Int] = {
    Map(values.min -> values.max)
  }

  println(minmax(Array(2, 3, 34, 343)))

  def lteqgt(values: Array[Int], v: Int): Tuple3[Int, Int, Int] = {
    (values.count(value => value < v), values.count(value => value == v), values.count(value => value > v))
  }

  println(lteqgt(Array(2,34,1,3,2,34), 3))

  println("Hello".zip("World"))
}
