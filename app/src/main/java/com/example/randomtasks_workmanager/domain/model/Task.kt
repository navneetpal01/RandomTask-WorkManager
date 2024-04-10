package com.example.randomtasks_workmanager.domain.model

data class Task(
    val accessibility: Double,
    val activity: String,
    val key: String,
    val link: String,
    val participants: Int,
    val price: Int,
    val type: String
)