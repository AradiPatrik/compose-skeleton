plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

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
    implementation(libs.compose)
}
