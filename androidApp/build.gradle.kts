plugins {
    id("com.android.application")
    kotlin("android")
}

val kotlinVersion = "1.4.0"
val jetpackCompose = "1.0.0-beta03"
val composeActivitiesIntegration = "1.3.0-alpha06"
val composeViewModelIntegration = "1.0.0-alpha04"

dependencies {
    implementation(project(":shared"))

    implementation("androidx.compose.ui:ui:$jetpackCompose")

    // for preview
    implementation("androidx.compose.ui:ui-tooling:$jetpackCompose")

    implementation("androidx.compose.material:material:$jetpackCompose")

    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation("androidx.compose.foundation:foundation:$jetpackCompose")

    implementation("androidx.activity:activity-compose:$composeActivitiesIntegration")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$composeViewModelIntegration")
    implementation("androidx.appcompat:appcompat:1.2.0")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.jflavio.kmmtest.android"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = jetpackCompose
    }
}