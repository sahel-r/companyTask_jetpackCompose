package com.example.companytask_jetpackcompose.features

import android.content.SharedPreferences
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController, sharedPreferences: SharedPreferences) {

    val firstName by rememberSaveable { mutableStateOf(sharedPreferences.getString("نام", "Unknown") ?: "Unknown") }
    val lastName by rememberSaveable { mutableStateOf(sharedPreferences.getString("نام خانوادگی", "Unknown") ?: "Unknown") }
    val birthDate by rememberSaveable { mutableStateOf(sharedPreferences.getString("سال تولد", "Unknown") ?: "Unknown") }
    val nationalCode by rememberSaveable { mutableStateOf(sharedPreferences.getString("کد ملی", "Unknown") ?: "Unknown") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.78f),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement.Center,

        ) {
        Text("اطلاعات ثبت نام")
        Spacer(modifier = Modifier.height(16.dp))
        Text("نام :$firstName")
        Text("نام خانوادگی: $lastName")
        Text("سال تولد: $birthDate")
        Text("کد ملی: $nationalCode")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.padding(top =30.dp , bottom = 8.dp),
            onClick = {
                with(sharedPreferences.edit()) {
                    putBoolean("isLoggedIn", false)
                    apply()
                }

                navController.navigate("signup_screen") {
                    popUpTo("signup_screen") { inclusive = true }
                }
            }
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = "خروج")
            Spacer(modifier = Modifier.width(8.dp))
            Text("خروج")
        }


    }
}