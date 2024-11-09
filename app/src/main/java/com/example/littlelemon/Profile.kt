package com.example.littlelemon

 import android.content.SharedPreferences

 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material3.Button
 import androidx.compose.material3.ButtonDefaults
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable

 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
 import androidx.core.content.edit

 import kotlinx.serialization.json.Json

@Composable
fun Profile(sharedPreferences: SharedPreferences,  callback: ()->Unit ) {

    val jstr = """
        {
          "firstName" : "default",
          "lastName" : "default",
          "email" : "defaultemail@test.com"
        }
    """.trimIndent()





val jsonString = sharedPreferences.getString("userprofile", jstr).toString()


   val userdata =   Json.decodeFromString<UserProfile>(jsonString)

    Column() {

            Text(text = "Profile information")



            OnboardingHeader()


            Text(text = "Profile")


            // Present Text to display the user’s first name
            Text(text = userdata.firstName)
            //      Present Text to display the user’s last name
            Text(text = userdata.lastName)
            //     Present Text to display the user’s email address
            Text(text = userdata.email)


            Button(
                onClick = {

                    sharedPreferences.edit().clear().commit()

                    sharedPreferences.edit(commit = true) {
                        putBoolean("userloggedin", false)
                    }


                    callback()

                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
            ) {
                Text(
                    text = "Log out",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
            }

    }
}



@Composable
private fun OnboardingHeader() {
    Column {
//        Image(
//            painter = painterResource(id = R.drawable.logo),
//            contentDescription = "Header Image",
//            modifier = Modifier.clip(RoundedCornerShape(10.dp) )
//        )
    }
}
