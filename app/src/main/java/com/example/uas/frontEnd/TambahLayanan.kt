package com.example.uas.frontEnd



import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uas.BottomNavigasi
import com.example.uas.PreferencesManager
import com.example.uas.R
import com.example.uas.data.LayananData
import com.example.uas.data.LayananDataWrapper
import com.example.uas.respon.LayananRespon
import com.example.uas.ui.theme.AlegreyaSansFontFamily
import com.google.gson.Gson
import com.example.uas.service.LayananService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahLayanan(navController: NavController, context: Context = LocalContext.current) {
    val baseColor = Color(0xFF6C3428)
    val NamaLayanan = remember { mutableStateOf(TextFieldValue("")) }
    val DeskripsiLayanan = remember { mutableStateOf(TextFieldValue("")) }
    val Harga = remember { mutableStateOf(TextFieldValue("")) }

    val preferencesManager = remember { PreferencesManager(context = context) }
    val layananId = preferencesManager.getData("layananID")
    val primaryColorOrg = Color(0xFFFF5F00)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Tambah Layanan ", modifier = Modifier.padding(top = 8.dp), fontWeight = FontWeight.Bold, fontSize = 24.sp,
                        fontFamily = AlegreyaSansFontFamily)
                    IconButton(modifier = Modifier.padding(start = 320.dp), onClick = {
                        preferencesManager.saveData("jwt", "")
                        navController.navigate("login")
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
            Spacer(modifier = Modifier.height(24.dp))
        },
        bottomBar = {
            BottomNavigasi(navController)
        },


    )

    { innerPadding ->
        Spacer(modifier = Modifier.padding(16.dp))
        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),

                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "*Nama Layanan",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color(0xFF6C3428),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                )
                OutlinedTextField(
                    value = NamaLayanan.value,
                    onValueChange = {
                        NamaLayanan.value = it
                    },
                    singleLine = true,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFF6C3428),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "*Potong Rambut*",
                            style = TextStyle(
                                color = Color(0xFF6C3428),
                                fontFamily = AlegreyaSansFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6C3428),
                        focusedLabelColor = Color(0xFF6C3428),
                        textColor = Color(0xFF6C3428),
                    )
                )
                Text(
                    text = "*Deskripsi Layanan",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color(0xFF6C3428),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                )
                OutlinedTextField(
                    value = DeskripsiLayanan.value,
                    onValueChange = {
                        DeskripsiLayanan.value = it
                    },
                    singleLine = false,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFF6C3428),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "*Bisa Memotong Rambut berbagai macam model rambut yang lagi trend*",
                            style = TextStyle(
                                color = Color(0xFF6C3428),
                                fontFamily = AlegreyaSansFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6C3428),
                        focusedLabelColor = Color(0xFF6C3428),
                        textColor = Color(0xFF6C3428),
                    )
                )
                Text(
                    text = "*Harga Layanan",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AlegreyaSansFontFamily,
                        color = Color(0xFF6C3428),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                )
                OutlinedTextField(
                    value = Harga.value,
                    onValueChange = {
                        Harga.value = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFF6C3428),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text(
                            text = "*10000*",
                            style = TextStyle(
                                color = Color(0xFF6C3428),
                                fontFamily = AlegreyaSansFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6C3428),
                        focusedLabelColor = Color(0xFF6C3428),
                        textColor = Color(0xFF6C3428),
                    )
                )
                Spacer(modifier = Modifier.padding(10.dp))

                ElevatedButton(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6C3428),


                        ),
                    shape = RoundedCornerShape(8.dp),
                    onClick = {
                        var baseUrl = "http://10.0.2.2:1337/api/"
                        val retrofit = Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(LayananService::class.java)
                        try {
                            val layananData = LayananDataWrapper(
                                LayananData(
                                    NamaLayanan.value.text,
                                    DeskripsiLayanan.value.text,
                                    Harga.value.text.toInt()
                                )
                            )
                            val call = retrofit.createLayanan(layananData)
                            call.enqueue(object : Callback<LayananRespon> {
                                override fun onResponse(
                                    call: Call<LayananRespon>,
                                    response: Response<LayananRespon>
                                ) {
                                    print(response.code())
                                    if (response.isSuccessful) {
                                        navController.navigate("HomePageBarber")
                                    } else {
                                        print("error create")
                                        var toast = Toast.makeText(
                                            context,
                                            "Error creating: ${response.errorBody()?.string()}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                                override fun onFailure(call: Call<LayananRespon>, t: Throwable) {
                                    print(t.message)
                                }
                            })
                        } catch (e: NumberFormatException) {
                            Toast.makeText(
                                context,
                                "Error: Invalid price format",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    },
                )
                {
                    Text(
                        text = "Tambah Layanan",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = AlegreyaSansFontFamily,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}



