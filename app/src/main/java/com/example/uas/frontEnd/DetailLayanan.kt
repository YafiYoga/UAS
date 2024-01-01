package com.example.uas.frontEnd


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uas.BottommNavigasi
import com.example.uas.PreferencesManager
import com.example.uas.R
import com.example.uas.data.LayananData
import com.example.uas.data.LayananDataWrapper
import com.example.uas.data.PemesananData
import com.example.uas.data.PemesananDataWrapper
import com.example.uas.respon.LayananRespon
import com.example.uas.respon.PemesananRespon
import com.example.uas.service.LayananService
import com.example.uas.service.PemesananService
import com.example.uas.ui.theme.AlegreyaSansFontFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailLayanan(
    navController: NavController,
    layananid: String?,
    NamaParameter: String?,
    DeskripsiParameter: String?,
    HargaParameter: String?,


    context: Context = LocalContext.current
) {
    val baseColor = Color(0xFF6C3428)
    var NamaLayanan by remember { mutableStateOf(NamaParameter ?: "") }
    var DeskripsiLayanan by remember { mutableStateOf(DeskripsiParameter ?: "") }
    var Harga by remember { mutableStateOf(HargaParameter ?: "") }




    val preferencesManager = remember { PreferencesManager(context = context) }

    val NamaPemesan = remember { mutableStateOf(TextFieldValue("")) }
//    val TglPemesanan = remember { mutableStateOf(TextFieldValue("")) }
//    val JamPemesanan = remember { mutableStateOf(TextFieldValue("")) }

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    var TglPemesanan by remember { mutableStateOf("Select Date") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val formattedMonth = String.format("%02d", mMonth + 1)
            val formattedDay = String.format("%02d", mDayOfMonth)
            TglPemesanan = "$mYear-$formattedMonth-$formattedDay"
        }, mYear, mMonth, mDay
    )

    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]


    // Value for storing time as a string
    var JamPemesanan by remember { mutableStateOf("Select Time") }

    val mTimePickerDialog = TimePickerDialog(
        context,
        {_, mHour : Int, mMinute: Int ->
            JamPemesanan = String.format("%02d:%02d:00.000", mHour, mMinute)
        }, mHour, mMinute, true
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { navController.navigate("Layanan") }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Text(
                            text = "Detail Layanan", fontWeight = FontWeight.Bold, fontSize = 24.sp,
                        )
                    }
                },

                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = baseColor,
                    titleContentColor = Color.White,
                ),

            )
        },bottomBar = {
            BottommNavigasi(navController)
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(18.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = NamaLayanan,
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFF6C3428)
                )

                Text(
                    text = DeskripsiLayanan,
                    modifier = Modifier.padding(top = 12.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFF6C3428)
                )

                Text(

                    text =  "Rp $Harga",
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFF6C3428)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "*Nama Pemesan",
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
                    value = NamaPemesan.value,
                    onValueChange = {
                        NamaPemesan.value = it
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
                            text = "*Yafi Yoga*",
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
                    text = "*Tgl Pemesanan",
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
                OutlinedTextField(value = TglPemesanan,
                    onValueChange = { TglPemesanan = it },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxWidth()
                        .height(65.dp)
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
                    ),
                    placeholder = {
                        Text(
                            text = "*12/30/2023*", style = TextStyle(
                                color = Color(0xFF6C3428),
                                fontFamily = AlegreyaSansFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    },
                    trailingIcon = {
                        Icon(Icons.Default.DateRange,
                            contentDescription  ="date picker",
                            Modifier.clickable { mDatePickerDialog.show() })
                    })

                Text(
                    text = "*Jam Pemesanan",
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
                OutlinedTextField(value = JamPemesanan,
                    onValueChange = { JamPemesanan = it },
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
                    ),
                    placeholder = {
                        Text(
                            text = "*12/30/2023*", style = TextStyle(
                                color = Color(0xFF6C3428),
                                fontFamily = AlegreyaSansFontFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight(500)
                            )
                        )
                    },
                    trailingIcon = {
                        Icon(Icons.Default.Add,
                            "time picker",

                            Modifier.clickable { mTimePickerDialog.show() })
                    })
                Spacer(modifier = Modifier.padding(10.dp))

                ElevatedButton(
                    modifier = Modifier
                        .padding(bottom = 17.dp)
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
                            .create(PemesananService::class.java)
                        val pemesananData = PemesananDataWrapper(
                            PemesananData(
                                NamaPemesan.value.text,
                                TglPemesanan,
                                JamPemesanan,
                                layananid!!.toInt()
                            )
                        )
                        val call = retrofit.createPemesanan(pemesananData)
                        call.enqueue(object : Callback<PemesananRespon> {
                            override fun onResponse(
                                call: Call<PemesananRespon>,
                                response: Response<PemesananRespon>
                            ) {
                                print(response.code())
                                if (response.isSuccessful) {
                                    val resp = response.body()
                                    navController.navigate("HomePagePelanggan")
                                } else {
                                    print("error create")
                                    var toast = Toast.makeText(
                                        context,
                                        "Error creating: ${response.errorBody()?.string()}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<PemesananRespon>, t: Throwable) {
                                print(t.message)
                            }
                        })
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