package com.example.jetpackuistudy

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.jetpackuistudy.ui.theme.JetpackUIStudyTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackUIStudyTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainPage("John Doe")
                }
            }
        }
    }
}

@Composable
fun MainPage(userName: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = { Text(text = "Home") },
            actions = {
                IconButton(onClick = { /* TODO: Handle search icon click */ }) {
                    Icon(painter = painterResource(id = android.R.drawable.ic_menu_search), contentDescription = "Search")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Hello, $userName!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Welcome back!",
            fontSize = 18.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                    contentDescription = "Sample Image",
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /* TODO: Handle button click */ }) {
                    Text(text = "Click Me")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        SimpleConstraintLayout()
        Text(
            text = "Here is some description content that provides more information about the above card. You can customize this text to display any relevant details.",
            fontSize = 16.sp
        )
    }

}

@Composable
fun SimpleConstraintLayout() {
    ConstraintLayout(
        modifier = Modifier.minimumInteractiveComponentSize()
    ) {
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(button1.bottom, margin = 16.dp)
                start.linkTo(button1.start)
            }
        ) {
            Text("Button 2")
        }

        Text(
            text = "Hello, ConstraintLayout!",
            modifier = Modifier.constrainAs(text) {
                top.linkTo(button2.bottom, margin = 16.dp)
                start.linkTo(button2.start)
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetpackUIStudyTheme {
        MainPage("John Doe")
    }
}