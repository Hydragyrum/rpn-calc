package com.accenture.cfe.dev.rpncalc.service

import com.accenture.cfe.dev.rpncalc.entity.CalculatorInput
import com.accenture.cfe.dev.rpncalc.utils.Stack
import com.accenture.cfe.dev.rpncalc.utils.StackImpl
import kotlin.math.sqrt

class CalculatorServiceImpl() : CalculatorService {

  private val stack: Stack<Number> = StackImpl()

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
      "SQRT" -> applyOperation { a -> sqrt(a) }
      "SQ" -> applyOperation { a -> a * a }
      else -> throw IllegalArgumentException("Unknown operation")
    }
  }

  private fun applyOperation(operation: (Double) -> Double) {
    val operand = getOperand()
    val result = operation(operand)
    stack.push(result)
  }

  private fun applyOperation(operation: (Double, Double) -> Double) {
    val operand2 = getOperand()
    val operand1 = getOperand()
    val result = operation(operand1, operand2)
    stack.push(result)
  }

  private fun getOperand() = stack.pop()?.toDouble() ?: throw IllegalArgumentException("No numbers left!")
}
