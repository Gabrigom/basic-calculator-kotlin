package com.example.calculadora_kotlin.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OperationButton(
    onSelect: () -> Unit,
    symbol: String,
    isSelected: Boolean,
    textColor: Color = Color.White,
    backgroundColor: Color = Color(0xFF388E3C),
    selectedBackgroundColor: Color = Color(0xFF1B5E20)
) {
    // Use selected background color if this operation is selected
    val buttonBackground = if (isSelected) selectedBackgroundColor else backgroundColor

    Button(
        onClick = onSelect,
        modifier = Modifier
            .size(65.dp)
            .aspectRatio(1f),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonBackground,
            contentColor = textColor
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = symbol,
                fontSize = 28.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OperationButtonPreview() {
    OperationButton(
        onSelect = {},
        symbol = "+",
        isSelected = false
    )
}

@Preview(showBackground = true)
@Composable
fun SelectedOperationButtonPreview() {
    OperationButton(
        onSelect = {},
        symbol = "+",
        isSelected = true
    )
}