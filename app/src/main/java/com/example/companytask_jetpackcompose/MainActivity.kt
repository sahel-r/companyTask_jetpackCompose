package com.example.companytask_jetpackcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.companytask_jetpackcompose.ui.theme.CompanyTask_jetpackComposeTheme
import com.example.companytask_jetpackcompose.features.ProfileScreen
import com.example.companytask_jetpackcompose.features.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompanyTask_jetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val sharedPreferences = getPreferences(Context.MODE_PRIVATE)
                    val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

                    NavHost(
                        navController = navController,
                        startDestination = if (isLoggedIn) "profile_screen" else "signup_screen"
                    ) {
                        composable("signup_screen") {
                            SignUpScreen(navController,sharedPreferences)
                        }
                        composable("profile_screen") {
                            ProfileScreen(navController,sharedPreferences)
                        }
                    }
                }
            }
        }
    }
}

