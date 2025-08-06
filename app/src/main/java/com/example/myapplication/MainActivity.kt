package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StopwatchApp()
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopwatchApp() {
    var timeInMillis by remember { mutableStateOf(0L) }
    var isRunning by remember { mutableStateOf(false) }

    // Timer logic: runs every 10 ms
    LaunchedEffect(key1 = isRunning) {
        while (isRunning) {
            delay(10L)
            timeInMillis += 10L
        }
    }

    val hours = (timeInMillis / (1000 * 60 * 60)) % 24
    val minutes = (timeInMillis / (1000 * 60)) % 60
    val seconds = (timeInMillis / 1000) % 60
    val millis = (timeInMillis % 1000)

    val timeDisplay = String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Stopwatch") })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(timeDisplay, fontSize = 36.sp)

            Spacer(modifier = Modifier.height(32.dp))

            Row {
                Button(onClick = { isRunning = true }) {
                    Text("Start")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = { isRunning = false }) {
                    Text("Hold")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    isRunning = false
                    timeInMillis = 0L
                }) {
                    Text("Stop")
                }
            }
        }
    }
}
