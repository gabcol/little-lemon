package com.example.littlelemon

 import androidx.compose.foundation.Image
 import androidx.compose.foundation.background
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.shape.RoundedCornerShape
 import androidx.compose.material3.Button
 import androidx.compose.material3.ButtonDefaults
 import androidx.compose.material3.Text
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp


@Composable
fun Profile(firstName : String, lastName: String, email: String) {

    Column(
    ) {
        Text(text = "Profile information")
    }

    Column {
        OnboardingHeader()
    }
    Column(modifier = Modifier
        .background(Color(0xEE999F97))) {
        Text(text = "Profile")
    }
    Column {
       // Present Text to display the user’s first name
        Text(text = firstName)
          //      Present Text to display the user’s last name
        Text(text = lastName)
           //     Present Text to display the user’s email address
        Text(text = email)

    }
    Column {
        Button(
            onClick = {


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
