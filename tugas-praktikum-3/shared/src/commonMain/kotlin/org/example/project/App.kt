package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun App() {
    MaterialTheme {
        val profile = remember {
            ProfileData(
                name = "Guntur Anggoro Rahardianto",
                bio = "Mahasiswa Teknik Informatika Institut Teknologi Sumatera",
                email = "guntur.124140105@student.itera.ac.id",
                phone = "+62 857-5804-0861",
                location = "Bandar Lampung, Indonesia"
            )
        }

        var showMessage by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .safeContentPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileCard(profile = profile)

                val buttonColor by animateColorAsState(
                    targetValue = if (showMessage) Color(0xFF243EEA) else Color(0xFF31AAA9),
                    animationSpec = tween(durationMillis = 500)
                )

                Button(
                    onClick = { showMessage = !showMessage },
                    modifier = Modifier.padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor,
                        contentColor = Color.White
                    )
                ) {
                    Text("Hubungi Saya")
                }

                AnimatedVisibility(showMessage) {
                    Text(
                        text = "Terima kasih! Saya akan segera merespons.",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color(0xFF000000)
                    )
                }
            }
        }
    }
}