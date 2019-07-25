package com.accenture.cfe.dev.rpncalc.service

import com.accenture.cfe.dev.rpncalc.entity.CalculatorInput
import spock.lang.Specification
import spock.lang.Unroll

class CalculatorServiceImplSpec extends Specification {
  def CalculatorService = new CalculatorServiceImpl()

  @Unroll
  def "A single number #num returns #num"() {
    given: "A Calculator"
      def calc = new CalculatorServiceImpl()
    and: "A set of inputs"
      def inputs = [new CalculatorInput.Value(num)]
    expect:
      num == calc.calculate(inputs)
    where:
      num << [1, 2, 3]
  }

  @Unroll
  def "A simple #op operation between #a and #b yields #c"() {
    given: "A calculator"
      def calc = new CalculatorServiceImpl()
    and: "A set of inputs"
      def inputs = [new CalculatorInput.Value(a), new CalculatorInput.Value(b), new CalculatorInput.Operator(op)]
    expect:
      c == calc.calculate(inputs)

    where:
      a | b | op  || c
      1 | 2 | "+" || 3
      4 | 5 | "+" || 9
      1 | 2 | "-" || -1
      3 | 1 | "-" || 2
      2 | 3 | "*" || 6
      6 | 2 | "/" || 3
  }

  def "A more complicated operation"() {
    given: "A calculator"
      def calc = new CalculatorServiceImpl()
    and: "A set of inputs"
      def inputs = [
          new CalculatorInput.Value(2),
          new CalculatorInput.Value(3),
          new CalculatorInput.Value(4),
          new CalculatorInput.Operator("+"),
          new CalculatorInput.Operator("*")
      ]
    expect:
      14 == calc.calculate(inputs)
  }
}
