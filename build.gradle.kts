// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.google.dagger.hilt.android") version "2.47" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
}

buildscript {
    val agp_version by extra("8.1.3")
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven("https://www.jitpack.io")
    }
    dependencies {
        // Safe Args
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")

        // Add the dependency for the Crashlytics Gradle plugin
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.9")

        // Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44.2")
    }
}