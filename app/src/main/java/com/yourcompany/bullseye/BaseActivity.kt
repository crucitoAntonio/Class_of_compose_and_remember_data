package com.yourcompany.bullseye

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.yourcompany.bullseye.screens.AboutScreen
import com.yourcompany.bullseye.screens.GameScreen
import com.yourcompany.bullseye.ui.theme.BullseyeTheme
import com.yourcompany.bullseye.utils.ConstantsGame

open class BaseActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent{
      ShowSplashScreen{
        onSplashCompleted()
      }
    }
  }
  open fun onSplashCompleted() {
    setContent {
      BullseyeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          MainContent()
        }
      }
    }
  }

  @Composable
  open fun MainContent() {

  }

  @Composable
  private fun ShowSplashScreen(onAnimationEnd: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_target))
    val progress by animateLottieCompositionAsState(
      composition = composition,
      iterations = 1 // Reproduce la animación una sola vez
    )

    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      LottieAnimation(
        composition = composition,
        progress = progress
      )
    }

    // Detecta cuándo termina la animación y ejecuta onAnimationEnd
    LaunchedEffect(progress) {
      if (progress == 1f) {
        onAnimationEnd()
      }
    }
  }

  fun showLoading() {
  }

  fun hideLoading() {
  }
}