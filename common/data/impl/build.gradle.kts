plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

    `android-config`
}

android {
    namespace = "com.cardinalblue.data.impl"

    buildFeatures.buildConfig = true
}

dependencies {
    implementation(domain(), platform(), data.api())

    // ===== android =====
    implementation(libs.coroutines)
    implementation(libs.paging)

    // ===== dagger =====
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    // ===== retrofit =====
    implementation(libs.retrofit)
    ksp(libs.moshiCompiler)

    // ===== room =====
    implementation(libs.room)
    ksp(libs.roomCompiler)
}

secrets {
    propertiesFileName = "local.properties"
    defaultPropertiesFileName = "local.default.properties"
    ignoreList.add("sdk.*")
}