# MAD Practical 7 - Person Directory & Map Viewer

A native Android application built with Kotlin that displays person profile data, caches it locally using an offline-first SQLite database, presents it in a RecyclerView, and provides Google Maps location viewing.

---

## Features

- **Person Profile List**: Displays name, phone number, email, address, latitude, and longitude.
- **Offline-first data**: Loads bundled demonstration records and stores profile data locally using SQLite.
- **Google Maps Integration**: "View on Map" launches Google Maps using geo-intents (`geo:lat,lng`).
- **Material 3 UI**: Light and dark theme support.

> **API credential security:** The Android client intentionally does not call the former authenticated JSON Generator endpoint. A private API token embedded in an APK can be extracted by anyone who obtains the app. The current client uses bundled demonstration records. If remote data is needed, add a trusted backend proxy and keep the provider credential exclusively on that server. Do not put private credentials in Kotlin source, resources, BuildConfig, or other client-side configuration.

## Tech Stack

- **Language**: Kotlin
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 37
- **Database**: SQLite (`SQLiteOpenHelper`)
- **UI**: RecyclerView, ConstraintLayout, CardView, MaterialButton
- **Maps**: Android geo-intents

## Project Structure

```text
com.example.a24012011189_mad_p7/
├── DatabaseHelper.kt
├── HttpRequest.kt         # Bundled demo data; no embedded API credential
├── MainActivity.kt
├── MapActivity.kt
├── Person.kt
├── PersonAdapter.kt
└── res/
    ├── layout/
    └── values/
```

## Getting Started

### Prerequisites
- Android Studio (Jellyfish or newer)
- JDK 11 or higher
- Android device/emulator running Android 7.0 (API 24) or higher

### Build & Run
1. Clone the repository:
   ```bash
   git clone https://github.com/romit077-hub/practical-7.git
   ```
2. Open the project in Android Studio.
3. Sync project with Gradle files.
4. Select a device/emulator and run the app.

## Usage

1. Launch the app to view the bundled demonstration profiles.
2. Tap **View on Map** on a profile to open its coordinates in Google Maps.

## License

Created for educational purposes under the Mobile Application Development (MAD) lab coursework.
