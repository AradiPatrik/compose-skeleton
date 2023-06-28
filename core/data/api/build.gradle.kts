plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")

    `android-config`
}

android {
    namespace = "com.cardinalblue.data.api"
}

dependencies {
    implementation(domain())
    implementation(libs.coroutines)
}