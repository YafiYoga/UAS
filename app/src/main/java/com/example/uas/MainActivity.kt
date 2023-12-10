package com.example.uas


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uas.frontEnd.Login
import com.example.uas.frontEnd.Register
import com.example.uas.frontEnd.SelamatDatang

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
                startDestination = "Login"
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
            }
        }
    }
}
