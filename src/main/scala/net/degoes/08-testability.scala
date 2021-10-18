package net.degoes

import zio.test._
import zio.test.TestAspect._

object Testability extends DefaultRunnableSpec {
  def spec =
    suite("Testability") {
      test("") {
        assertTrue(true)
      } @@ ignore
    }
}
