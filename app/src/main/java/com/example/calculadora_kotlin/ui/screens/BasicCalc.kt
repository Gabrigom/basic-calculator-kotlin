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
                label = "Primeiro número"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomNumericInput(
                value = numTwo,
                onValueChange = { numTwo = it },
                label = "Segundo número"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OperationButton(
                    onPress = {
                        handleOperation(
                            numOne,
                            numTwo,
                            CalculatorOperations::add,
                            { newResult -> result = newResult },
                            { newError -> inputError = newError }
                        )
                    },
                    symbol = "+"
                )

                OperationButton(
                    onPress = {
                        handleOperation(
                            numOne,
                            numTwo,
                            CalculatorOperations::subtract,
                            { newResult -> result = newResult },
                            { newError -> inputError = newError }
                        )
                    },
                    symbol = "−"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OperationButton(
                    onPress = {
                        handleOperation(
                            numOne,
                            numTwo,
                            CalculatorOperations::multiply,
                            { newResult -> result = newResult },
                            { newError -> inputError = newError }
                        )
                    },
                    symbol = "×"
                )

                OperationButton(
                    onPress = {
                        handleOperation(
                            numOne,
                            numTwo,
                            CalculatorOperations::divide,
                            { newResult -> result = newResult },
                            { newError -> inputError = newError }
                        )
                    },
                    symbol = "÷"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            ResultDisplay(result = result, error = inputError)
        }
    }
}


private fun handleOperation(
    firstNumber: String,
    secondNumber: String,
    operation: (Float, Float) -> Float,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    onSuccess("")
    onError("")

    when (val operationResult = CalculatorOperations.performOperation(firstNumber, secondNumber, operation)) {
        is CalculatorOperations.OperationResult.Success -> onSuccess(operationResult.value)
        is CalculatorOperations.OperationResult.Error -> onError(operationResult.message)
    }
}

@Preview(showBackground = true)
@Composable
fun BasicCalcPreview() {
    BasicCalc()
}