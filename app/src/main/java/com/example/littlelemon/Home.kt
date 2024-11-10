package com.example.littlelemon

 import android.util.Log
 import androidx.compose.foundation.Image
 import androidx.compose.foundation.background
 import androidx.compose.foundation.clickable
 import androidx.compose.foundation.horizontalScroll
 import androidx.compose.foundation.layout.Arrangement
 import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.Row
 import androidx.compose.foundation.layout.Spacer
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.padding
 import androidx.compose.foundation.layout.size
 import androidx.compose.foundation.layout.width
 import androidx.compose.foundation.lazy.LazyColumn
 import androidx.compose.foundation.rememberScrollState
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.foundation.verticalScroll
 import androidx.compose.material.icons.Icons
 import androidx.compose.material.icons.filled.AccountCircle
 import androidx.compose.material.icons.filled.Search
 import androidx.compose.material3.Button
 import androidx.compose.material3.ButtonDefaults
 import androidx.compose.material3.Card
 import androidx.compose.material3.CardDefaults
 import androidx.compose.material3.Icon
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.OutlinedTextField
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.runtime.LaunchedEffect
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.text.font.FontWeight
 import androidx.lifecycle.viewmodel.compose.viewModel
 import androidx.compose.runtime.*
 import androidx.compose.runtime.livedata.observeAsState
 import androidx.compose.ui.draw.clip
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.res.painterResource

@Composable
fun Home(callback: () -> Unit) {

    val viewModel: MyViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllDatabaseMenuItems().observeAsState(emptyList()).value

    val searchPhrase = remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        viewModel.fetchMenuData()
    }








    Column() {
        Header(callback)
        UpperPanel(){
            searchPhrase.value = it
        }
        LowerPanel(databaseMenuItems, searchPhrase)
    }


}

@Composable
fun Header(callback: () -> Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Spacer(modifier = Modifier.width(50.dp))
        Image(painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(0.65f))

        Box(modifier = Modifier
            .size(50.dp)
            .clickable { callback() }){
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                tint = PrimaryGreen,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 2.dp))
        }


    }
}

@Composable
fun UpperPanel(search : (parameter: String)-> Unit) {
    val searchPhrase = remember {
        mutableStateOf("")
    }

    Log.e("search", "UpperPanel: ${searchPhrase.value}")
    Column(modifier = Modifier
        .background(PrimaryGreen)
        .padding(horizontal = 20.dp, vertical = 10.dp)) {
        Text(text = "Little Lemon", style = MaterialTheme.typography.headlineLarge, color = PrimaryYellow)
        Text(text = "New York", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        Row(Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with  a modern twist. Turkish, Italian, Indian and chinese recipes are our speciality.",
                modifier = Modifier.fillMaxWidth(0.7f),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge)
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = "Hero Image",
                modifier = Modifier.clip(RoundedCornerShape(16.dp))

            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        OutlinedTextField(value = searchPhrase.value,
            onValueChange = {
                searchPhrase.value = it
                search(searchPhrase.value)
            },
            placeholder = {
                Text(text = "Enter Search Phrase")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon")
            },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                backgroundColor = MaterialTheme.colors.background
//            ),
            modifier = Modifier.fillMaxWidth())

    }

}

@Composable
fun LowerPanel(databaseMenuItems: List<MenuItemRoom>, search: MutableState<String>) {
    val categories = databaseMenuItems.map {
        it.category.replaceFirstChar {character ->
            character.uppercase()
        }
    }.toSet()


    val selectedCategory = remember {
        mutableStateOf("")
    }


    val items = if(search.value == ""){
        databaseMenuItems

    }
    else{
        databaseMenuItems.filter {
            it.title.contains(search.value, ignoreCase = true)

        }


    }



    val filteredItems = if(selectedCategory.value == "" || selectedCategory.value == "all"){
        items
    }
    else{
        items.filter {
            it.category.contains(selectedCategory.value, ignoreCase = true)
        }
    }


    Column {
        MenuCategories(categories) {selectedCategory.value = it
        }
        Spacer(modifier = Modifier.size(2.dp))
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for (item in filteredItems){
                MenuItem2(item = item)
            }
        }

    }
}


@Composable
fun MenuCategories(categories: Set<String>, categoryLambda : (sel: String) -> Unit) {
    val cat = remember {
        mutableStateOf("")
    }


    Card(elevation = CardDefaults.cardElevation(defaultElevation = 10.dp), modifier = Modifier.fillMaxWidth()) {

        Column(Modifier.padding(horizontal = 20.dp, vertical = 10.dp)) {
            Text(text = "ORDER FOR DELIVERY", fontWeight = FontWeight.Bold)

            Row(modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                CategoryButton(category = "All"){
                    cat.value = it.lowercase()
                    categoryLambda(it.lowercase())
                }

                for (category in categories){
                    CategoryButton(category = category){
                        cat.value = it
                        categoryLambda(it)
                    }

                }

            }
        }
    }
}

@Composable
fun CategoryButton(category:String, selectedCategory: (sel: String) -> Unit) {
    val isClicked = remember{
        mutableStateOf(false)
    }
    Button(onClick = {
        isClicked.value = !isClicked.value
        selectedCategory(category)

    },

    colors =  ButtonDefaults.buttonColors(containerColor = PrimaryGreen, contentColor = Secondary2)) {
        Text(text = category)
    }


}


//@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem2(item: MenuItemRoom) {

    val itemDescription = if(item.description.length>100) {
        item.description.substring(0,100) + ". . ."
    }
    else{
        item.description
    }

    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .clickable {

            }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.fillMaxWidth(0.7f),
                verticalArrangement = Arrangement.SpaceBetween) {
                Text(text = item.title, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 10.dp))
                Text(text = itemDescription, modifier = Modifier.padding(bottom = 10.dp))
                Text(text = "$ ${item.price}", fontWeight = FontWeight.Bold)

            }

//            GlideImage(model = item.imageUrl,
//                contentDescription = "",
//                Modifier.size(100.dp, 100.dp),
//                contentScale = ContentScale.Crop)
        }
    }

}

@Composable
fun MenuItems(menuItemRooms: List<MenuItemRoom>?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            if (menuItemRooms != null) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                        for (menuItem in menuItemRooms) {
                            //MyMenuItem(item = menuItem)
                            Text(text = menuItem.title, fontWeight = FontWeight.Bold)
                            Text(text = "$  ${menuItem.price}")
                            Text(text = menuItem.description)

                        }
                    }
                }
            }
        }
    }
}





/*

@Composable
fun Home(database: MenuDatabase, callback: ()->Unit ) {


        val dao = database.menuItemDao()
        var listMenuItemsRoom : LiveData<List<MenuItemRoom>> = dao.getAll()

        var emp: String = "Ciao"

        listMenuItemsRoom.value?.let {
            if (it.isEmpty())
                emp = "NO"
            else
                emp = "YES"
        }

        Log.e("listMenuItemsEmpty", emp)
        Log.e("listMenuItems", listMenuItemsRoom.value.toString())
        //  Log.e("table size", dao.tablesize().toString() )



    Column() {
        Text(text = "Home")



        MenuItems(listMenuItemsRoom.value)

        Button(onClick = { callback() } ){
            Text("Go to profile")
        }
    }

}


@Composable
fun MenuItems(menuItemRooms: List<MenuItemRoom>?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {

            if (menuItemRooms != null) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                        for (menuItem in menuItemRooms) {
                            //MyMenuItem(item = menuItem)
                            Text(text = menuItem.title, fontWeight = FontWeight.Bold)
                            Text(text = "$  ${menuItem.price}")
                            Text(text = menuItem.description)

                        }
                    }
                }
            }
        }
    }
}





@Composable
fun MyMenuItem(item: MenuItemRoom) {
 Spacer(modifier = Modifier.width(10.dp))
 //Divider(color = Color.Gray, thickness = 1.dp)
 //Spacer(modifier = Modifier.width(10.dp))
 Row(modifier = Modifier
     .fillMaxWidth()
     .padding(top = 8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
  Column(modifier = Modifier.fillMaxWidth(0.5f)) {
   Text(text = item.title, fontWeight = FontWeight.Bold)
   Text(text = "$  ${item.price}")
   Text(text = item.description)
  }
  Column {
   Spacer(modifier = Modifier.width(10.dp))

//   import coil.compose.AsyncImage
//
//   AsyncImage(
//    model = item.image,
//    contentDescription = null,
//    modifier = Modifier.size(100.dp, 100.dp),
//    contentScale = ContentScale.Crop,
//   )
//   Spacer(modifier = Modifier.width(10.dp))
  }

 }

}
*/


