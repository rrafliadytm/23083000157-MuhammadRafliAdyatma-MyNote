package com.example.week12_mynoteapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.example.week12_mynoteapp.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel untuk mengelola data catatan menggunakan SharedPreferences.
 */
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences = application.getSharedPreferences("notes_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Backing property untuk StateFlow daftar catatan
    private val _notes = MutableStateFlow<List<Note>>(loadNotes())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private fun loadNotes(): List<Note> {
        val json = sharedPreferences.getString("notes_list", null) ?: return emptyList()
        val type = object : TypeToken<List<Note>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveNotesToPrefs(notes: List<Note>) {
        val json = gson.toJson(notes)
        sharedPreferences.edit().putString("notes_list", json).apply()
    }

    /**
     * Menyimpan atau memperbarui catatan.
     */
    fun saveNote(id: Long?, content: String, color: Long) {
        if (content.isBlank()) return

        _notes.update { currentNotes ->
            val updatedNotes = if (id == null) {
                // Tambahkan catatan baru di posisi teratas
                val newNote = Note(
                    id = System.currentTimeMillis(),
                    content = content,
                    color = color
                )
                listOf(newNote) + currentNotes
            } else {
                // Perbarui catatan yang sudah ada
                currentNotes.map { note ->
                    if (note.id == id) {
                        note.copy(content = content, color = color, updatedAt = System.currentTimeMillis())
                    } else {
                        note
                    }
                }.sortedByDescending { it.updatedAt } // Urutkan berdasarkan waktu terbaru
            }
            saveNotesToPrefs(updatedNotes)
            updatedNotes
        }
    }

    /**
     * Menghapus catatan berdasarkan ID.
     */
    fun deleteNote(id: Long) {
        _notes.update { currentNotes ->
            val updatedNotes = currentNotes.filterNot { it.id == id }
            saveNotesToPrefs(updatedNotes)
            updatedNotes
        }
    }

    /**
     * Mengambil catatan berdasarkan ID.
     */
    fun getNoteById(id: Long): Note? {
        return _notes.value.find { it.id == id }
    }
}
