plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigationSafeArgs)
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



    // Kotlin

    implementation ("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation( "androidx.navigation:navigation-ui-ktx:2.6.0")


    implementation("com.tbuonomo:dotsindicator:4.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("com.github.bosphere.android-fadingedgelayout:fadingedgelayout:1.0.0")

    implementation ("com.github.chrisbanes:PhotoView:2.3.0")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:11.1.0")

    implementation ("com.kyleduo.switchbutton:library:2.1.0")
}

