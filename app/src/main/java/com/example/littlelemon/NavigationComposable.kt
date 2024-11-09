package com.example.littlelemon

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Database

@Composable
fun NavigationComposable(isUserLoggedIn: Boolean?, sharedPreferences: SharedPreferences, database: MenuDatabase)
{

    val navController = rememberNavController()
    var destination = "home"

    if (!isUserLoggedIn!!){
        destination = "onboarding"
    }


    NavHost(navController, startDestination =  destination) {

        composable("home") {

                Home(database){
                    navController.navigate("profile" )
                }
        }

        composable("onboarding") {

            Onboarding(sharedPreferences, {
                navController.navigate("home" )
            } )

        }

        composable("profile") {

            Profile(sharedPreferences, {navController.navigate("onboarding") }  )

        }
    }
}

