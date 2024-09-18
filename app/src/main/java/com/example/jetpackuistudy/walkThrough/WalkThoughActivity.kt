package com.example.jetpackuistudy.walkThrough

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackuistudy.R
import com.example.jetpackuistudy.ui.theme.JetpackUIStudyTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WalkThoughActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp{
                ExpandableCardView(/*items = List(20) { "Item $it" }*/)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    JetpackUIStudyTheme {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            content()
        }
    }
}


@Composable
fun ExpandableCardView() {
    var listItems = mutableListOf<walkThoughData>()

    // Create a date formatter
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Get the current date
    val currentDate: String = dateFormatter.format(Date())

    // Create subcontent items
    val subContent1 = SubContent(title = "Subheading 1", description = "Description 1")
    val subContent2 = SubContent(title = "Subheading 2", description = "Description 2")

    // Create body content items
    val bodyContent1 = BodyContent(
        heading = "Main Body Heading 1",
        subContents = listOf(subContent1, subContent2)
    )

    val bodyContent2 = BodyContent(
        heading = "Main Body Heading 2",
        subContents = listOf(subContent1)
    )

    // Create the main content with the current date as the main heading
    val mainContent = walkThoughData(
        mainHeading = "Today $currentDate",
        bodyContents = listOf(bodyContent1, bodyContent2)
    )

    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)



    Column {
        
        Text(text = "sfdsfdf sdf dsfs f  sdf sd f sfs f dsf dsf  sf sf s df sdfsdfsdfdsf dsf sd f sfd  sfdsfdsfsf  th ht y jy y yjhyjyjjmjymjymjym y y n hynh nhy nhynh yn nhy n ny j yj yj trgfrgefef defe vfr gr te ffe v grre ff sdf  sdf s df sdf s fd sfd sdf fdsf")

        LazyColumn (modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            /*.clickable { expanded = !expanded }*/
            .animateContentSize(animationSpec = tween(300))
            .background(Color.White)){
            items(listItems) { item ->
                Column( modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White)) {
                    MainContentView(mainContent)
                }
            }
        }
    }

   /* LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(animationSpec = tween(300))
            .background(Color.White)
    ) {
        items(listItems.size) { index ->
            val item = listItems[index]
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(Color.White)
            ) {
                // Header Section (Gray)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_arrow_right),
                        contentDescription = "Expand/Collapse",
                        modifier = Modifier
                            .rotate(rotationAngle)
                            .size(18.dp),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.mainHeading,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                }

                // Content Section (White)
                if (expanded) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp)
                    ) {
                        items(item.bodyContents.size) { bodyIndex ->
                            val bodyItem = item.bodyContents[bodyIndex]
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()){
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "1. ${bodyItem.heading}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = Color.Black
                                )
//                                LazyColumn() {
//                                    items(bodyItem.subContents.size) { subIndex ->
//                                        val subItem = bodyItem.subContents[subIndex]
//                                        Column(
//                                            modifier = Modifier
//                                                .fillMaxWidth()
//                                        ) {
//                                            Text(
//                                                modifier = Modifier.padding(start = 10.dp),
//                                                text = "a. ${subItem.title}",
//                                                style = MaterialTheme.typography.bodyLarge,
//                                                color = Color.Black)
//                                        }
//                                    }
//                                }

                            }
                        }

                    }
                }
            }
        }

    }*/
}

// Composable function to display the MainContent
@Composable
fun MainContentView(mainContent: walkThoughData) {
    var expanded by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Column() {
        // Header Section (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .clickable { expanded = !expanded }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right),
                contentDescription = "Expand/Collapse",
                modifier = Modifier
                    .rotate(rotationAngle)
                    .size(18.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = mainContent.mainHeading,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }

        if (expanded) {
            mainContent.bodyContents.forEach { bodyContent ->
                BodyContentView(bodyContent)
            }
        }


    }
}

// Composable function to display a BodyContent
@Composable
fun BodyContentView(bodyContent: BodyContent) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(text = "1. ${bodyContent.heading}", style = MaterialTheme.typography.bodyLarge)
        bodyContent.subContents.forEach { subContent ->
            SubContentView(subContent)
        }
    }
}

// Composable function to display a SubContent
@Composable
fun SubContentView(subContent: SubContent) {
    Column(modifier = Modifier.padding(start = 16.dp, top = 4.dp)) {
        Text(text = "a. ${subContent.title}", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableCardPreview() {
    MyApp{
        ExpandableCardView(/*items = List(20) { "Item $it" }*/)
    }
}