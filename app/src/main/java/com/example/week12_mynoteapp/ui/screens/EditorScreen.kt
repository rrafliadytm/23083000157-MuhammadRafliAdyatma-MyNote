package com.example.week12_mynoteapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.week12_mynoteapp.viewmodel.NoteViewModel

/**
 * Layar Editor untuk menambah atau mengubah catatan.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    viewModel: NoteViewModel,
    noteId: Long?,
    onBack: () -> Unit
) {
    // Daftar warna Sticky Note
    val noteColors = listOf(
        0xFFFFF9C4, // Yellow
        0xFFFFCCBC, // Orange/Peach
        0xFFC8E6C9, // Green
        0xFFB3E5FC, // Blue
        0xFFF8BBD0, // Pink
        0xFFE1BEE7  // Purple
    )

    // Mencari data catatan jika sedang dalam mode edit
    val existingNote = remember(noteId) {
        noteId?.let { viewModel.getNoteById(it) }
    }

    // State untuk menampung input teks
    var textState by remember {
        mutableStateOf(existingNote?.content ?: "")
    }

    // State untuk warna yang dipilih
    var selectedColor by remember {
        mutableLongStateOf(existingNote?.color ?: noteColors[0])
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = if (noteId == null) "Catatan Baru" else "Edit Catatan",
                        fontWeight = FontWeight.Bold
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Batal")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.saveNote(noteId, textState, selectedColor)
                            onBack()
                        },
                        enabled = textState.isNotBlank()
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Simpan")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(selectedColor),
                    titleContentColor = Color.Black,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        },
        containerColor = Color(selectedColor)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Color Picker Row
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(noteColors) { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(color))
                            .border(
                                width = if (selectedColor == color) 3.dp else 1.dp,
                                color = if (selectedColor == color) Color.Black else Color.Black.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                            .clickable { selectedColor = color }
                    )
                }
            }

            TextField(
                value = textState,
                onValueChange = { textState = it },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                placeholder = { Text("Mulai menulis...", color = Color.Black.copy(alpha = 0.5f)) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black
                ),
                textStyle = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
