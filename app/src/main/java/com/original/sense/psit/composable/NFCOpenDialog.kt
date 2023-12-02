package com.original.sense.psit.composable

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NFCAlertDialog(context: Context) {
    AlertDialog(
        onDismissRequest = { /* handle dialog dismiss if needed */ },
        title = { Text("NFC Scanner") },
        text = { Text("Please enable NFC scanner to use this feature.") },
        confirmButton = {
            Button(
                onClick = {
                    // Open NFC settings
                    val intent = Intent(Settings.ACTION_NFC_SETTINGS)
                    context.startActivity(intent)
                }
            ) {
                Text("Open Settings")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    /* handle negative button action if needed */
                }
            ) {
                Text("Cancel")
            }
        }
    )
}