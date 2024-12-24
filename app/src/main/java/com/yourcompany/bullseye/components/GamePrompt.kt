package com.yourcompany.bullseye.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourcompany.bullseye.R

@Composable
fun GamePrompt(valueText: Int, titleText: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        Text(
            titleText,
            style = MaterialTheme.typography.titleMedium.copy(
                letterSpacing = 1.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            "$valueText",
/*
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
*/
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold
            ),
                    modifier = Modifier.padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GamePromptPreview() {
    GamePrompt(22, stringResource(R.string.text_title_game_screen))
}
