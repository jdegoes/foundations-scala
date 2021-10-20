/**
 * Functional Scala uses functional programming, which emphasizes composing
 * programs from expressions that are referentially transparent, with
 * functions that are total and deterministic. This property, which is
 * sometimes called _purity_, fully inverts control in a functional program,
 * allowing the caller of functions complete control, which in turn makes
 * programs easier to understand, cheaper to safely modify, and easier to
 * test.
 *
 * In this module, you will explore purity and techniques for using purity
 * inside your applications.
 */
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
