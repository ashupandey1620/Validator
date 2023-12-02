package com.original.sense.psit.composable

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun NFCAlertDialog(context: Context) {

    var dialogVisible by remember { mutableStateOf(true) }

    if(dialogVisible) {
        AlertDialog(
            onDismissRequest = { dialogVisible = false } ,
            title = { Text("NFC Scanner") } ,
            text = { Text("Please enable NFC scanner to use this feature.") } ,
            confirmButton = {
                Button(
                    onClick = {
                        // Open NFC settings
                        dialogVisible = false
                        val intent = Intent(Settings.ACTION_NFC_SETTINGS)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // Set the FLAG_ACTIVITY_NEW_TASK flag
                        context.startActivity(intent)
                    }
                ) {
                    Text("Open Settings")
                }
            } ,
            dismissButton = {
                Button(
                    onClick = {
                        dialogVisible = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}