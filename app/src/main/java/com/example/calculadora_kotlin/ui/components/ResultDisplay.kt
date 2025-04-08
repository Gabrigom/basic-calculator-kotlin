package com.example.calculadora_kotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultDisplay(
    result: String,
    error: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFE8F5E9))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            result.isNotEmpty() -> {
                Text(
                    text = "Resultado: $result",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32),
                    textAlign = TextAlign.Center
                )
            }
            error.isNotEmpty() -> {
                Text(
                    text = error,
                    fontSize = 18.sp,
                    color = Color(0xFFD32F2F),
                    textAlign = TextAlign.Center
                )
            }
            else -> {
                Text(
                    text = "O resultado aparecerá aqui",
                    fontSize = 18.sp,
                    color = Color(0xFF757575),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultDisplayPreview() {
    ResultDisplay(
        result = "120.5",
        error = ""
    )
}