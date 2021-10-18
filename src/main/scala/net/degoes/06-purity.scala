package net.degoes

import zio.test._
import zio.test.TestAspect._

object Purity extends DefaultRunnableSpec {
  def spec =
    suite("Purity") {
      test("") {
        assertTrue(true)
      } @@ ignore
    }
}
