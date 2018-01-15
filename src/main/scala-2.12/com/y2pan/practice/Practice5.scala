package com.y2pan.practice

class Counter {
  private var value = 0

  def increment() {
    if (value != Int.MaxValue) value += 1
  }

  def current(): Int = value
}

class BankAccount1 {
  private var balance: Int = 0

  def current: Int = balance

  def deposit(value: Int): Unit = {
    balance += value
    println("deposit " + value)
  }

  def withDraw(value: Int): Unit = {
    if (balance >= value) {
      balance -= value
      println("withDraw " + value)
    }
    else println("you balance is not enough")
  }
}

class Time3 {
  private var hours: Int = 0
  private var minutes: Int = 0

  def this(hours: Int, minutes: Int) {
    this()
    if (hours >= 0 && hours <= 23) {
      this.hours = hours
    } else {
      throw new Exception("construct Time hour error")
    }
    if (minutes >= 0 && minutes <= 59) {
      this.minutes = minutes
    } else {
      throw new Exception("construct Time minute error")
    }
  }

  def before(other: Time3): Boolean = {
    if (this.hours < other.hours) true
    else if (this.hours == other.hours) this.minutes < other.minutes
    else false
  }
}

class Time4(private val hours: Int, private val minutes: Int) {

  if (hours < 0 || hours > 23) {
    throw new Exception("construct Time hour error")
  }
  if (minutes < 0 || minutes > 59) {
    throw new Exception("construct Time minute error")
  }

  def getMinute: Int = minutes + hours * 60

  def before(other: Time4): Boolean = {
    if (this.hours < other.hours) true
    else if (this.hours == other.hours) this.minutes < other.minutes
    else false
  }
}

import scala.beans.BeanProperty

class Student(@BeanProperty var id: Long, @BeanProperty var name: String) {

}

class Person(private var age: Int) {
  if (age < 0) age = 0

  def getAge: Int = age
}

class Person1(name: String) {
  private var firstName = ""
  private var lastName = ""

  private val names: Array[String] = name.split("\\s")
  if (names.length != 2) {
    throw new Exception("wrong name format!")
  } else {
    firstName = names(0)
    lastName = names(1)
  }

  def getName: String = firstName + " " + lastName
}

class Car(private val maker: String, private val version: Int) {
  private var vYear = 2017
  var licencePlate = "S32348"

  def this() {
    this("China", 2011)
  }

  def this(maker: String, version: Int, vYear: Int, licencePlate: String) {
    this(maker, version)
    this.vYear = vYear
    this.licencePlate = licencePlate
  }

  def this(maker: String, version: Int, vYear: Int) {
    this(maker, version)
    this.vYear = vYear
    this.licencePlate = ""
  }

  def this(maker: String, version: Int, licencePlate: String) {
    this(maker, version)
    this.vYear = -1
    this.licencePlate = licencePlate
  }

}

object Practice5 extends App {
  val counter = new Counter
  counter.increment()
  println(counter.current())

  val account = new BankAccount1
  account.withDraw(2)
  account.deposit(200)
  account.withDraw(100)
  println(s"left ${account.current}")

  val t1 = new Time3(22, 55)
  val t2 = new Time3(23, 22)

  println(t1.before(t2))

  val t3 = new Time4(22, 55)
  println(t3.getMinute)

  val s = new Student(333l, "stu1")
  println(s.getId)

  val p = new Person(-30)
  println(p.getAge)

  val p1 = new Person1("Smith Fate")
  println(p1.getName)

  val car = new Car("Toyota", 2011)
  val car1 = new Car("China", 2012, "S343")
  val car2 = new Car("China", 2012, 2012)
  val car3 = new Car("China", 2012, 2012, "S343")
  val car4 = new Car
  println(car)
}

