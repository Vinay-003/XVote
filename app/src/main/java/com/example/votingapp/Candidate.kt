package com.example.votingapp

data class Candidate(val name: String)

object CandidateData {
    val sampleCandidates = listOf(
        Candidate("Candidate 1"),
        Candidate("Candidate 2"),
        Candidate("Candidate 3"),
        Candidate("Candidate 4")
    )
}