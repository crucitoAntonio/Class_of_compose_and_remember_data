package com.yourcompany.bullseye.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourcompany.bullseye.R

@Composable
fun GameDetail(
    modifier: Modifier = Modifier,
    totalScore: Int,
    round: Int,
    onStartOver: () -> Unit,
    onInfoButton: () -> Unit
) {
    Row(modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,) {
        FilledIconButton(onClick = {onStartOver()},
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.size(50.dp)

        ) {
            Icon(Icons.Filled.Refresh, contentDescription = stringResource(R.string.text_restart_button) )
        }
        GameInfo(stringResource(R.string.text_score), value = totalScore)
        GameInfo(stringResource(R.string.text_round), value = round)
        FilledIconButton(onClick = {onInfoButton()},
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.tertiary),
            modifier = Modifier.size(50.dp)) {
            Icon(Icons.Filled.Info, contentDescription = stringResource(R.string.text_info))
        }
    }
}

@Composable
fun GameInfo(label: String, value: Int = 0) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(IntrinsicSize.Min)) {
        Text(label)
        Text(value.toString(),style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp))
    }
}

@Preview(showBackground = true,device = Devices.AUTOMOTIVE_1024p, widthDp = 400, heightDp = 96)
@Composable
fun PreviewGameDetail() {
    GameDetail(totalScore = 999, round = 1, onStartOver = {}, onInfoButton = {})
}