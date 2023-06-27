plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")

    `android-config`
}

android {
    namespace = "com.cardinalblue.platform"
}

dependencies {
    implementation(libs.coroutines)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    kapt(libs.moshiCompiler)
    implementation(libs.moshi)
}
