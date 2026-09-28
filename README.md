# MAD Practical 7 - Person Directory & Map Viewer

A native Android application built with Kotlin that fetches person profile data from a remote JSON REST API, caches it locally using an offline-first SQLite database, displays it in a RecyclerView, and provides Google Maps location viewing for each profile.

---

## 📱 Features

- **Person Profile List**: Displays person details including Name, Phone Number, Email, Address, Latitude, and Longitude.
- **Offline-First Architecture**: Loads cached profiles immediately from the local SQLite database upon launch, then updates in the background.
- **Resilient Network Handling**: Seamless fallback mechanism supporting both `JSONArray` and `JSONObject` formats with automatic mock data recovery on HTTP 401 / network errors.
- **Google Maps Integration**: "View on Map" feature launches Google Maps using geo-intents (`geo:lat,lng`) to display exact coordinates.
- **Material 3 UI**: Clean layout styled with Material Design components supporting both Light and Dark modes.

---

## 🛠️ Tech Stack & Architecture

- **Language**: Kotlin
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 37 (Android 15)
- **Database**: SQLite (`SQLiteOpenHelper`)
- **Async Processing**: Kotlin Coroutines (`Dispatchers.IO` & `Dispatchers.Main`)
- **UI Components**: `RecyclerView`, `ConstraintLayout`, `CardView`, `MaterialButton`
- **Network & JSON**: `HttpURLConnection`, `org.json` (`JSONArray`, `JSONObject`)

---

## 📂 Project Structure

```text
com.example.a24012011189_mad_p7/
├── DatabaseHelper.kt      # SQLite database helper for CRUD operations on person records
├── HttpRequest.kt         # REST client for fetching remote JSON profiles with error fallback
├── MainActivity.kt        # Main screen hosting RecyclerView and coordinating DB/Network sync
├── MapActivity.kt         # Location detail screen with Google Maps Intent launcher
├── Person.kt              # Serializable data model representing a Person profile
├── PersonAdapter.kt       # RecyclerView adapter binding Person items to layout viewholders
└── res/
    ├── layout/
    │   ├── activity_main.xml    # Main container with RecyclerView and status view
    │   ├── activity_map.xml     # Location details and map action view
    │   └── item_person.xml      # Individual person list item card layout
    └── values/
        ├── strings.xml          # Translatable string resources
        └── themes.xml           # App theme definitions
```

---

## 🚀 Getting Started

### Prerequisites
- **Android Studio**: Jellyfish or newer
- **JDK**: Java 11 or higher
- **Android Device / Emulator**: Running Android 7.0 (API level 24) or higher

### Build & Run
1. Clone the repository:
   ```bash
   git clone https://github.com/romit077-hub/practical-7.git
   ```
2. Open the project in **Android Studio**.
3. Sync project with Gradle files (`File > Sync Project with Gradle Files`).
4. Select your target device/emulator and click **Run (Shift + F10)**.

---

## 📸 Screenshots & Usage

1. **Launch App**: View cached or fetched person profiles in a scrollable list.
2. **View Location**: Tap **View on Map** under any person's profile to inspect location coordinates and trigger Google Maps.

---

## 📄 License

This project is created for educational purposes under the Mobile Application Development (MAD) lab coursework.
