package com.yourcompany.bullseye.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yourcompany.bullseye.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TargetSlider(
                 value: Float = 0.5f,
                 onValueChanged: (Float) -> Unit,
                 ) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.text_label_min_value),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 16.dp)
        )
        Slider(
            value = value,
            valueRange = 0.01f..1f,
            onValueChange = onValueChanged, modifier = Modifier
                .weight(1f),
            thumb = {
                Image(modifier = Modifier.size(60.dp),
                    painter = painterResource(R.drawable.target_shooting),
                    contentDescription = stringResource(R.string.text_slider_thumb)

                )
            },
            track = { Box(modifier= Modifier.height(8.dp).fillMaxWidth().background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            )) }
        )
        Text(
            stringResource(R.string.text_label_max_value),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}
@Preview
@Composable
fun PreviewTargetSlider(){
    TargetSlider(onValueChanged = {})
}