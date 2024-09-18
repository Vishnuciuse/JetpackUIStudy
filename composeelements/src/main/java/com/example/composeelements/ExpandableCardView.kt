package com.example.composeelements

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.composeelements.data.BodyContent
import com.example.composeelements.data.SubContent
import com.example.composeelements.data.walkThoughData
import com.example.composeelements.ui.theme.JetpackUIStudyTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExpandableCardView(items: List<String>) {

    var expanded by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }

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
        mainHeading = "Today's Date: $currentDate",
        bodyContents = listOf(bodyContent1, bodyContent2)
    )

    listItems.add(mainContent)
    listItems.add(mainContent)
    listItems.add(mainContent)



    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 90f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(animationSpec = tween(300))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
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
                    text = "PRIMARY NETWORK",
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "1. the main header section",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "a. the sub content section",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableCardPreview() {
    JetpackUIStudyTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ExpandableCardView(items = List(20) { "Item $it" })
        }
        }
}