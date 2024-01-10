package com.example.uas.frontEnd



import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uas.BottomNavigasi
import com.example.uas.PreferencesManager
import com.example.uas.R
import com.example.uas.respon.LayananRespon
import com.example.uas.respon.UserRespon
import com.example.uas.service.LayananService
import com.example.uas.respon.layanan
import com.example.uas.ui.theme.AlegreyaSansFontFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePageBarber(navController: NavController, context: Context = LocalContext.current) {
    val baseColor = Color(0xFF6C3428)
    val Tambahlayanan = painterResource(id = R.drawable.layanan)
    val DaftarBooking = painterResource(id = R.drawable.daftar)
    val CekBooking = painterResource(id = R.drawable.cek)
    //var listUser: List<UserRespon> = remember
    var search by remember { mutableStateOf(TextFieldValue("")) }
    val preferencesManager = remember { PreferencesManager(context = context) }
    val listLayanan = remember { mutableStateListOf<LayananRespon>() }

    //var listUser: List<UserRespon> by remember { mutableStateOf(List<UserRespon>()) }
    var baseUrl = "http://10.0.2.2:1337/api/"
    //var baseUrl = "http://10.217.17.11:1337/api/"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LayananService::class.java)
    val call = retrofit.getData(search.text,"*")
    call.enqueue(object : Callback<layanan<List<LayananRespon>>> {
        override fun onResponse(
            call: Call<layanan<List<LayananRespon>>>,
            response: Response<layanan<List<LayananRespon>>>
        ) {
            if (response.isSuccessful) {
                listLayanan.clear()
                response.body()?.data!!.forEach { layananRespon: LayananRespon ->
                    listLayanan.add(layananRespon)
                }
            } else {
                print("Error getting data. Code: ${response.code()}")
                Toast.makeText(
                    context,
                    "Error getting data. Code: ${response.code()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        override fun onFailure(call: Call<layanan<List<LayananRespon>>>, t: Throwable) {
            print(t.message)
            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        }
    })
    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "HomePage  ",
                        modifier = Modifier.padding(top = 5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = AlegreyaSansFontFamily
                    )
                    IconButton(modifier = Modifier
                        .padding(start = 320.dp)
                      ,
                        onClick = {
                        preferencesManager.saveData("jwt", "")
                        navController.navigate("Login")
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
                    titleContentColor = Color.White,
                ),
            )
        },
        bottomBar = {
            BottomNavigasi(navController)
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "User",
                    tint = baseColor
                )
                Text(
                    text = preferencesManager.getData("username"),
                    color = baseColor,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AlegreyaSansFontFamily,
                    modifier = Modifier
                        .padding(start = 9.dp)
                )
            }

            OutlinedTextField(
                value = search,
                onValueChange = {
                    search = it
                },
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        // Handle the search action
                    }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = baseColor
                        )
                    }
                },
                placeholder = { Text(text = "Pencarian......", color = baseColor) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = baseColor,
                    unfocusedBorderColor = baseColor,
                    cursorColor = baseColor,
                    textColor = Color(0xFF6C3428),
                )
            )
            LazyColumn {
                listLayanan?.forEach { layanan ->
                    item {
                        Box(
                            modifier = Modifier
                                    .fillMaxWidth()
                                .padding(16.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF6C3428))
                        ) {

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 2.dp)
                                    .padding(top = 18.dp, bottom = 18.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo3),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(72.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )

                                Column {

                                    Text(
                                        text = layanan.attributes.NamaLayanan,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = AlegreyaSansFontFamily,
                                                color = Color.White
                                    )


                                    Text(
                                        text = "Rp " + layanan.attributes.Harga.toString(),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = AlegreyaSansFontFamily,
                                                color = Color.White
                                    )
                                }

                                Column(modifier = Modifier.padding(top = 5.dp)) {

                                    Row {

                                        IconButton(onClick = {
                                            navController.navigate(
                                                "EditLayanan/"
                                                        + layanan.id + "/"
                                                        + layanan.attributes.NamaLayanan + "/"
                                                        + layanan.attributes.DeskripsiLayanan + "/"
                                                        + layanan.attributes.Harga
                                            )

                                        }) {
                                            Icon(
                                                Icons.Default.Edit,
                                                contentDescription = "Edit",
                                                tint =  Color.White

                                            )
                                        }

                                        IconButton(onClick = {
                                            val retrofit = Retrofit.Builder()
                                                .baseUrl(baseUrl)
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build()
                                                .create(LayananService::class.java)
                                            val call = retrofit.delete(layanan.id)
                                            call.enqueue(object : Callback<LayananRespon> {
                                                override fun onResponse(
                                                    call: Call<LayananRespon>,
                                                    response: Response<LayananRespon>
                                                ) {
                                                    print(response.code())
                                                    if (response.isSuccessful) {
                                                        listLayanan.remove(layanan)
                                                    } else {
                                                        print("error delete")
                                                        var toast = Toast.makeText(
                                                            context,
                                                            "Error deleting: ${
                                                                response.errorBody()?.string()
                                                            }",
                                                            Toast.LENGTH_SHORT
                                                        ).show()

                                                    }
                                                }

                                                override fun onFailure(
                                                    call: Call<LayananRespon>,
                                                    t: Throwable
                                                ) {
                                                    print(t.message)
                                                }

                                            })
                                        }) {
                                            Icon(
                                                Icons.Default.Delete,
                                                contentDescription = "Delete",
                                                tint =  Color.White,
                                                modifier = Modifier.padding(end = 8.dp)
                                            )

                                        }

                                    }

                                }

                            }


                        }

                    }
                }


            }

        }
    }
}


