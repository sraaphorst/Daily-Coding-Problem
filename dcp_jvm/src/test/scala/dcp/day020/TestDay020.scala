package dcp.day020;

import org.scalatest.FunSuite;

class TestDay020 extends FunSuite {
    test("Day020: ListIntersection example 1") {
        val n3  = new ListIntersection.Node(3)
        val n7  = new ListIntersection.Node(7)
        val n8  = new ListIntersection.Node(8)
        val n10 = new ListIntersection.Node(10)
        val n99 = new ListIntersection.Node(99)
        val n1  = new ListIntersection.Node(1)

        n3.setNext(n7).setNext(n8).setNext(n10)
        n99.setNext(n1).setNext(n8)

        assert(ListIntersection.findIntersectionPoint(n3, n99).filter(n => n == n8).isPresent)
        val a = ListIntersection.findIntersectionPoint(n3, n99)
        assert(!ListIntersection.findIntersectionPoint(n3, n99).isEmpty)
    }

    test("Day020: ListIntersection example 2") {
        val n3   = new ListIntersection.Node(3)
        val n7   = new ListIntersection.Node(7)
        val n81  = new ListIntersection.Node(8)
        val n82  = new ListIntersection.Node(8)
        val n101 = new ListIntersection.Node(10)
        val n102 = new ListIntersection.Node(10)
        val n99  = new ListIntersection.Node(99)
        val n1   = new ListIntersection.Node(1)

        n3.setNext(n7).setNext(n81).setNext(n101)
        n99.setNext(n1).setNext(n82).setNext(n102)

        assert(ListIntersection.findIntersectionPoint(n3, n99).isEmpty)
        assert(!ListIntersection.findIntersectionPoint(n3, n99).isPresent)
    }
}
