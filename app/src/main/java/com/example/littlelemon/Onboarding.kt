package com.example.littlelemon


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.runtime.Composable
 import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import com.example.littlelemon.EmailTextFieldSnippet.SimpleEmailField
import com.example.littlelemon.TextTextFieldSnippet.SimpleTextField
import com.example.littlelemon.ui.theme.LittleLemonTheme


import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString



@Composable
fun Onboarding(context: Context, callback: ()->Unit ) {

    var message = remember { mutableStateOf("") }


    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    Column(
           // .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Column {
            OnboardingHeader()
        }
        Column(modifier = Modifier
            .background(Color(0xEE999F97))) {
            Text(text = "Let's get to know you")
        }
        Column {
            SimpleTextField(label = "First Name", firstName.value) { firstName.value = it }
            SimpleTextField(label = "Last name" , lastName.value) { lastName.value= it }
            SimpleEmailField(label = "Last name" , email.value) { email.value = it }
            // PasswordTextField()
        }
        Column {
            Button(
                onClick = {

                    //  Validate registration form
                    //  firstName.isBlank()

                   if(firstName.value.isEmpty() || lastName.value.isEmpty() || email.value.isEmpty()) {

                       message.value = "Registration unsuccessful. Please enter all data."
                       return@Button
                   }

                    val userdata = UserProfile(firstName = firstName.value, lastName= lastName.value, email = email.value)

                    val userProfile = Json.encodeToString(userdata)

                    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)

                    sharedPreferences.edit(commit = true){
                        putBoolean("userloggedin", true)
                        putString("userprofile", userProfile)
                    }




                    callback() // go back to home
                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
               // enabled = (!firstName.value.isEmpty() && !lastName.value.isEmpty() && !email.value.isEmpty())
            ) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
            }
        }
        Column {
            Text(text = message.value)
        }
    }
}
//
//private object TextFormattingTextFieldSnippet {
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    fun PasswordTextField() {
//        var password by rememberSaveable { mutableStateOf("") }
//
//        TextField(
//            value = password,
//            onValueChange = { password = it },
//            label = { Text("Enter password") },
//            visualTransformation = PasswordVisualTransformation(),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
//        )
//    }
//
//}

private object TextTextFieldSnippet {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleTextField(label: String, text: String, textUpdate: (String)->Unit) {


        TextField(
            value = text,
            onValueChange = textUpdate,
           // label = { Text(label) }
        )
    }
}

private object EmailTextFieldSnippet {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleEmailField(label: String, email: String, emailUpdate: (String)->Unit) {
       // var email by remember { mutableStateOf(" ") }

        TextField(
            value = email,
            onValueChange = emailUpdate,
          //  label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
    }
}



@Composable
private fun OnboardingHeader() {
    Column {
//        Image(
//            painter = painterResource(id = R.drawable.logo), //(id = R.drawable.logo),
//            contentDescription = "Header Image",
//            modifier = Modifier.clip(RoundedCornerShape(10.dp) )
//        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview2() {
    LittleLemonTheme {
      //  Onboarding({})
    }
}

