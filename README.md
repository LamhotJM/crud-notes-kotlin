````markdown
# Note CRUD + Search (Room) — Installation & Setup Guide

This guide walks you through downloading, installing, and running the Note CRUD + Search Android app powered by Jetpack Compose and Room.

---

## 1. Project Overview

A lightweight Android app showcasing:

- **Room** – local SQLite persistence via DAO, Entity, Database.  
- **CRUD** – Create, Read, Update, Delete note entries.  
- **Search** – realtime filter of notes list.  
- **Jetpack Compose** – modern UI toolkit for layouts and state.  


## 2. Prerequisites

- **Operating System**: Windows, macOS, or Linux with Android Studio support.  
- **Android Studio**: Arctic Fox (2020.3.1) or later.  
- **JDK**: Java 8 (bundled); Kotlin 1.8+ support.  
- **Android SDK**: API 34 installed.  
- **Gradle Wrapper**: will auto-download Gradle 7.5+.  


## 3. Download or Clone the Repo

```bash
# via HTTPS:
git clone https://github.com/LamhotJM/crud-notes-kotlin.git
cd NoteApp
````

Or download and unzip the provided ZIP archive into your workspace.

## 4. Configure Global Properties

At the **project root**, create or update `gradle.properties`:

```properties
# Enable AndroidX and Jetifier
android.useAndroidX=true
android.enableJetifier=true
```

## 5. Verify `settings.gradle.kts`

Ensure your `settings.gradle.kts` includes proper plugin repositories:

```kotlin
pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "NoteApp"
include(":app")
```

## 6. Import into Android Studio

1. **Open** Android Studio.
2. **File → Open** and select the project’s **folder** (where `settings.gradle.kts` resides).
3. Let Gradle sync and download dependencies.
4. If prompted, **update Gradle wrapper** to match the recommended version.

## 7. Module Gradle Config

In `app/build.gradle.kts`, confirm these blocks are present:

```kotlin
android {
  compileSdk = 34

  defaultConfig {
    minSdk = 21
    targetSdk = 34
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    // Compose on Android requires Java 8 target
    jvmTarget = "1.8"
  }

  buildFeatures { compose = true }
  composeOptions { kotlinCompilerExtensionVersion = "1.5.0" }
}

dependencies {
  implementation("androidx.room:room-runtime:2.5.2")
  kapt("androidx.room:room-compiler:2.5.2")
  implementation("androidx.room:room-ktx:2.5.2")
  implementation("androidx.activity:activity-compose:1.7.2")
  implementation("androidx.compose.material:material:1.5.0")
  // other Compose + Lifecycle deps...
}
```

## 8. Run the App

1. **Select** a *Virtual Device* (AVD) or plug in your Android device.
2. Click **▶ Run** in Android Studio.
3. Wait for the APK to install and the app to launch.

**Test CRUD & Search**:

* Tap **Add** → enter title/content → confirm new note appears.
* Tap on a note → edit fields → **Save** updates it.
* Use the **Search** field at top to filter notes by keyword.
* Tap **Delete** icon to remove a note.

## 9. Troubleshooting

| Issue                                        | Solution                                                          |
| -------------------------------------------- | ----------------------------------------------------------------- |
| `android.useAndroidX` warning                | Add `android.useAndroidX=true` in root `gradle.properties`.       |
| `Unknown JVM target: 21`                     | Set `jvmTarget = "1.8"` in `kotlinOptions`.                       |
| Plugin not found (`com.android.application`) | Ensure `pluginManagement.repositories` includes `google()`.       |
| Gradle sync errors                           | Run `./gradlew --refresh-dependencies` or upgrade Gradle wrapper. |

## 10. Further Improvements

* **Encrypted Room**: integrate SQLCipher for data security.
* **Paging**: use `Paging 3` for large note sets.
* **Multi-module**: separate data and UI into libraries.
* **Dark Mode**: add Compose theming with dark theme support.

---

*Happy coding!*

```
```
