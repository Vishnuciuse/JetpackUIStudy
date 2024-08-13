package com.example.jetpackuistudy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeelements.ExpantableViewActivity
import com.example.jetpackuistudy.Constants.CollapseAnimation
import com.example.jetpackuistudy.Constants.ExpandAnimation
import com.example.jetpackuistudy.Constants.FadeInAnimation
import com.example.jetpackuistudy.Constants.FadeOutAnimation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.jetpackuistudy.ui.theme.JetpackUIStudyTheme
import com.example.jetpackuistudy.ui.theme.Purple80

class WalkThoughActivity1 : ComponentActivity() {
    private val viewModel by viewModels<ExpandableViewModel>()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackUIStudyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ExpandableScreen(viewModel)
//                    ExpantableViewActivity()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun ExpandableScreen(viewModel: ExpandableViewModel) {
    val cards = viewModel.cards.collectAsState()
    val expandedCard = viewModel.expandedCardList.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Purple80),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Expandable Lists",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyColumn {
            itemsIndexed(cards.value) { _, card ->
                ExpandableCard(
                    card = card,
                    onCardArrowClick = { viewModel.cardArrowClick(card.id) },
                    expanded = expandedCard.value.contains(card.id)
                )
            }
        }
    }

}

@ExperimentalAnimationApi
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    card: SampleData,
    onCardArrowClick: () -> Unit,
    expanded: Boolean
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(targetState = transitionState, label = "transition")
    val cardBgColor by transition.animateColor({
        tween(durationMillis = ExpandAnimation)
    }, label = "bgColorTransition") {
        if (expanded) Purple80 else Purple80
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = ExpandAnimation)
    }, label = "paddingTransition") {
        20.dp
    }
    val cardElevation by transition.animateDp({
        tween(durationMillis = ExpandAnimation)
    }, label = "elevationTransition") {
        if (expanded) 20.dp else 5.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = ExpandAnimation,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        15.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = ExpandAnimation)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }

    Card(
        backgroundColor = cardBgColor,
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(0.85f)
                    ) {
                        Text(
                            text = card.title,
                            color = Color.White,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        )
                    }
                    Column(
                        modifier = Modifier.weight(0.15f)
                    ) {
                        CardArrow(
                            degrees = arrowRotationDegree,
                            onClick = onCardArrowClick
                        )
                    }
                }
            }
            ExpandableContent(expanded)
        }
    }
}

@Composable
fun CardArrow(
    degrees: Float,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_right),
                contentDescription = "Expandable Arrow",
                modifier = Modifier.rotate(degrees),
                tint = Color.White
            )
        }
    )
}

@ExperimentalAnimationApi
@Composable
fun ExpandableContent(expanded: Boolean = true) {
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = FadeInAnimation,
                easing = FastOutLinearInEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(ExpandAnimation))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = FadeOutAnimation,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(CollapseAnimation))
    }

    AnimatedVisibility(
        visible = expanded,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Text(
                text = "Make It Easy Description",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                color = Purple80
            )
        }
    }
}

@Immutable
data class SampleData(
    val id: Int,
    val title: String
)

class ExpandableViewModel : ViewModel() {
    private val _cards = MutableStateFlow(listOf<SampleData>())
    val cards: StateFlow<List<SampleData>> get() = _cards

    private val _expandedCardList = MutableStateFlow(listOf<Int>())
    val expandedCardList: StateFlow<List<Int>> get() = _expandedCardList

    init {
        getSampleData()
    }

    private fun getSampleData() {
        viewModelScope.launch(Dispatchers.Default) {
            val sampleList = arrayListOf<SampleData>()
            repeat(10) {
                sampleList += SampleData(
                    id = it + 1,
                    title = "Make ${it + 1}"
                )
            }
            _cards.emit(sampleList)
        }
    }

    fun cardArrowClick(cardId: Int) {
        _expandedCardList.value = _expandedCardList.value.toMutableList().also { list ->
            if (list.contains(cardId)) {
                list.remove(cardId)
            } else {
                list.add(cardId)
            }
        }
    }
}

object Constants {
    const val ExpandAnimation = 300
    const val CollapseAnimation = 300
    const val FadeInAnimation = 300
    const val FadeOutAnimation = 300
}






