package com.example.week12_mynoteapp.model

/**
 * Data class untuk merepresentasikan entitas Catatan.
 */
data class Note(
    val id: Long,
    val content: String,
    val updatedAt: Long = System.currentTimeMillis(),
    val color: Long = 0xFFFFF9C4 // Default yellow color (Sticky Note style)
)
