plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    `android-config`
}

android {
    namespace = "com.cardinalblue.theme"
    applyCompose()
}

dependencies {
    implementation(libs.compose)
}
