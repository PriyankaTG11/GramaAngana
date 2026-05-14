package com.example.gramaangana.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gramaangana.navigation.Routes
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    navController: NavController
) {

    val auth = FirebaseAuth.getInstance()

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var message by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()

            .background(

                Brush.verticalGradient(
                    listOf(
                        Color(0xFFE8F5E9),
                        Color.White
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(
                text = "Grama-Angana",

                fontSize = 34.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFF1B5E20)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text =
                    "Community Space Management",

                color = Color.DarkGray,

                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Card(

                shape =
                    RoundedCornerShape(28.dp),

                colors =
                    CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                elevation =
                    CardDefaults.cardElevation(
                        defaultElevation = 10.dp
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Welcome Back",

                        fontSize = 26.sp,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    OutlinedTextField(
                        value = email,

                        onValueChange = {
                            email = it
                        },

                        leadingIcon = {

                            Icon(
                                Icons.Default.Email,
                                contentDescription = null
                            )
                        },

                        label = {
                            Text("Email")
                        },

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    OutlinedTextField(
                        value = password,

                        onValueChange = {
                            password = it
                        },

                        leadingIcon = {

                            Icon(
                                Icons.Default.Lock,
                                contentDescription = null
                            )
                        },

                        label = {
                            Text("Password")
                        },

                        visualTransformation =
                            PasswordVisualTransformation(),

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp)
                    )

                    Spacer(
                        modifier =
                            Modifier.height(24.dp)
                    )

                    Button(

                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                    Color(0xFF2E7D32)
                            ),

                        shape =
                            RoundedCornerShape(16.dp),

                        modifier =
                            Modifier.fillMaxWidth(),

                        onClick = {

                            auth.signInWithEmailAndPassword(
                                email,
                                password
                            )

                                .addOnSuccessListener {

                                    navController.navigate(
                                        Routes.Home.route
                                    )
                                }

                                .addOnFailureListener {

                                    message =
                                        it.message.toString()
                                }
                        }
                    ) {

                        Text(
                            "Login",
                            fontSize = 18.sp
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(12.dp)
                    )

                    OutlinedButton(

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(16.dp),

                        onClick = {

                            auth.createUserWithEmailAndPassword(
                                email,
                                password
                            )

                                .addOnSuccessListener {

                                    message =
                                        "Registration Successful"
                                }

                                .addOnFailureListener {

                                    message =
                                        it.message.toString()
                                }
                        }
                    ) {

                        Text(
                            "Create Account"
                        )
                    }

                    Spacer(
                        modifier =
                            Modifier.height(16.dp)
                    )

                    Text(
                        text = message,

                        color = Color.Red
                    )
                }
            }
        }
    }
}