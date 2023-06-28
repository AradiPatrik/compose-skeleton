plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")

    `android-config`
}

android {
    namespace = "com.cardinalblue.domain"
}

dependencies {
    implementation(libs.coroutines, libs.paging)
}