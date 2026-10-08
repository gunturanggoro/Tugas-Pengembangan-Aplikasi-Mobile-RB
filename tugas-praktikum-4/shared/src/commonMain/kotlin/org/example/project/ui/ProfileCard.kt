package org.example.project.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.example.project.data.ProfileData

@Composable
fun ProfileCard(
    profile: ProfileData,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    AnimatedVisibility(
        visible = isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 850))
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileHeader(name = profile.name)

                Text(
                    text = profile.bio,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    InfoItem(
                        icon = Icons.Default.Email,
                        label = "Email",
                        value = profile.email
                    )
                    InfoItem(
                        icon = Icons.Default.Phone,
                        label = "Phone",
                        value = profile.phone
                    )
                    InfoItem(
                        icon = Icons.Default.LocationOn,
                        label = "Location",
                        value = profile.location
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileCardPreview() {
    ProfileCard(
        profile = ProfileData(
            name = "Budi Santoso",
            bio = "Mobile App Developer yang suka ngoprek Kotlin",
            email = "budi@example.com",
            phone = "+62 812-3456-7890",
            location = "Bandar Lampung, Indonesia"
        )
    )
}