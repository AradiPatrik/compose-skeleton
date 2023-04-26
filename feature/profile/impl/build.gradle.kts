plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    `android-config`
}

android {
    namespace = "com.cardinalblue.profile.impl"

    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = versions.composeCompiler
    }
}

dependencies {
    applyFeatureCommon()
    implementation(profile.api())
    implementation(libs.compose)
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}
