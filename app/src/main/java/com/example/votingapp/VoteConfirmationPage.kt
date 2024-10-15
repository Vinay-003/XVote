package com.example.votingapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

class VoteConfirmationPage(private val candidate: Candidate) : BasePage() {
    override val title: String = "Vote Confirmation"

    @Composable
    fun Content() {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("You have successfully casted your vote for: ${candidate.name}")
        }
    }
}