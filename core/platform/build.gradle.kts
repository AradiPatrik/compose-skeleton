plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.devtools.ksp")

    `android-config`
}

android {
    namespace = "com.cardinalblue.platform"
}

dependencies {
    implementation(libs.coroutines)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(libs.moshi)
    ksp(libs.moshiCompiler)
}
