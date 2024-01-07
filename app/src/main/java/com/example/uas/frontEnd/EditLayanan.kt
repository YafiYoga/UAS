package com.example.uas.frontEnd



import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uas.BottomNavigasi
import com.example.uas.PreferencesManager
import com.example.uas.data.LayananData
import com.example.uas.data.LayananDataWrapper
import com.example.uas.respon.LayananRespon
import com.example.uas.ui.theme.AlegreyaSansFontFamily
import com.example.uas.service.LayananService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLayanan(navController: NavController, layananid : String?, NamaParameter: String?, HargaParameter: String?, DeskripsiParameter: String?, context: Context = LocalContext.current) {
    val baseColor = Color(0xFF6C3428)
    var NamaLayanan by remember { mutableStateOf(NamaParameter?:"") }
    var DeskripsiLayanan by remember { mutableStateOf(HargaParameter?:"") }
    var Harga by remember { mutableStateOf(DeskripsiParameter?:"") }

    val preferencesManager = remember { PreferencesManager(context = context) }
    val primaryColorOrg = Color(0xFFFF5F00)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(text = "Edit Layanan ", modifier = Modifier.padding(top = 8.dp), fontWeight = FontWeight.Bold, fontSize = 24.sp,
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
                    value = NamaLayanan,
                    onValueChange = { newText ->
                        NamaLayanan = newText
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6C3428),
                        focusedLabelColor = Color(0xFF6C3428),
                        textColor = Color(0xFF6C3428),
                    )
                )

                OutlinedTextField(
                    value = DeskripsiLayanan,
                    onValueChange = { newText ->
                        DeskripsiLayanan = newText
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6C3428),
                        focusedLabelColor = Color(0xFF6C3428),
                        textColor = Color(0xFF6C3428),
                    )
                )
                OutlinedTextField(
                    value = Harga,
                    onValueChange = { newText ->
                        Harga = newText
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
                        val layananData = LayananDataWrapper(LayananData(NamaLayanan, DeskripsiLayanan, Harga.toInt()))
                        val call = retrofit.save(layananid, layananData)
                        call.enqueue(object : Callback<LayananRespon> {
                            override fun onResponse(
                                call: Call<LayananRespon>,
                                response: Response<LayananRespon>
                            ) {
                                print(response.code())
                                if (response.isSuccessful) {
                                    navController.navigate("HomePageBarber")
                                } else {
                                    print("error update")
                                    var toast = Toast.makeText(
                                        context,
                                        "Error updating: ${response.errorBody()?.string()}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<LayananRespon>, t: Throwable) {
                                print(t.message)
                            }

                        })
                    },
                )
                {
                    Text(
                        text = "Edit Layanan",
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


