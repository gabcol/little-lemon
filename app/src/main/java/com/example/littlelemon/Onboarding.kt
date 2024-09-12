package com.example.littlelemon


import androidx.compose.foundation.Image
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.TextFormattingTextFieldSnippet.PasswordTextField
import com.example.littlelemon.TextTextFieldSnippet.SimpleFilledTextFieldSample
import com.example.littlelemon.ui.theme.LittleLemonTheme



@Composable
fun Onboarding() {
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
            SimpleFilledTextFieldSample("First Name")
            SimpleFilledTextFieldSample("Last name")
            PasswordTextField()
        }
        Column {
            Button(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            ) {
                Text(
                    text = "Register",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
            }
        }
    }
}

private object TextFormattingTextFieldSnippet {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PasswordTextField() {
        var password by rememberSaveable { mutableStateOf("") }

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
    }

}

private object TextTextFieldSnippet {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleFilledTextFieldSample(label: String) {
        var text by remember { mutableStateOf(" ") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("$label") }
        )
    }

}




@Composable
private fun OnboardingHeader() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Header Image",
            modifier = Modifier.clip(RoundedCornerShape(10.dp) )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview2() {
    LittleLemonTheme {
        Onboarding()
    }
}