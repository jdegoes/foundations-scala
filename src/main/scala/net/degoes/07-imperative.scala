package net.degoes

import zio.test._
import zio.test.TestAspect._

object Imperative extends DefaultRunnableSpec {
  def spec =
    suite("Imperative") {
      test("") {
        assertTrue(true)
      } @@ ignore
    }
}
