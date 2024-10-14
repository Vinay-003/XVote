package com.example.votingapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class CastVotePage(private val candidate: Candidate) : BasePage() {
    override val title: String = "Cast Vote"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("You are voting for: ${candidate.name}")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { setCurrentPage(VoteConfirmationPage(candidate)) }) {
                Text("Vote")
            }
        }
    }
}