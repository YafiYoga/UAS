package com.example.uas.frontEnd

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.uas.R
import com.example.uas.components.CButton
import com.example.uas.ui.theme.AlegreyaFontFamily
import com.example.uas.ui.theme.AlegreyaSansFontFamily


@Composable
fun SelamatDatang(
    navController: NavHostController,

) {
    Surface(
        color = Color(0xFF6C3428),
        modifier = Modifier.fillMaxSize()
    ) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // BG Gambar


        /// Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {

            Spacer(modifier = Modifier.weight(1f))



            Text(
                "WELCOME!!",
                fontSize = 32.sp,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight(1000),
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.logo3),
                contentDescription = null,
                modifier = Modifier
                    .width(320.dp)
                    .height(240.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                "di aplikasi baron(barbershop online) membantu anda dalam menemukan barbershop pilihan anda.",
                textAlign = TextAlign.Center,
                fontFamily = AlegreyaSansFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight(1000),
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))


            Row {
                CButton(text = "Pelanggan",
                    onClick = {
                        navController.navigate("loginn")
                    }


                )

                Spacer(modifier = Modifier.width(16.dp))

                CButton(text = " Barbershop",
                    onClick = {
                        navController.navigate("login")
                    }


                )

            }
            Spacer(modifier = Modifier.weight(1f))






        }
    }
    }
}
