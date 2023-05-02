plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    `android-config`
}

android {
    namespace = "com.cardinalblue.moviedetails.impl"
    applyCompose()
}

dependencies {
    applyFeatureCommon()
    implementation(movieDetails.api())
    implementation(libs.compose, libs.coroutines, libs.paging, libs.landscapist)

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}