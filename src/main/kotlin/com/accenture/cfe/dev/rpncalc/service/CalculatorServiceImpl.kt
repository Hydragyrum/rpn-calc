package com.accenture.cfe.dev.rpncalc.service

import com.accenture.cfe.dev.rpncalc.entity.CalculatorInput
import com.accenture.cfe.dev.rpncalc.utils.Stack
import com.accenture.cfe.dev.rpncalc.utils.StackImpl
import java.math.BigDecimal
import kotlin.math.sqrt

class CalculatorServiceImpl() : CalculatorService {

  private val stack: Stack<Number> = StackImpl<Number>()

  override fun calculate(inputs: List<CalculatorInput>): Number {
    stack.clear()
    inputs.forEach {
      when (it) {
        is CalculatorInput.Value -> stack.push(it.number)
        is CalculatorInput.Operator -> executeOp(it.op)
      }
    }
    return if (stack.count() == 1) {
      stack.pop() ?: throw IllegalStateException("This shouldn't be happening")
    } else {
      throw IllegalArgumentException("Invalid inputs!")
    };
  }

  private fun executeOp(op: String) {
    when (op) {
      "+" -> applyOperation { a, b -> a + b }
      "-" -> applyOperation { a, b -> a - b }
      "*" -> applyOperation { a, b -> a * b }
      "/" -> applyOperation { a, b -> a / b }
      "SQRT" -> applyOperation { a -> BigDecimal(sqrt(a.toDouble()).toString()) }
      "SQ" -> applyOperation { a -> a * a }
      else -> throw IllegalArgumentException("Unknown operation")
    }
  }

  private fun applyOperation(operation: (BigDecimal) -> BigDecimal) {
    val operand = getOperand()
    val result = operation(operand)
    stack.push(result.toDouble())
  }

  private fun applyOperation(operation: (BigDecimal, BigDecimal) -> BigDecimal) {
    val operand2 = getOperand()
    val operand1 = getOperand()
    val result = operation(operand1, operand2)
    stack.push(result.toDouble())
  }

  private fun getOperand() = BigDecimal((stack.pop() ?: throw IllegalArgumentException("No numbers left!")).toString())
}
