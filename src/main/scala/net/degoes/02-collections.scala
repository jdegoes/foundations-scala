/**
 * For loops are common in other programming languages. In functional Scala,
 * however, most for loops are replaced by operations on Scala collections,
 * which provide high-level and declarative solutions to many problems
 * involving data processing and transformation. Mastery of the Scala
 * collections library can provide an enormous boost to productivity,
 * as well as cement understanding of the tools of the experienced
 * functional programmer (mapping, filtering, folding, etc.).
 *
 * In this module, you will become more familiar with the power of the
 * Scala collections library as you solve a variety of common tasks
 * using the collections operations.
 */
package net.degoes

import zio.test._
import zio.test.TestAspect._

object Collections extends DefaultRunnableSpec {
  def spec =
    suite("Collections") {
      suite("operations") {

        /**
         * EXERCISE
         *
         * Using `List#foreach`, add up the contents of the provided list
         * into the variable `sum`.
         */
        test("foreach") {
          var sum = 0

          val list = List(0, 3, 0, 2, 1)

          list.foreach { _ =>
            sum += 0
          }

          assertTrue(sum == 6)
        } @@ ignore +
          /**
           * EXERCISE
           *
           * Using `List#map`, transform the provided list into a new list,
           * where each element of the list has been multiplied by 2.
           */
          test("map") {
            val list1 = List(0, 3, 0, 2, 1)

            val list2 = list1.map(i => i) // EDIT HERE

            assertTrue(list2.sum == 12 && list2.length == list1.length)
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Using `List#filter`, filter the provided list so that it only
           * contains even numbers.
           */
          test("filter") {
            val isEven = (i: Int) => i % 2 == 0

            val _ = isEven

            val list1 = List(0, 3, 0, 2, 1)

            val list2 = list1.filter(_ => true) // EDIT HERE

            assertTrue(list2 == List(0, 0, 2))
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Using `List#collect` and a partial function, collect all even
           * numbers from the provided List, wrapping them into an `Even`
           * wrapper type.
           */
          test("collect") {
            final case class Even(number: Int)

            val isEven = (i: Int) => i % 2 == 0

            val _ = isEven

            val list1 = List(0, 3, 0, 2, 1)

            def list2: List[Even] = list1.collect(???)

            assertTrue(list2 == List(Even(0), Even(0), Even(2)))
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Use `partition` to partition the provided list of integers into
           * those that are even, and those that are odd.
           */
          test("partition") {
            val isEven = (i: Int) => i % 2 == 0

            val _ = isEven

            val list = List(0, 3, 0, 2, 1)

            val (even, odd) = list.partition(_ => ???)

            assertTrue(even == List(0, 0, 2) && odd == List(3, 1))
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Use `reduceOption` to sum up both of the provided lists. In what
           * cases does `reduceOption` return `None`?
           */
          test("reduceOption") {
            val list1: List[Int] = List()
            val list2: List[Int] = List(1, 2, 3)

            val _ = list1
            val _ = list2

            def summedList1: Option[Int] = ???
            def summedList2: Option[Int] = ???

            assertTrue(summedList1 == None && summedList2 == Some(6))
          } @@ ignore +
          /**
           * EXERCISE
           *
           * Use `List#find` to find the first number that is greater than
           * two in the provided list.
           */
          test("find") {
            val list = List(1, 2, 3, 4)

            val _ = list

            def firstGreaterThan2: Option[Int] = ???

            assertTrue(firstGreaterThan2 == Some(3))
          } @@ ignore
      }
    }
}
