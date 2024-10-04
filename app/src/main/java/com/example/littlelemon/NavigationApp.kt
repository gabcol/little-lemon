package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigationapp()
{

    val navController = rememberNavController()

    NavHost(navController, startDestination = "onboarding") {

        composable("onboarding") {

          Onboarding({
              navController.navigate("registration" )
          })

        }

        composable("registration") {

            Registration()

        }
    }
}

