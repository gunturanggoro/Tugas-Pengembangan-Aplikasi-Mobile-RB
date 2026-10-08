package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.example.project.ui.DarkModeToggle
import org.example.project.ui.EditProfileForm
import org.example.project.ui.ProfileCard
import org.example.project.ui.theme.AppTheme
import org.example.project.viewmodel.ProfileViewModel

@Composable
fun App(
    viewModel: ProfileViewModel = viewModel { ProfileViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    var showMessage by remember { mutableStateOf(false) }

    val contactButtonColor by animateColorAsState(
        targetValue = if (showMessage) Color(0xFF2E7D32) else Color(0xFF14746F),
        animationSpec = tween(durationMillis = 500)
    )

    AppTheme(darkTheme = uiState.isDarkMode) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .safeContentPadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DarkModeToggle(
                    isDarkMode = uiState.isDarkMode,
                    onToggle = viewModel::setDarkMode
                )

                if (uiState.isEditing) {
                    EditProfileForm(
                        profile = uiState.profile,
                        onSave = viewModel::saveProfile,
                        onCancel = viewModel::cancelEditing
                    )
                } else {
                    ProfileCard(profile = uiState.profile)

                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = viewModel::startEditing,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF3A41C6),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Edit Profil")
                        }

                        Button(
                            onClick = { showMessage = !showMessage },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = contactButtonColor,
                                contentColor = Color.White
                            )
                        ) {
                            Text("Hubungi Saya")
                        }
                    }

                    AnimatedVisibility(showMessage) {
                        Text(
                            text = "Terima kasih! Saya akan segera merespons.",
                            modifier = Modifier.padding(top = 8.dp),
                            color = if (uiState.isDarkMode) Color(0xFF81C784) else Color(0xFF2E7D32)
                        )
                    }
                }
            }
        }
    }
}