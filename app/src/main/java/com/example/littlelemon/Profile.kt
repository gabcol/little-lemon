package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import kotlinx.serialization.json.Json

@Composable
fun Profile(context: Context, callback: ()->Unit ) {

    val  sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)

    val jstr = """
        {
          "firstName" : "default",
          "lastName" : "default",
          "email" : "defaultemail@test.com"
        }
    """.trimIndent()

    val jsonString = sharedPreferences.getString("userprofile", jstr).toString()

    val userdata =   Json.decodeFromString<UserProfile>(jsonString)

    val firstName = remember {
        mutableStateOf(userdata.firstName)
    }

    val lastName = remember {
        mutableStateOf(userdata.lastName)
    }

    val email = remember {
        mutableStateOf(userdata.email)
    }

    val scrollState = rememberScrollState()


    Column(
        Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Row(Modifier.fillMaxWidth(1.0f).height(25.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center)  {
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo", modifier = Modifier.scale(2.0f))
        }

        Text(text = "Personal Information",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineSmall)
        OutlinedTextField(
            enabled = false,
            readOnly = true,
            value = firstName.value,
            onValueChange ={},
            label = { Text(text = "First Name")},
            singleLine = true,
            placeholder = { Text(text = "Your name")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            enabled = false,
            readOnly = true,
            value = lastName.value!!,
            onValueChange ={},
            label = { Text(text = "Last Name")},
            singleLine = true,
            placeholder = { Text(text = "Your surname")},
            modifier = Modifier.fillMaxWidth())

        OutlinedTextField(
            enabled = false,
            readOnly = true,
            value = email.value!!,
            onValueChange ={},
            label = { Text(text = "Email")},
            singleLine = true,
            placeholder = { Text(text = "your email")},
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.size(40.dp))

        Button(onClick = {


            sharedPreferences.edit(commit = true) {

                // clear share preference
                sharedPreferences.edit().clear().apply()

                //set user as logged out
                putBoolean("userloggedin", false)
            }

            callback()


        },
//            shape = RoundedCornerShape(20.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)) {
            Text(text = "Log Out"
//                ,fontSize = 18.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF333333)
            )
        }
    }



}