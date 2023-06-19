plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    id("com.google.devtools.ksp")

    `android-config`
}

android {
    namespace = "com.cardinalblue.moviedetails.impl"
    applyCompose()
}

dependencies {
    applyFeatureCommon()
    implementation(movieDetails.api())
    implementation(libs.compose, libs.coroutines, libs.paging, libs.landscapist, libs.logcat)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(libs.moshi)
    ksp(libs.moshiCompiler)
}