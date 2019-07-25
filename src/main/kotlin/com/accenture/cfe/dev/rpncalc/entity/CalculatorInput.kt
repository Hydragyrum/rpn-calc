package com.accenture.cfe.dev.rpncalc.entity

sealed class CalculatorInput {
  class Value(val number: Number): CalculatorInput()
  class Operator(val op: String): CalculatorInput()
}
