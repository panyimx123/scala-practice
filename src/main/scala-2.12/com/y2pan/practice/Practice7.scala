package com.y2pan.practice


package object random {
  var seed: Int = _
  val a = 1664525
  val b = 1013904223
  val n = 32

  def nextInt(): Int = {
    seed = ((seed * a + b) % Math.pow(2, n)).toInt
    seed
  }

  def nextDouble(): Double = {
    val temp = (seed * a + b) % Math.pow(2, n)
    seed = temp.toInt
    temp.toDouble
  }
}
import random._
object Practice7 extends App {
  (1 to 10).foreach(x => println(nextInt()))
  (1 to 10).foreach(x => println(nextDouble()))

  import java.util.{HashMap => JavaHashMap}
  import scala.collection.{Map => ScalaMap}

  val javaMap = new JavaHashMap[Int, String]
  javaMap.put(1, "a")
  var scalaMap = ScalaMap[Int, String]()
  for (key <- javaMap.keySet().toArray) {
    scalaMap += (key.asInstanceOf[Int] -> javaMap.get(key))
  }
  println(scalaMap)

  import java.lang.System._
  val userName = getProperty("user.name")
  val password =  Console.readLine()
  if(!password.equals("secret"))
  {
    out.println("wrong password")
  }else{
    out.println("welcome")
  }

}

package com {

  class T1() {}
  package horstmann {

    class T2() {}
    package impatient {

      class T3() {}

    }

  }

}

package horstmann {
  package com {
    package horstman {

    }

  }

}

package com.horstmann.impatent {


}

