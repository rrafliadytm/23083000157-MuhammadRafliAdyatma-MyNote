# 📝 MyNote App - RafliNote Master

A modern Android Note-taking application built with **Jetpack Compose**, featuring a "Sticky Note" aesthetic with color customization and local persistence.

---

## 📸 Screenshots

| 🏠 Dashboard Screen | 🎨 Editor & Color Picker | ℹ️ About Screen |
| :---: | :---: | :---: |
| ![Dashboard](screenshots/dashboard%20screen.png) | ![Editor](screenshots/editor%20screen.png) | ![About](screenshots/about%20screen.png) |

---

## ✨ Fitur Utama (Features)

*   **💾 Penyimpanan Lokal (Persistence)**: Menggunakan `SharedPreferences` dan `Gson` untuk menyimpan data secara permanen di perangkat. Catatan tidak akan hilang saat aplikasi ditutup.
*   **🌈 Kustomisasi Warna**: Fitur gaya "Sticky Note" yang memungkinkan pengguna memilih warna latar belakang untuk setiap catatan (Kuning, Oranye, Hijau, Biru, Pink, Ungu).
*   **✏️ Editor Intuitif**: Antarmuka bersih untuk menulis dan mengedit catatan dengan indikator warna yang berubah secara real-time.
*   **🗑️ Manajemen Catatan**: Menambah, memperbarui, dan menghapus catatan dengan mudah melalui daftar di dashboard.
*   **ℹ️ Halaman Informasi**: Halaman "About" khusus yang menampilkan informasi pengembang dan metadata proyek.
*   **📱 Material 3 Design**: Menggunakan komponen UI modern dari Google untuk pengalaman pengguna yang maksimal.

---

## 🛠️ Tech Stack

*   **Bahasa**: [Kotlin](https://kotlinlang.org/)
*   **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   **Navigasi**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
*   **Arsitektur**: MVVM (Model-View-ViewModel)
*   **Data Serialization**: [Gson](https://github.com/google/gson) (JSON)
*   **Local Storage**: SharedPreferences

---

## 📂 Struktur Proyek

```text
app/src/main/java/com/example/week12_mynoteapp/
├── model/           # Definisi data (Note.kt)
├── viewmodel/       # Logika bisnis & Persistence (NoteViewModel.kt)
├── navigation/      # Pengaturan rute (Screen.kt, MyNoteNavGraph.kt)
└── ui/
    ├── screens/     # Tampilan (DashboardScreen, EditorScreen, AboutScreen)
    └── theme/       # Desain sistem (Warna, Tipografi, Tema)
```

---

## 👨‍💻 Profil Pengembang

*   **NIM**: `23083000157`
*   **Nama**: `Muhammad Rafli Adyatma`
*   **Mata Kuliah**: `Pemrograman Mobile`
*   **Tugas**: `Project MyNote - Semester 6`
*   **Tanggal**: `Juli 2026`

---

## 🚀 Cara Menjalankan

1. Clone repository ini.
2. Buka di **Android Studio (Ladybug 2024.2.1 atau versi terbaru)**.
3. Tunggu proses **Gradle Sync** selesai.
4. Jalankan pada Emulator atau Perangkat Fisik (Minimum Android 8.0 / API 26).

---

© 2026 Muhammad Rafli Adyatma. Dibuat untuk memenuhi tugas akademik.
