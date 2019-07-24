package com.accenture.cfe.dev.rpncalc.service

interface CalculatorService {
  fun inputNumber(number: Number)
  fun calculateOperation(operation: String): Number
}
