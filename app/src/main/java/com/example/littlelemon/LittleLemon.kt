package com.example.littlelemon

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.room.Room

import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch

class LittleLemon : ComponentActivity() {

    private val menuItemsLiveData = MutableLiveData<List<MenuItemNetwork>>()

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
                    NavigationComposable(isUserLoggedIn(), sharedPreferences)
                }
            }
        }

        //DB
//        val database by lazy {
//            Room.databaseBuilder(this, AppDatabase::class.java, "database").build()
//        }
//
//        lifecycleScope.launch {
//
//
//            var myMenu: List<MenuItemNetwork> = fetchMenu()
//            val menuItems: List<MenuItem> = myMenu.map(MenuItemNetwork::toMenuItemRoom)
//           // val dao: MenuItemDao
//
//
//        runOnUiThread {
//          //  menuItemsLiveData.value = menuItems
//        }
//        }

    }





//suspend fun fetchMenu() : List<MenuItemNetwork> {
//    val httpClient = HttpClient(Android)    {
//        install(ContentNegotiation) {
//            json(contentType = ContentType("text", "plain"))
//        }
//    }
//
//    val dataURL = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
//    val menuNetwork : MenuNetwork = httpClient.get(dataURL).body<MenuNetwork>()
//
//
//    return menuNetwork.menu
//}


    fun isUserLoggedIn(): Boolean {
        // check shared preferences
        userLoggedInLiveData.value
        return false
    }



}

@Preview(showBackground = true)
@Composable
fun LittleLemonPreview() {
    LittleLemonTheme {
       // Onboarding(sharedPreferences = SharedPreference, callback = {})
    }
}