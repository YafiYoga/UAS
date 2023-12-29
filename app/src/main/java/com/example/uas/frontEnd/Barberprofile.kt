package com.example.uas.frontEnd

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uas.BottomNavigasi
import com.example.uas.PreferencesManager
import com.example.uas.R
import com.example.uas.ui.theme.AlegreyaSansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Barberprofile (navController: NavController, context: Context = LocalContext.current) {
    val baseColor = Color(0xFF6C3428)
    val preferencesManager = remember { PreferencesManager(context = context) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Profile ", modifier = Modifier.padding(top = 5.dp), fontWeight = FontWeight.Bold, fontSize = 24.sp,
                        fontFamily = AlegreyaSansFontFamily)
                    IconButton(modifier = Modifier.padding(start = 320.dp), onClick = {
                        preferencesManager.saveData("jwt", "")
                        navController.navigate("HomePageBarber")
                    }) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "Sign Out",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = baseColor,
                    titleContentColor = Color.White
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
        },
        bottomBar = {
            BottomNavigasi(navController)
        },


        )

    {
        Box (modifier = Modifier.fillMaxSize() .padding(top = 100.dp)
        ){
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier
                    .width(430.dp)
                    .height(270.dp)
                    .alpha(0.5f),
                painter = painterResource(id = R.drawable.layanan),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds,
            )

        }
        Spacer(modifier = Modifier.height(24.dp) .padding(top = 200.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 42.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier
                    .width(114.dp)
                    .height(114.dp),
                painter = painterResource(id = R.drawable.layanan),
                contentDescription = "image description",
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Halimah",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            )
        }
        Spacer(modifier = Modifier.padding(top = 300.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp, start = 12.dp, end = 12.dp),

            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,

            ) {
            Spacer(modifier = Modifier.padding(top = 90.dp))
            Text(
                text = "Profile",
                style = TextStyle(
                    fontSize =28.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF6C3428),
                )
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "Nama Karyawan",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF6C3428),
                )
            )


            Spacer(modifier = Modifier.padding(top =20.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.padding(top =20.dp))

            Text(
                text = "Deskripsi Booth",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF6C3428),
                )
            )
            Text(
                text = "Menjual berbagai penyetan ayam, ikan, dan nasi campur",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF6C3428),
                )
            )


        }

        }
    }

