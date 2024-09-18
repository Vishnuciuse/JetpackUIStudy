package com.example.jetpackuistudy.walkThrough

data class walkThoughData(
    val mainHeading: String,
    val bodyContents: List<BodyContent>,
    var expanded: Boolean = false
)

// Data class for the subcontent
data class SubContent(
    val title: String,
    val description: String
)

// Data class for the main body content
data class BodyContent(
    val heading: String,
    val subContents: List<SubContent>
)
