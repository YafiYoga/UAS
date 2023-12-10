package com.example.uas.frontEnd

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.uas.respon.LoginRespon
import com.example.uas.PreferencesManager
import com.example.uas.R
import com.example.uas.data.RegisterData
import com.example.uas.service.RegisterService
import com.example.uas.ui.theme.AlegreyaFontFamily
import com.example.uas.ui.theme.AlegreyaSansFontFamily
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(navController: NavController, context: Context = LocalContext.current) {
    val preferencesManager = remember { PreferencesManager(context = context) }
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf(TextFieldValue("")) }



        Surface(
            color = Color(0xFF6C3428),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize()

            ) {
                /// Background Image
                //  Image(
                //    painter = painterResource(id = R.drawable.bg3),
                //  contentDescription = null,
                //contentScale = ContentScale.FillBounds,
                // modifier = Modifier.fillMaxSize()
                //)

                /// Content

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                ) {

                    // Logo
                    Image(
                        painter = painterResource(id = R.drawable.logo3),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 54.dp)
                            .height(100.dp)
                            .align(Alignment.Start)
                            .offset(x = (-20).dp)
                    )

                    Text(
                        text = "Register",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = AlegreyaFontFamily,
                            fontWeight = FontWeight(700),
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Text(
                        "buatlah akun untuk bisa masuk di aplikasi ini",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = AlegreyaSansFontFamily,
                            fontWeight = FontWeight(700),
                            color = Color.White
                        ),
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(bottom = 24.dp)
                    )


                    // Text Field
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .background(Color.White)
                    )
                    OutlinedTextField(value = username, onValueChange = { newText ->
                        username = newText
                    }, label = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "person",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Username",
                                style = TextStyle(fontFamily = AlegreyaSansFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                            )
                        }
                    },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            textColor = Color.White,
                        )
                    )
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .background(Color.White)
                    )
                    OutlinedTextField(
                        value = password, onValueChange = { newText ->
                            password = newText
                        },
                        label = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "lock",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    "Password",
                                    style = TextStyle(fontFamily = AlegreyaSansFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    imageVector = if (passwordVisibility) Icons.Default.Face else Icons.Default.Face,
                                    contentDescription = "Toggle Password Visibility",
                                    tint = Color.White
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            textColor = Color.White,
                        ))
                    Spacer(
                        modifier = Modifier
                            .height(16.dp)
                            .background(Color.White)
                    )
                    OutlinedTextField(value = email, onValueChange = { newText ->
                        email = newText
                    }, label = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "email",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "email",
                                style = TextStyle(fontFamily = AlegreyaSansFontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                            )
                        }
                    },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            textColor = Color.White,
                        )
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                        Row {

                            ElevatedButton(shape = MaterialTheme.shapes.large,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                ), onClick = {
                                var baseUrl = "http://10.0.2.2:1337/api/"
                                val retrofit = Retrofit.Builder()
                                    .baseUrl(baseUrl)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build()
                                    .create(RegisterService::class.java)
                                val call =
                                    retrofit.saveData(
                                        RegisterData(
                                            email.text,
                                            username.text,
                                            password.text
                                        )
                                    )
                                call.enqueue(object : Callback<LoginRespon> {
                                    override fun onResponse(
                                        call: Call<LoginRespon>,
                                        response: Response<LoginRespon>
                                    ) {
                                        print(response.code())
                                        if (response.code() == 200) {
                                            var toast = Toast.makeText(
                                                context,
                                                "Register Sukses",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            navController.navigate("Register")
                                        } else if (response.code() == 400) {
                                            print(response.errorBody())
                                            var toast = Toast.makeText(
                                                context,
                                                response.message(),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }

                                    override fun onFailure(call: Call<LoginRespon>, t: Throwable) {
                                        print(t.message)
                                    }

                                })
                            }) {
                                Text(
                                    "Register",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(500),
                                    fontFamily = AlegreyaSansFontFamily,
                                    color = Color(0xFF6C3428)
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            ElevatedButton(shape = MaterialTheme.shapes.large,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                ),onClick = {
                                preferencesManager.saveData("jwt", "")
                                navController.navigate("Login")
                            }) {
                                Text(
                                    "Batal",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight(500),
                                    fontFamily = AlegreyaSansFontFamily,
                                    color = Color(0xFF6C3428)
                                )
                            }
                        }
                    }
                }


            }
        }






