package dcp.day010

import java.time.{Duration, Instant}
import java.util.function.Supplier

import org.scalatest.FunSuite

class TestDay010 extends FunSuite {
  val WaitTime: Long = 1000
  val Supplied: Int = 1
  val Supplier: Supplier[Int] = () => { Supplied }

  test("Day010: seconds elapsed before function call") {
    val starttime = Instant.now
    val value = DelayedExecutor.execute(Supplier, WaitTime)
    val endtime = Instant.now
    val duration = Duration.between(starttime, endtime).toMillis
    assert(duration >= WaitTime && value == Supplied)
  }
}
