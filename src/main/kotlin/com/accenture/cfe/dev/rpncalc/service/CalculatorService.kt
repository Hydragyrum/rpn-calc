package com.accenture.cfe.dev.rpncalc.service

import com.accenture.cfe.dev.rpncalc.entity.CalculatorInput

interface CalculatorService {
  fun calculate(inputs:List<CalculatorInput>): Number
}
