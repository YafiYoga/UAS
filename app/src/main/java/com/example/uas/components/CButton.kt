package com.example.uas.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.uas.ui.theme.AlegreyaSansFontFamily

@Composable
fun CButton(
    onClick: () -> Unit = {},
    text: String


    ,
){
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        )
        ) {

        Text(
            text = text,
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = AlegreyaSansFontFamily,
                fontWeight = FontWeight(500),
                color = Color(0xFF6C3428)
            )
        )

    }
}