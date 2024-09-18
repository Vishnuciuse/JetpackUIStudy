package com.example.jetpackuistudy.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackuistudy.ui.theme.JetpackUIStudyTheme

class NotesActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                MainRecyclerView()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetpackUIStudyTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Composable
fun MainRecyclerView( modifier: Modifier = Modifier) {
    val itemsList = listOf("Today", "Tomorrow", "Yesterday", "Item 3", "Item 4", "Item 5")

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(itemsList) { item ->
            Card (modifier = modifier.padding(8.dp)
                .border(
                    width = 0.dp, // Border thickness
                    color = Color.White, // Border color
                    shape = RoundedCornerShape(16.dp) // Curved corners
                )
                ){

                Row() {
                    Column {
                        Text(
                            text = item, modifier = Modifier
                                .padding(8.dp)
                        )
                        Text(
                            text = "Sat 17", modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                    Text(
                        text = "Today task are written on here , you can add multiple tasks",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainRecyclerView2() {
    MyApp {
        MainRecyclerView()
    }
}