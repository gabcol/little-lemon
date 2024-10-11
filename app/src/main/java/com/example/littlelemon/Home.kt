package com.example.littlelemon

 import androidx.compose.foundation.layout.Column
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
// import androidx.compose.ui.platform.LocalContext
// import androidx.room.Room
// import io.ktor.client.HttpClient
// import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
// import io.ktor.client.call.body
// import io.ktor.client.engine.android.Android
// import io.ktor.client.request.get
// import io.ktor.http.ContentType
// import io.ktor.serialization.kotlinx.json.json
// import androidx.lifecycle.MutableLiveData
// import androidx.lifecycle.lifecycleScope
// import kotlinx.coroutines.launch

@Composable
fun Home() {

//    val context = LocalContext.current
//    val database by lazy {
//        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
//    }


  //      var menuItems: List<MenuItemNetwork> = fetchMenu()

//        runOnUiThread {
//          //  menuItemsLiveData.value = menuItems
//        }

    //var myMenu: List<MenuItemNetwork> = fetchMenu()
   // val menuItems: List<MenuItem> = myMenu.map(MenuItemNetwork::toMenuItemRoom)

    // dao.insertAll(menuItems)

    Column() {
        Text(text = "Home")
    }
}



//suspend fun fetchMenu() : List<MenuItemNetwork> {
//    val httpClient = HttpClient(Android)    {
//        install(ContentNegotiation) {
//            json(contentType = ContentType("text", "plain"))
//        }
//    }
//
//    val dataURL = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
//    // val menuNetwork : MenuNetwork = httpClient.get(dataURL).body()
//
//    val menuNetwork : MenuNetwork = httpClient.get(dataURL).body<MenuNetwork>()
//
//
//    return menuNetwork.menu
//}