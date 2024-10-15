package com.example.votingapp

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

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
        var errorMessage by remember { mutableStateOf<String?>(null) }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uidProof = uri
        }

        val context = LocalContext.current
        val countryList = loadCountryList(context)

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val phoneRegex = "^[+]?[0-9]{10,13}$".toRegex()
            return phoneNumber.matches(phoneRegex)
        }

        fun isValidCountry(country: String): Boolean {
            return countryList.contains(country)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
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
            Button(onClick = { launcher.launch("image/*") }) {
                Text("Upload  UID Proof (Image)")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
            Spacer(modifier = Modifier.height(8.dp))
            TextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password") }, visualTransformation = PasswordVisualTransformation())
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                when {
                    name.isEmpty() -> errorMessage = "Name is required"
                    phoneNumber.isEmpty() -> errorMessage = "Phone Number is required"
                    !isValidPhoneNumber(phoneNumber) -> errorMessage = "Invalid Phone Number"
                    country.isEmpty() -> errorMessage = "Country is required"
                    !isValidCountry(country) -> errorMessage = "Invalid Country Name"
                    uidNumber.isEmpty() -> errorMessage = "UID Number is required"
                    uidProof == null -> errorMessage = "UID Proof is required"
                    email.isEmpty() -> errorMessage = "Email is required"
                    password.isEmpty() -> errorMessage = "Password is required"
                    confirmPassword.isEmpty() -> errorMessage = "Confirm Password is required"
                    password != confirmPassword -> errorMessage = "Passwords do not match"
                    password.length <= 8 -> errorMessage = "Password must be longer than 8 characters"
                    else -> {
                        // Perform signup logic here
                        setCurrentPage(SelectElectionPage(sampleElections)) // Pass sampleElections
                    }
                }
            }) {
                Text("Sign Up")
            }
            errorMessage?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }

    private fun loadCountryList(context: Context): List<String> {
        val inputStream = context.assets.open("countries.json")
        val reader = InputStreamReader(inputStream)
        val countryType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(reader, countryType)
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