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

//import io.ktor.client.HttpClient
//import io.ktor.client.call.body
//import io.ktor.client.engine.android.Android
//
//import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
//import io.ktor.client.request.get
//import io.ktor.http.ContentType
//import io.ktor.serialization.kotlinx.json.json
//import kotlinx.coroutines.launch

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

//    fun isUserLoggedIn(): Boolean? {
//        // check shared preferences
//        return userLoggedInLiveData.value
//    }

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

    //   var menuItemsFromJason : List<MenuItemFromJason>? = null
//        this.lifecycleScope.launch(Dispatchers.IO) {
//            // Perform database operations here
//
//            menuItemsFromJason =  fetchMenu()
//
//            //myViewModel.addItems(menuItemsFromJason)
//            val menuItemRooms: List<MenuItemRoom>? = menuItemsFromJason?.map(MenuItemFromJason::toMenuItemRoom)
//
//            menuItemRooms?.let {
//                val dao = database.menuItemDao()
//                if (dao.isEmpty())
//                {
//                    dao.insertAll(*menuItemRooms.toTypedArray())
//                    Log.e("DBSIZE", dao.tablesize().toString())
//                }
//                // Update UI on the main thread
//                // withContext(Dispatchers.Main) {
//                // Update UI elements with the data
//                // }
//            }
//        }

    }

// DB
//    val database by lazy {
//        Room.databaseBuilder(this, MenuDatabase::class.java, "database").build()
//    }











}

@Preview(showBackground = true)
@Composable
fun LittleLemonPreview() {
    LittleLemonTheme {
       // Onboarding(sharedPreferences = SharedPreference, callback = {})
    }
}