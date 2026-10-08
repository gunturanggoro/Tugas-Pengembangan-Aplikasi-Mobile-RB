package org.example.project.viewmodel

import org.example.project.data.ProfileData
data class ProfileUiState(
    val profile: ProfileData = ProfileData(
        name = "Guntur Anggoro Rahardianto",
        bio = "Mahasiswa Teknik Informatika Institut Teknologi Sumatera",
        email = "guntur.124140105@student.itera.ac.id",
        phone = "+62 857-5804-0861",
        location = "Bandar Lampung, Indonesia"
    ),
    val isEditing: Boolean = false,
    val isDarkMode: Boolean = false
)