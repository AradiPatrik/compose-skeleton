plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    `android-config`
}

android {
    namespace = "com.cardinalblue.navigation"
    applyCompose()
}

dependencies {
    implementation(platform())
    implementation(libs.compose, libs.logcat)
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}