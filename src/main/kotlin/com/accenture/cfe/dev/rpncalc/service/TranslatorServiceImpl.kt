package com.accenture.cfe.dev.rpncalc.service

import com.accenture.cfe.dev.rpncalc.entity.CalculatorInput

class TranslatorServiceImpl(
  private val calculatorService: CalculatorService
) : TranslatorService {
  override fun calculateInput(input: String): Number {
    return calculatorService.calculate(
      input.split(' ')
      .map {
        it.toDoubleOrNull() ?: it
      }
      .map {
        when(it) {
          is Number -> CalculatorInput.Value(it)
          is String -> CalculatorInput.Operator(it)
          else -> throw IllegalArgumentException("Totally froopy input")
        }
      }
    )
  }
}
