package com.example.companytask_jetpackcompose.features

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gmail.hamedvakhide.compose_jalali_datepicker.JalaliDatePickerDialog

@Composable
fun SignUpScreen(navController: NavController, sharedPreferences: SharedPreferences) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var nationalCode by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    val maxChar = 10

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.78f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("نام") },
                    textStyle = TextStyle(textDirection = TextDirection.Rtl)
                )

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("نام خانوادگی") },
                textStyle = TextStyle(textDirection = TextDirection.Rtl)

            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = { Text("سال تولد") },
                textStyle = TextStyle(textDirection = TextDirection.ContentOrRtl)
            )
        Button(onClick = { openDialog.value = true }) {
            Text(text = "انتخاب تاریخ")
        }
        JalaliDatePickerDialog(
            openDialog = openDialog,
            onSelectDay = { //it:JalaliCalendar
                Log.d("Date", "onSelect: ${it.day} ${it.monthString} ${it.year}")
            },
            onConfirm = {
                Log.d("Date", "onConfirm: ${it.day} ${it.monthString} ${it.year}")
            }
        )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nationalCode,
                onValueChange = {
                    if (it.length<=maxChar)
                        nationalCode = it
                     },
                label = { Text("کد ملی") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.padding(top = 30.dp, bottom = 8.dp),
                onClick = {
                    with(sharedPreferences.edit()) {
                        putString("نام", firstName)
                        putString("نام خانوادگی", lastName)
                        putString("سال تولد", birthDate)
                        putString("کد ملی", nationalCode)
                        putBoolean("isLoggedIn", true)
                        apply()
                    }

                    navController.navigate("profile_screen")
                }
            ) {
                Icon(Icons.Default.Send, contentDescription = "Submit")
                Spacer(modifier = Modifier.width(8.dp))
                Text("ثبت نام")
            }
        }
    }




