plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")

    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = "com.cardinalblue.skeleton"

    defaultConfig {
        applicationId = "com.cardinalblue.skeleton"
        versionCode = 1
        versionName = "1.0.0"
    }

    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = versions.composeCompiler
    }
}

dependencies {
    // ===== common =====
    implementation(
        theme(),
        platform(),
        domain(),
        navigation(),
        *data.all()
    )

    // ===== feature modules =====
    implementation(
        *movieSearch.all(),
        *movieDetails.all(),
        *profile.all()
    )

    // ===== android =====
    implementation(libs.android)

    // ===== compose =====
    implementation(libs.compose)

    // ===== dagger =====
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    // ===== test =====
    testImplementation(libs.unitTests)
    androidTestImplementation(libs.androidTests)

    // ===== debug =====
    debugImplementation(libs.debug)

    // ===== baseline profiles =====
    implementation(libs.profileInstaller)
}