package com.example.votingapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ProfilePage(private val user: User) : BasePage() {
    override val title: String = "Profile"

    @Composable
    fun Content() {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Name: ${user.name}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Phone Number: ${user.phoneNumber}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Country: ${user.country}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("UID Number: ${user.uidNumber}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Email: ${user.email}")
        }
    }
}