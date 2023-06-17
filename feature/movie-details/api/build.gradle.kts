plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")

    `android-config`
}

android {
    namespace = "com.cardinalblue.moviedetails.api"
    applyCompose()
}

dependencies {
    implementation(navigation())
    implementation(libs.compose, libs.moshi)
    ksp(libs.moshiCompiler)
}
