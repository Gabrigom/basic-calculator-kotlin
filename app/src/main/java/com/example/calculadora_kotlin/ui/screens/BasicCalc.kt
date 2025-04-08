package com.example.calculadora_kotlin.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadora_kotlin.domain.CalculatorOperations
import com.example.calculadora_kotlin.ui.components.CalculateButton
import com.example.calculadora_kotlin.ui.components.CustomNumericInput
import com.example.calculadora_kotlin.ui.components.OperationButton
import com.example.calculadora_kotlin.ui.components.ResultDisplay
import com.example.calculadora_kotlin.ui.components.AppHeader

@Composable
fun BasicCalc() {
    var numOne by remember { mutableStateOf("") }
    var numTwo by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var inputError by remember { mutableStateOf("") }

    // Track selected operation
    var selectedOperation by remember { mutableStateOf<OperationType?>(null) }

    Scaffold(
        topBar = {
            AppHeader()
        },
        containerColor = Color(0xFFF1F8E9)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF1F8E9)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            CustomNumericInput(
                value = numOne,
                onValueChange = { numOne = it },
                label = "Valor 1"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomNumericInput(
                value = numTwo,
                onValueChange = { numTwo = it },
                label = "Valor 2"
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Operation buttons row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OperationButton(
                    onSelect = { selectedOperation = OperationType.ADDITION },
                    symbol = "+",
                    isSelected = selectedOperation == OperationType.ADDITION
                )

                OperationButton(
                    onSelect = { selectedOperation = OperationType.SUBTRACTION },
                    symbol = "−",
                    isSelected = selectedOperation == OperationType.SUBTRACTION
                )

                OperationButton(
                    onSelect = { selectedOperation = OperationType.MULTIPLICATION },
                    symbol = "×",
                    isSelected = selectedOperation == OperationType.MULTIPLICATION
                )

                OperationButton(
                    onSelect = { selectedOperation = OperationType.DIVISION },
                    symbol = "÷",
                    isSelected = selectedOperation == OperationType.DIVISION
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Result display moved above Calculate button
            ResultDisplay(result = result, error = inputError)

            Spacer(modifier = Modifier.height(16.dp))

            // Calculate button
            CalculateButton(
                onClick = {
                    // Clear previous results
                    result = ""
                    inputError = ""

                    // Perform calculation based on selected operation
                    selectedOperation?.let { opType ->
                        val operation: (Float, Float) -> Float = when (opType) {
                            OperationType.ADDITION -> CalculatorOperations::add
                            OperationType.SUBTRACTION -> CalculatorOperations::subtract
                            OperationType.MULTIPLICATION -> CalculatorOperations::multiply
                            OperationType.DIVISION -> CalculatorOperations::divide
                        }

                        // Execute calculation
                        when (val operationResult = CalculatorOperations.performOperation(numOne, numTwo, operation)) {
                            is CalculatorOperations.OperationResult.Success -> result = operationResult.value
                            is CalculatorOperations.OperationResult.Error -> inputError = operationResult.message
                        }
                    } ?: run {
                        inputError = "Selecione uma operação"
                    }
                },
                enabled = selectedOperation != null
            )
        }
    }
}

/**
 * Enum to represent the different operation types
 */
enum class OperationType {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION
}

@Preview(showBackground = true)
@Composable
fun BasicCalcPreview() {
    BasicCalc()
}