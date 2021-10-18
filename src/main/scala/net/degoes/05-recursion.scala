package net.degoes

import zio.test._
import zio.test.TestAspect._

object Recursion extends DefaultRunnableSpec {
  def spec =
    suite("Recursion") {
      test("") {
        assertTrue(true)
      } @@ ignore
    }
}
