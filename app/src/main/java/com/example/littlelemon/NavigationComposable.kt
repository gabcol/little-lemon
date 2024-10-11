package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
//fun Navigationapp(isUserLoggedIn : Boolean, callback: () ->  Unit )
fun NavigationComposable( isUserLoggedIn: Boolean, sharedPreferences: SharedPreferences)
{

    val navController = rememberNavController()
    var destination = "home"

    if (!isUserLoggedIn ){
        destination = "onboarding"
    }


    NavHost(navController, startDestination =  destination) {

        composable("home") {

                Home()
        }

        composable("onboarding") {

            Onboarding(sharedPreferences, {
                navController.navigate("home" )
            } )

        }

        composable("profile") {

            Profile("Pippo", "Franco", "test@email.com")

        }
    }
}

