package com.yourcompany.bullseye

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GamePrompt(valueText:Int,titleText:String, modifier: Modifier = Modifier){
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(titleText)
        Text("$valueText", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(12.dp))
    }
}
@Preview(showBackground = true)
@Composable
fun GamePromptPreview() {
    GamePrompt(22,stringResource(R.string.text_title_game_screen))
}
