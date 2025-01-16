plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.ozinshe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.ozinshe"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    val nav_version = "2.7.7"

    // Kotlin

    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")

    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")
// Coroutines to make the HTTP requests asynchronous(In the background thread)

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
}

