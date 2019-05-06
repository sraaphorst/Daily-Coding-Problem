package dcp.day029

import org.scalatest.FunSuite

class TestDay029 extends FunSuite {
  private val ExampleString = "AAAABBBCCDAA"
  private val ExampleStringEncoding = "4A3B2C1D2A"
  private val LongString = "A" * 1001 + "BBB" + "C" * 23 + "A" * 99
  private val LongStringEncoding = "1001A3B23C99A"

  test("Encoder: empty string") {
    Encoder.encode("").equals("")
  }
  test("Encoder: one character") {
    Encoder.encode("A").equals("1A")
  }
  test("Encoder: example") {
    Encoder.encode(ExampleString).equals(ExampleStringEncoding)
  }
  test("Encoder: long string") {
    Encoder.encode(LongString).equals(LongStringEncoding)
  }

  test("Decoder: empty string") {
    Decoder.decode("").equals("")
  }
  test("Decoder: one character") {
    Decoder.decode("1A").equals("A")
  }
  test("Decoder: example") {
    Decoder.decode(ExampleStringEncoding).equals(ExampleString)
  }
  test("Decoder: long string") {
    Decoder.decode(LongStringEncoding).equals(LongString)
  }
}
