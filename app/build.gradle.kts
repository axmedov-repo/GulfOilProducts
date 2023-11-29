plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.gulfoil.pdsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gulfoil.pdsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
        buildConfigField("String", "BASE_URL", "\"https://gulf.ravshandev.uz/\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("Boolean", "LOGGING", "true")
        }
        release {
            buildConfigField("Boolean", "LOGGING", "false")
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
        buildConfig = true
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
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

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

    // GlideToVector
//    implementation("com.github.corouteam:GlideToVectorYou:v2.0.0")

    // PDF Viewer
    implementation("com.github.barteksc:android-pdf-viewer:2.8.2")

    // ViewPager Page Transformer
    implementation("danny.view-pager-2-page-transformers:horizontal-transformers:1.0.0")

    // Spinner
    implementation("com.github.skydoves:powerspinner:1.2.4")

    // Chuck
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    // nabinbhanbari Permission Library
    implementation("com.nabinbhandari.android:permissions:3.8")

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.4.1"))
    // Add the dependencies for the Crashlytics and Analytics libraries
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-compiler:2.48")

    // CircleView
    implementation("de.hdodenhof:circleimageview:3.1.0")
}

kapt {
    correctErrorTypes = true
}
