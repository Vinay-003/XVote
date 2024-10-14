package com.example.votingapp

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

class SignupPage : BasePage() {
    override val title: String = "Sign Up"

    @Composable
    fun Content(sampleElections: List<Election>, setCurrentPage: (BasePage) -> Unit) {
        var name by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var uidNumber by remember { mutableStateOf("") }
        var uidProof by remember { mutableStateOf<Uri?>(null) }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uidProof = uri
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = country, onValueChange = { country = it }, label = { Text("Country") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = uidNumber, onValueChange = { uidNumber = it }, label = { Text("UID Number") })
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { launcher.launch("application/pdf") }) {
                Text("Upload UID Proof (PDF)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { launcher.launch("image/*") }) {
                Text("Upload UID Proof (Image)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password") }, visualTransformation = PasswordVisualTransformation())
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (password == confirmPassword) {
                    // Perform signup logic here
                    setCurrentPage(SelectElectionPage(sampleElections)) // Pass sampleElections
                } else {
                    // Show error message
                    // You can use a Snackbar or any other method to show the error
                }
            }) {
                Text("Sign Up")
            }
        }
    }

    @Preview
    @Composable
    fun ContentPreview() {
        val sampleElections = listOf(
            Election("Election 1", listOf(Candidate("Candidate 1"), Candidate("Candidate 2"))),
            Election("Election 2", listOf(Candidate("Candidate 3"), Candidate("Candidate 4")))
        )
        Content(sampleElections = sampleElections, setCurrentPage = {})
    }
}