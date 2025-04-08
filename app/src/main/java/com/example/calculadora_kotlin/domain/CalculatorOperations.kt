package com.example.calculadora_kotlin.domain

class CalculatorOperations {

    sealed class OperationResult {
        data class Success(val value: String) : OperationResult()
        data class Error(val message: String) : OperationResult()
    }

    companion object {

        fun performOperation(
            firstNumberStr: String,
            secondNumberStr: String,
            operation: (Float, Float) -> Float
        ): OperationResult {
            try {
                val num1 = firstNumberStr.toFloatOrNull()
                val num2 = secondNumberStr.toFloatOrNull()

                if (num1 != null && num2 != null) {
                    val operationResult = operation(num1, num2)

                    val formattedResult = if (operationResult == operationResult.toInt().toFloat()) {
                        operationResult.toInt().toString()
                    } else {
                        operationResult.toString()
                    }

                    return OperationResult.Success(formattedResult)
                } else {
                    return OperationResult.Error("Por favor, insira números válidos nos dois campos")
                }
            } catch (e: ArithmeticException) {
                return OperationResult.Error(e.message ?: "Erro ao calcular")
            } catch (e: Exception) {
                return OperationResult.Error("Falha ao calcular: ${e.message}")
            }
        }


        fun add(num1: Float, num2: Float): Float = num1 + num2

        fun subtract(num1: Float, num2: Float): Float = num1 - num2

        fun multiply(num1: Float, num2: Float): Float = num1 * num2

        fun divide(num1: Float, num2: Float): Float {
            if (num2 == 0f) throw ArithmeticException("Divisão por zero!!")
            return num1 / num2
        }
    }
}