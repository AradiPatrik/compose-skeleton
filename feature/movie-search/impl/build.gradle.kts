plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")

    `android-config`
}

android {
    namespace = "com.cardinalblue.movie_search.impl"
    applyCompose()
}

dependencies {
    applyFeatureCommon()
    implementation(movieSearch.api(), featuredMovies.api())

    implementation(
        libs.compose,
        libs.coroutines,
        libs.paging,
        libs.landscapist,
        libs.logcat
    )

    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}
