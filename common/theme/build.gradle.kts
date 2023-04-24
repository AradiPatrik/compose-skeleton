plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    `android-config`
}

android {
    namespace = "com.cardinalblue.theme"

    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = versions.composeCompiler
    }
}

dependencies {
    implementation(libs.compose)
}
