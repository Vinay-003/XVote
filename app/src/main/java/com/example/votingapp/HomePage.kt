package com.example.votingapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class HomePage : BasePage() {
    override val title: String = "Home"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { setCurrentPage(LoginPage()) }) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { setCurrentPage(SignupPage()) }) {
                Text("Sign Up")
            }
        }
    }
}