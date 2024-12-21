package com.yourcompany.bullseye

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ResultDialog(
    hideDialog: () -> Unit,
    sliderValue: Int,
    points: Int,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { hideDialog() },
        confirmButton = {
            TextButton(onClick =
            {
                hideDialog()
            }) {
                Text("Button")
            }
        },
        title = { Text("Tittle") },
        text = { Text(stringResource(R.string.text_result_dialog_message,sliderValue,points)) },
    )
}

@Preview(showBackground = true)
@Composable
fun ResultDialogPreview(){
    ResultDialog(hideDialog = {},22,55)
}