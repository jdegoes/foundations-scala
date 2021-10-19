package net.degoes

import zio.test._
import zio.test.TestAspect._

object Recursion extends DefaultRunnableSpec {
  def spec =
    suite("Recursion") {

      /**
       * EXERCISE
       *
       * Using recursion, compute the sum of a list of integers.
       */
      test("sum") {
        def sum(list: List[Int]): Int = ???

        assertTrue(sum(List(1, 2, 3, 4, 5)) == 15)
      } @@ ignore +
        /**
         * EXERCISE
         *
         * Using recursion, compute the maximum of a list of integers.
         */
        test("max") {
          def max(list: List[Int]): Int = ???

          assertTrue(max(List(1, 7, 3, 2, 4, 5)) == 7)
        } @@ ignore
    }
}
