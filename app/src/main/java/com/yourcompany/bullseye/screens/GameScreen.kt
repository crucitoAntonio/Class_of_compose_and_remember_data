package com.yourcompany.bullseye.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.bullseye.R
import com.yourcompany.bullseye.components.GameDetail
import com.yourcompany.bullseye.components.GamePrompt
import com.yourcompany.bullseye.components.ResultDialog
import com.yourcompany.bullseye.components.TargetSlider
import com.yourcompany.bullseye.ui.theme.BullseyeTheme
import com.yourcompany.bullseye.utils.ConstantsGame
import kotlin.math.abs
import kotlin.random.Random

@Composable
fun GameScreen(onNavigationToAbout:() -> Unit) {

    fun newTargetValue()=Random.nextInt(1,ConstantsGame.ONE_HUNDRED)

    var alertIsVisible by rememberSaveable { mutableStateOf(false) }
    var sliderValue by rememberSaveable { mutableStateOf(0.5f) }
    var targetValue by rememberSaveable { mutableStateOf(newTargetValue()) }
    var totalScore by rememberSaveable { mutableStateOf(0) }
    var currentRound by rememberSaveable { mutableStateOf(1) }
    var hitTheTurn by rememberSaveable { mutableStateOf(0) }
    val phrase by rememberSaveable { mutableStateOf(listOf(R.string.text_perfect,R.string.text_almost_had_it,R.string.text_not_bad,R.string.text_are_you_even_trying)) }

    fun startNewGame(){
        alertIsVisible=false
        sliderValue = 0.5f
        totalScore = 0
        hitTheTurn = 0
        currentRound=1
        targetValue = newTargetValue()
    }
    fun winTheSameGameSameTurn(){
        alertIsVisible=true
        hitTheTurn = 0
        targetValue = newTargetValue()
    }
    fun differenceAmount() = abs((sliderValue * ConstantsGame.ONE_HUNDRED).toInt() - targetValue)
    fun pointsEarned(): Int {
        return (ConstantsGame.ONE_HUNDRED - differenceAmount()) + when {
            hitTheTurn == 1 && (differenceAmount()==0) -> ConstantsGame.ONE_HUNDRED
            hitTheTurn == 2 && (differenceAmount()==1 || differenceAmount()==0) -> 50
            else -> 0
        }
    }
    fun alertTitle():Int{
        val difference = differenceAmount()
         return when{
            difference == 0 -> phrase[0]
            difference < 5 -> phrase[1]
            difference <= 10 -> phrase[2]
            else -> phrase[3]
        }
    }
    BackHandler { onNavigationToAbout() }
    Box {
        Image(modifier = Modifier.fillMaxSize().graphicsLayer(alpha =if (isSystemInDarkTheme()) 0.19f else 0.5f),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.dartboard),
            contentDescription = "Background Image")
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.weight(.5f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .weight(9f)
            ) {
                GamePrompt(targetValue, stringResource(R.string.text_title_game_screen))
                TargetSlider(value = sliderValue,
                    onValueChanged = { value ->
                        sliderValue = value
                    })
                Button(
                    onClick = {
                        hitTheTurn++
                        alertIsVisible = true
                        totalScore += pointsEarned()
                        if (differenceAmount() == 0) {
                            hitTheTurn = 0
                            winTheSameGameSameTurn()
                        }
                    }, shape = MaterialTheme.shapes.large,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(stringResource(R.string.text_button_game_screen))
                }
                GameDetail(
                    round = currentRound,
                    totalScore = totalScore,
                    modifier = Modifier.fillMaxWidth(),
                    onStartOver = { startNewGame() },
                    onInfoButton = {onNavigationToAbout()})
            }
            Spacer(modifier = Modifier.weight(.5f))
            if (alertIsVisible)
                ResultDialog(
                    hideDialog = { alertIsVisible = false },
                    sliderValue = (sliderValue * ConstantsGame.ONE_HUNDRED).toInt(),
                    points = pointsEarned(),
                    alertTitle = alertTitle(),
                    onRoundIncrement = {
                        currentRound += 1
                        //                    targetValue = newTargetValue()
                    }
                )

        }
    }

}


@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p, widthDp = 864, heightDp = 400)
@Composable
fun GreetingPreview() {
    BullseyeTheme {
        GameScreen(onNavigationToAbout = {})
    }
}
