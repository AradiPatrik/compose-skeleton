plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    `android-config`
}

android {
    namespace = "com.cardinalblue.featuredmovies.api"
    applyCompose()
}

dependencies {
    implementation(navigation())
    implementation(libs.compose)
}
