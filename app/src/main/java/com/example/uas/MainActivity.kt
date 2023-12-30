package com.example.uas


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uas.frontEnd.Barberprofile
import com.example.uas.frontEnd.DetailLayanan
import com.example.uas.frontEnd.EditLayanan
import com.example.uas.frontEnd.HalamanRiwayatPelanggan
import com.example.uas.frontEnd.Login
import com.example.uas.frontEnd.Register
import com.example.uas.frontEnd.SelamatDatang
import com.example.uas.frontEnd.HomePageBarber
import com.example.uas.frontEnd.HomePagePelanggan
import com.example.uas.frontEnd.TambahLayanan
import com.example.uas.ui.theme.AlegreyaSansFontFamily

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
                composable(route = "Barberprofile") {
                    Barberprofile(navController)
                }
                composable(route = "TambahLayanan") {
                    TambahLayanan(navController)
                }
                composable(
                    route = "EditLayanan/{layananid}/{NamaLayanan}/{DeskripsiLayanan}/{Harga}",
                ) {backStackEntry ->
                    EditLayanan(navController,
                        backStackEntry.arguments?.getString("layananid"),
                        backStackEntry.arguments?.getString("NamaLayanan"),
                        backStackEntry.arguments?.getString("DeskripsiLayanan"),
                        backStackEntry.arguments?.getString("Harga")
                    )
                }
                composable(
                    route = "DetailLayanan/{layananid}/{NamaLayanan}/{DeskripsiLayanan}/{Harga}",
                ) {backStackEntry ->
                    DetailLayanan(navController,
                        backStackEntry.arguments?.getString("layananid"),
                        backStackEntry.arguments?.getString("NamaLayanan"),
                        backStackEntry.arguments?.getString("DeskripsiLayanan"),
                        backStackEntry.arguments?.getString("Harga")
                    )
                }
                composable(route = "HalamanRiwayatPelanggan") {
                    HalamanRiwayatPelanggan(navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavigasi(navController: NavController) {


    NavigationBar(
        modifier = Modifier
            .background(color = Color(0xFF6C3428))
    )
    {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Home Page",
                icon = Icons.Default.Home,
                route = "HomePageBarber"
            ),
            BottomNavItem(
                label = "tambah layanan",
                icon = Icons.Default.AddCircle,
                route = "tambahlayanan"
            ),
            BottomNavItem(
                label = "Riwayat",
                icon = Icons.Default.DateRange,
                route = "DaftarBooking"
            ),
            BottomNavItem(
                label = "Profile",
                icon = Icons.Default.AccountCircle,
                route = "Barberprofile"
            )
        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.route,
                onClick = {navController.navigate(it.route) },
                icon = { Icon(imageVector = it.icon, contentDescription = it.label, tint = Color(0xFF6C3428) ) },
                label = { Text(text = it.label,
                    color = Color(0xFF6C3428),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp, // Adjust the font size as needed
                        fontFamily = AlegreyaSansFontFamily
                        // Add other style parameters as needed
                    )
                ) }
            )
        }

    }
}

@Composable
fun BottommNavigasi(navController: NavController) {


    NavigationBar(
        modifier = Modifier
            .background(color = Color(0xFF6C3428))
    )
    {
        val bottomNavigation = listOf(
            BottomNavItem(
                label = "Home Page",
                icon = Icons.Default.Home,
                route = "HomePagePelanggan"
            ),
            BottomNavItem(
                label = "transaksi",
                icon = Icons.Default.AddCircle,
                route = "HalamanRiwayatPelanggan"
            ),

        )
        bottomNavigation.map {
            NavigationBarItem(
                selected = navController.currentDestination?.route == it.route,
                onClick = {navController.navigate(it.route) },
                icon = { Icon(imageVector = it.icon, contentDescription = it.label, tint = Color(0xFF6C3428) ) },
                label = { Text(text = it.label,
                    color = Color(0xFF6C3428),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp, // Adjust the font size as needed
                        fontFamily = AlegreyaSansFontFamily
                        // Add other style parameters as needed
                    )
                ) }
            )
        }

    }
}
data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)