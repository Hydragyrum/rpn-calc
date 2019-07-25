package com.accenture.cfe.dev.rpncalc.service

import com.accenture.cfe.dev.rpncalc.entity.CalculatorInput
import spock.lang.Specification

class TranslatorServiceImplSpec extends Specification {
  def calcMock = Mock(CalculatorService)

  TranslatorService translatorService

  def setup() {
    translatorService = new TranslatorServiceImpl(calcMock)
  }

  def "Input string sends the list of values and operations"() {
    given: "An arbitrary input"
      def input = "1 2 3 + *"
    when:
      def result = translatorService.calculateInput(input)
    then:
      1 * calcMock.calculate(_ as List) >> { arg ->
        arg == [
            new CalculatorInput.Value(1.0),
            new CalculatorInput.Value(2.0),
            new CalculatorInput.Value(3.0),
            new CalculatorInput.Operator("+"),
            new CalculatorInput.Operator("*")
        ]
        return 5
      }
      result == 5
  }

}
