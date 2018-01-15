package com.y2pan.practice

import scala.collection.mutable.ArrayBuffer

object Practice8 extends App {
  val sa = new SavingAccount(300)
  sa.deposit(22)
  sa.deposit(44)
  sa.withdraw(66)
  sa.deposit(22)
  sa.withdraw(22)
  println(sa.currentBalance)
  sa.earnMonthlyInterest()
  sa.deposit(22)
  sa.withdraw(22)
  println(sa.currentBalance)
}

class BankAccount(initialBalance: Double) {
  private var balance = initialBalance

  def currentBalance: Double = balance

  def deposit(amount: Double): Double = {
    balance += amount
    balance
  }

  def withdraw(amount: Double): Double = {
    balance -= amount
    balance
  }
}

class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double): Double = super.deposit(amount - 1)

  override def withdraw(amount: Double): Double = super.withdraw(amount + 1)
}

class SavingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  var freeCount = 3

  def earnMonthlyInterest(): Unit = {
    freeCount = 3
    super.withdraw(currentBalance * 0.0005)
  }

  override def deposit(amount: Double): Double = {
    if (freeCount <= 0) {
      super.deposit(amount - 1)
    } else {
      freeCount -= 1
      super.deposit(amount)
    }
  }

  override def withdraw(amount: Double): Double = {
    if (freeCount <= 0) {
      super.withdraw(amount + 1)
    } else {
      freeCount -= 1
      super.withdraw(amount)
    }
  }
}

abstract class Item {
  def price(): Double

  def description(): String
}

class SimpleItem(override val price: Double, override val description: String) extends Item {

}

class Bundle extends Item {
  private val items: ArrayBuffer[Item] = ArrayBuffer()

  def addItem(item: Item): Unit = {
    items += item
  }

  def price(): Double = {
    var total = 0d
    items.foreach(total += _.price())
    total
  }

  def description(): String = {
    "This is a Bundle"
  }
}

class Point4(x: Int, y: Int) {}

class LabeledPoint(label: String, x: Int, y: Int) extends Point4(x, y) {

}

abstract class Shape {
  def centerPoint()
}

class Rectangle(startX: Int, startY: Int, endX: Int, endY: Int) extends Shape {
  def centerPoint() {}
}


class Circle(x: Int, y: Int, radius: Double) extends Shape {
  def centerPoint() {}
}

class Creature {
  def range: Int = 10
  val env: Array[Int] = new Array[Int](range)
}

class Ant extends Creature{
  override val range: Int = 2
}

class Point2(private val xy: Long) {
  private val x: Int = xy.toInt
  private val y: Int = xy.toInt
}

class Square extends java.awt.Rectangle{

}