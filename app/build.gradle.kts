plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.axmedov.gulfapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.axmedov.gulfapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    // Hilt
//    implementation("com.google.dagger:hilt-android:2.44.2")
//    annotationProcessor("com.google.dagger:hilt-compiler:2.44")

    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    // ViewBinding
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.9")

    // ViewModelProviders
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // lifeCycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // SecureSharedPreference
    implementation("com.scottyab:secure-preferences-lib:0.1.7")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // PDF Viewer
    implementation("com.github.barteksc:android-pdf-viewer:3.2.0-beta.1")

    // ViewPager Page Transformer
    implementation("danny.view-pager-2-page-transformers:horizontal-transformers:1.0.0")

    // Spinner
    implementation("com.github.skydoves:powerspinner:1.2.4")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.4.1"))
    // Add the dependencies for the Crashlytics and Analytics libraries
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-analytics-ktx")
}
