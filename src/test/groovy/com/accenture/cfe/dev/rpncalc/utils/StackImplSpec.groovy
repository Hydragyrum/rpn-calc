package com.accenture.cfe.dev.rpncalc.utils

import spock.lang.Specification

class StackImplSpec extends Specification {
  def "IsEmpty"() {
    given: "A Stack"
      def stack = new StackImpl()
    expect:
      stack.isEmpty()
  }

  def "IsNotEmpty"() {
    given: "A stack"
      def stack = new StackImpl()
      stack.push(1)
    expect:
      !stack.isEmpty()
  }

  def "Count"() {
    given: "A stack"
      def stack = new StackImpl()
    expect:
      0 == stack.count()
  }

  def "Count 1"() {
    given: "A stack"
      def stack = new StackImpl()
      stack.push(1)
    expect:
      1 == stack.count()
  }

  def "Pop"() {
    given: "A stack"
      def stack = new StackImpl()
      stack.push(1)
    expect:
      1 == stack.pop()
    and:
      stack.isEmpty()
  }

  def "Peek"() {
    given: "A stack"
      def stack = new StackImpl()
      stack.push(1)
    expect:
      1 == stack.peek()
  }
}
