package com.example.votingapp

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.ui.Alignment


class IdConfirmationPage(
    private val candidate: Candidate,
    private val voterIdNumber: String,
    private val voterIdProof: Uri?
) : BasePage() {
    override val title: String = "ID Confirmation"

    @Composable
    fun Content(setCurrentPage: (BasePage) -> Unit) {
        var name by remember { mutableStateOf("") }
        var dob by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var zipcode by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Fill all the details according to the Voter ID")
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = dob, onValueChange = { dob = it }, label = { Text("Date of Birth") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = voterIdNumber, onValueChange = { /* No-op */ }, label = { Text("Voter ID Number") }, enabled = false)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = zipcode, onValueChange = { zipcode = it }, label = { Text("Zipcode") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { setCurrentPage(CastVotePage(candidate)) }) {
                Text("Confirm")
            }
        }
    }
}