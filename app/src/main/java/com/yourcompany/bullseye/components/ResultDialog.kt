package com.yourcompany.bullseye.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.yourcompany.bullseye.R

@Composable
fun ResultDialog(
    hideDialog: () -> Unit,
    onRoundIncrement: () -> Unit,
    sliderValue: Int,
    points: Int,
    alertTitle: Int
) {
    AlertDialog(
        onDismissRequest = {
            hideDialog()
            onRoundIncrement()
        },
        confirmButton = {
            TextButton(onClick = {
                hideDialog()
                onRoundIncrement()
            }) {
                Text("AWESOME!!!")
            }
        },
        title = { Text(stringResource(alertTitle)) },
        text = { Text(stringResource(R.string.text_result_dialog_message, sliderValue, points)) },
    )
}

@Preview(showBackground = true)
@Composable
fun ResultDialogPreview() {
    ResultDialog(hideDialog = {}, onRoundIncrement = {}, 22, 55, alertTitle = R.string.text_perfect)
}