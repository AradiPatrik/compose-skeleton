package com.cardinalblue.impl.profile.profile.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cardinalblue.domain.Profile

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val profile by viewModel.profile.collectAsState()
    ProfileScreen(profile)
}

@Composable
private fun ProfileScreen(profile: Profile?) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            if (profile != null) {
                Text(text = profile.firstName)
                Text(text = profile.lastName)
                Text(text = profile.email)
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen(Profile(
        firstName = "John",
        lastName = "Doe",
        email = ""
    ))
}