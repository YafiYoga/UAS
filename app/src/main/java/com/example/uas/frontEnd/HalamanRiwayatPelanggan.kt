package com.example.uas.frontEnd



import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uas.BottommNavigasi
import com.example.uas.PreferencesManager
import com.example.uas.R
import com.example.uas.respon.PemesananRespon
import com.example.uas.respon.layanan
import com.example.uas.respon.pemesanan
import com.example.uas.service.PemesananService
import com.example.uas.ui.theme.AlegreyaSansFontFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HalamanRiwayatPelanggan(navController: NavController, context: Context = LocalContext.current) {
    val baseColor = Color(0xFF6C3428)
    //var listUser: List<UserRespon> = remember

    var search by remember { mutableStateOf(TextFieldValue("")) }
    val preferencesManager = PreferencesManager(context) // Replace with the appropriate context
    val layananId = preferencesManager.getData("layananId")
    val listPemesanan = remember { mutableStateListOf<PemesananRespon>() }
    //var listUser: List<UserRespon> by remember { mutableStateOf(List<UserRespon>()) }
 var baseUrl = "http://10.0.2.2:1337/api/"
//    //var baseUrl = "http://10.217.17.11:1337/api/"
//    val retrofit = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//        .create(PemesananService::class.java)
//    val call = retrofit.getData(search.text,"*")
//    call.enqueue(object : Callback<pemesanan<List<PemesananRespon>>> {
//        override fun onResponse(
//            call: Call<pemesanan<List<PemesananRespon>>>,
//            response: Response<pemesanan<List<PemesananRespon>>>
//        ) {
//            if (response.isSuccessful) {
//                Pemesanan.clear()
//                response.body()?.data!!.forEach { PemesananRespon: PemesananRespon ->
//                    listPemesanan.add(PemesananRespon)
//                }
//            } else {
//                print("Error getting data. Code: ${response.code()}")
//                Toast.makeText(
//                    context,
//                    "Error getting data. Code: ${response.code()}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//
//
//        override fun onFailure(call: Call<pemesanan<List<PemesananRespon>>>, t: Throwable) {
//            print(t.message)
//            Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
//        }
//        fun HalamanRiwayatPelanggan() {
//            listPemesanan.clear()
//        }
//    })
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Halaman Transaksi ",
                        modifier = Modifier.padding(top = 5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontFamily = AlegreyaSansFontFamily
                    )
                    IconButton(modifier = Modifier.padding(start = 320.dp), onClick = {
                        navController.navigate("SelamatDatang")
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
            BottommNavigasi(navController)
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                            tint = baseColor,

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier

                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF6C3428))
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp), verticalAlignment = Alignment.CenterVertically

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo3),
                            contentDescription = null,
                            modifier = Modifier
                                .size(72.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Column(
                        ) {

                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text =  "Nama Pemesan :  "+preferencesManager.getData("nama"),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AlegreyaSansFontFamily,
                                color = Color.White
                            )

                            val rawDate = preferencesManager.getData("Tgl")
                            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val date = dateFormat.parse(rawDate)
                            val indonesianLocale = Locale("id", "ID")
                            val formattedDate = SimpleDateFormat("EEEE, dd MMMM yyyy", indonesianLocale).format(date)


                            Text(
                                text = "Tgl Pemesanan :  ${preferencesManager.getData("Tgl")}",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AlegreyaSansFontFamily,
                                color = Color.White
                            )

                           // val jamPemesanan = pemesanan.attributes.JamPemesanan
                          //  val timeParts = jamPemesanan.split(":")
                          //  val hours = timeParts[0].toInt()
                           // val minutes = timeParts[1].toInt()
                           // val formattedTime = String.format("%02d:%02d", hours, minutes)
                           // val timeFormat = SimpleDateFormat("HH:mm", Locale("id", "ID"))
                            //val formattedTime = timeFormat.format(preferencesManager.getData("Jam"))
                            val rawTime = preferencesManager.getData("Jam")
                            val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                            val formattedTime = timeFormat.format(timeFormat.parse(rawTime))
                            Text(
                                text = "Jam Pemesanan :  $formattedTime",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AlegreyaSansFontFamily,
                                color = Color.White
                            )
                            Text(
                                text = "Nama Layanan: "+preferencesManager.getData("Namalayanan"),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AlegreyaSansFontFamily,
                                color = Color.White
                            )
                            Text(
                                text = "Harga Layanan: "+preferencesManager.getData("Hargalayanan"),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = AlegreyaSansFontFamily,
                                color = Color.White
                            )

                        }
                    }
                    }
                }
            }
        }
    }


