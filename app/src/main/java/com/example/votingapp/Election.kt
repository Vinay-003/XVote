package com.example.votingapp

data class Election(val name: String, val candidates: List<Candidate>)

object ElectionData {
    val sampleElections = listOf(
        Election("Election 1", listOf(Candidate("Candidate 1"), Candidate("Candidate 2"))),
        Election("Election 2", listOf(Candidate("Candidate 3"), Candidate("Candidate 4")))
    )
}