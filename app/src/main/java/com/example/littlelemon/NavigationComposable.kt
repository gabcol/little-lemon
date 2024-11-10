package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComposable(context: Context)
{


    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var destination = Onboarding.route

    if (sharedPreferences.getBoolean("userloggedin", false)) {
        destination = Home.route
    }

    val navController = rememberNavController()
   // var destination = Home.route
   //    if (!isUserLoggedIn!!){
   //        destination = "onboarding"
   //    }


    NavHost(navController, startDestination =  destination) {

        composable("home") {

                Home(){
                    navController.navigate(Profile.route)
                }
        }



        composable("onboarding") {

            Onboarding(context) {

                navController.navigate(Home.route)
            }

        }

        composable("profile") {

            Profile(context) { navController.navigate(Onboarding.route) }


        }
    }
}

