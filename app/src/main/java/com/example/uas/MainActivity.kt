package com.example.uas


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uas.frontEnd.Login
import com.example.uas.frontEnd.Register
import com.example.uas.frontEnd.SelamatDatang
import com.example.uas.frontEnd.HomePageBarber
import com.example.uas.frontEnd.HomePagePelanggan

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val preferencesManager = remember { PreferencesManager(context = LocalContext.current) }
            val sharedPreferences: SharedPreferences = LocalContext.current.getSharedPreferences("auth", Context.MODE_PRIVATE)
            val navController = rememberNavController()

            var startDestination: String
            var jwt = sharedPreferences.getString("jwt", "")
            if(jwt.equals("")){
                startDestination = "SelamatDatang"
            }else{
                startDestination = "login"
            }

            NavHost(navController, startDestination = startDestination) {
                composable(route = "SelamatDatang") {
                    SelamatDatang(navController)
                }
                composable(route = "Login") {
                    Login(navController)
                }
                composable(route = "Register") {
                    Register(navController)
                }
                composable(route = "HomePageBarber") {
                    HomePageBarber(navController)
                }
                composable(route = "HomePagePelanggan") {
                    HomePagePelanggan(navController)
                }
            }
        }
    }
}

