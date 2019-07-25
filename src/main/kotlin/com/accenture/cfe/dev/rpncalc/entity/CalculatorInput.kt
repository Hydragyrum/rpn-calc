package com.accenture.cfe.dev.rpncalc.entity

sealed class CalculatorInput {
  data class Value(val number: Number): CalculatorInput()
  data class Operator(val op: String): CalculatorInput()
}
