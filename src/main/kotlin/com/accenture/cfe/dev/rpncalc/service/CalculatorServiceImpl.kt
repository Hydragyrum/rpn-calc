package com.accenture.cfe.dev.rpncalc.service

import com.accenture.cfe.dev.rpncalc.entity.CalculatorInput
import com.accenture.cfe.dev.rpncalc.utils.Stack
import com.accenture.cfe.dev.rpncalc.utils.StackImpl
import java.math.BigDecimal

class CalculatorServiceImpl() : CalculatorService {

  private val stack: Stack<Number> = StackImpl<Number>()

  override fun calculate(inputs: List<CalculatorInput>): Number {
    inputs.forEach {
      when (it) {
        is CalculatorInput.Value -> stack.push(it.number)
        is CalculatorInput.Operator -> executeOp(it.op)
      }
    }
    return stack.pop() ?: TODO("Error when nothing on stack");
  }

  private fun executeOp(op: String) {
    when (op) {
      "+" -> applyOperation { a, b -> a + b }
      "-" -> applyOperation { a, b -> a - b }
      "*" -> applyOperation { a, b -> a * b }
      "/" -> applyOperation { a, b -> a / b }
    }
  }

  private fun applyOperation(operation: (BigDecimal, BigDecimal) -> BigDecimal) {
    val operand2 = getOperand()
    val operand1 = getOperand()
    val result = operation(operand1, operand2)
    stack.push(result.toDouble())
  }

  private fun getOperand() = BigDecimal((stack.pop() ?: TODO("Empty Stack")).toString())
}
