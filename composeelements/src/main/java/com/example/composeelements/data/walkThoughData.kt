package com.example.composeelements.data

data class walkThoughData(
    val mainHeading: String,
    val bodyContents: List<BodyContent>
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
