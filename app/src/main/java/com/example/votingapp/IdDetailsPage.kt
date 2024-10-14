package com.example.votingapp

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

class IdDetailsPage(private val candidate: Candidate) : BasePage() {
    override val title: String = "ID Details"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        var voterIdNumber by remember { mutableStateOf("") }
        var voterIdProof by remember { mutableStateOf<Uri?>(null) }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            voterIdProof = uri
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = voterIdNumber, onValueChange = { voterIdNumber = it }, label = { Text("Voter ID Number") })
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { launcher.launch("application/pdf") }) {
                Text("Upload Voter ID Proof (PDF)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { launcher.launch("image/*") }) {
                Text("Upload Voter ID Proof (Image)")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { setCurrentPage(IdConfirmationPage(candidate, voterIdNumber, voterIdProof)) }) {
                Text("Next")
            }
        }
    }
}