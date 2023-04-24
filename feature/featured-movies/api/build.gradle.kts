plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    `android-config`
}

android {
    namespace = "com.cardinalblue.featured_movies"

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
