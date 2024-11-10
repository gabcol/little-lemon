package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData

import com.example.littlelemon.ui.theme.LittleLemonTheme


class LittleLemon : ComponentActivity() {
    // val myViewModel = MyLittleLemonViewModel()


    //SHARED PREFERENCES
    private val userLoggedInLiveData = MutableLiveData<Boolean>()

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val sharedPreferencesListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "userloggedin") {
                userLoggedInLiveData.value = sharedPreferences.getBoolean(key, false)
            }
        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userLoggedInLiveData.value = sharedPreferences.getBoolean("userloggedin", false)
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)


        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComposable(context = applicationContext)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LittleLemonPreview() {
    LittleLemonTheme {
       // Onboarding(sharedPreferences = SharedPreference, callback = {})
    }
}