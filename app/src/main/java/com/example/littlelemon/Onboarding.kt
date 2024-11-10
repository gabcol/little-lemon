package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Composable
fun Onboarding(context: Context, callback: ()->Unit ) {

    val message = remember { mutableStateOf("") }

    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

        Column(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp))
        {
            Row(Modifier.fillMaxWidth(0.6f)) {
                Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Little Lemon Logo")
            }
            Row(modifier = Modifier
                .height(150.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Let's get to know you",
                    style = MaterialTheme.typography.headlineLarge,
                    color = PrimaryGreen)
            }

            Text(text = "Personal Information",
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium)
            OutlinedTextField(
                value = firstName.value,
                onValueChange = { firstName.value = it },
                label = { Text(text = "First Name")},
                singleLine = true,
                modifier = Modifier.fillMaxWidth())

            OutlinedTextField(
                value = lastName.value,
                onValueChange = { lastName.value = it },
                label = { Text(text = "Last Name")},
                singleLine = true,
                //placeholder = { Text(text = "Doe")},
                modifier = Modifier.fillMaxWidth())

            OutlinedTextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = "Email")},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(40.dp))

            Button(onClick = {
                //  Validate registration form

                if(firstName.value.isNotBlank() && lastName.value.isNotBlank() && email.value.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
                    val userdata = UserProfile(firstName = firstName.value, lastName= lastName.value, email = email.value)

                    val userProfile = Json.encodeToString(userdata)

                    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)

                    sharedPreferences.edit(commit = true){
                        putBoolean("userloggedin", true)
                        putString("userprofile", userProfile)
                    }

                    callback() // go back to home
                }
                else{
                    message.value = "Registration unsuccessful. Please enter all data."

                    return@Button
                }
            },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green),

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

            }

            Spacer(modifier = Modifier.size(40.dp))

            Text(text = message.value, color = Color(0xFF333333))

        }
}

