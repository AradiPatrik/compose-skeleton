@file:Suppress("ClassName", "unused")

object versions {
    const val ksp = "1.8.22-1.0.11"
    const val versionPlugin = "0.47.0"
    const val core = "1.11.0-beta02"
    const val lifecycle = "2.6.1"
    const val coroutines = "1.7.2"
    const val composeCompiler = "1.4.8"
    const val compose = "1.4.6"
    const val composeTest = "1.4.3"
    const val dagger = "2.47"
    const val paging = "3.2.0-rc01"
    const val accompanist = "0.31.5-beta"
    const val retrofit = "2.9.0"
    const val moshi = "1.15.0"
    const val room = "2.5.2"
    const val mockk = "1.13.5"
    const val material3 = "1.1.0"
    const val extendedIcons = "1.5.0-beta03"
    const val activityCompose = "1.7.2"
    const val navigationCompose = "2.7.0-beta02"
    const val composeBom = "2023.06.01"
    const val landscapist = "2.2.2"
    const val logcat = "0.1"
    const val activityKtx = "1.7.2"
    const val mlKitFaceDetection = "16.1.5"
    const val glide = "4.15.1"
    const val gpuImage = "2.1.0"
    const val inAppReview = "2.0.1"
    const val datastore = "1.0.0"
}

object libs {
    val android = listOf(
        "androidx.core:core-ktx:${versions.core}",
        "androidx.lifecycle:lifecycle-runtime-ktx:${versions.lifecycle}",
        "androidx.activity:activity:${versions.activityKtx}",
        "androidx.activity:activity-ktx:${versions.activityKtx}",
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1",
        "androidx.lifecycle:lifecycle-viewmodel:2.6.1",
        "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"
    )
    val compose = listOf(
        Bom("androidx.compose:compose-bom:${versions.composeBom}"),
        "androidx.compose.ui:ui-tooling-preview",
        "androidx.compose.ui:ui-tooling",
        "androidx.compose.material3:material3:${versions.material3}",
        "androidx.compose.material:material-icons-extended:${versions.extendedIcons}",
        "androidx.activity:activity-compose:${versions.activityCompose}",
        "androidx.navigation:navigation-compose:${versions.navigationCompose}",
        "com.google.accompanist:accompanist-systemuicontroller:${versions.accompanist}",
    )
    val mlKitFaceDetection = listOf(
        "com.google.mlkit:face-detection:${versions.mlKitFaceDetection}",
    )
    val glide = listOf(
        "com.github.bumptech.glide:glide:${versions.glide}",
        "com.github.zjupure:webpdecoder:2.3.${versions.glide}"
    )
    const val glideAnnotationProcessor = "com.github.bumptech.glide:compiler:${versions.glide}"

    val datastore = "androidx.datastore:datastore-preferences:${versions.datastore}"

    val paging = listOf(
        "androidx.paging:paging-runtime-ktx:${versions.paging}",
        "androidx.paging:paging-compose:${versions.paging}"
    )
    const val permissionsCompose =
        "com.google.accompanist:accompanist-permissions:${versions.accompanist}"

    const val swipeRefreshCompose =
        "com.google.accompanist:accompanist-swiperefresh:${versions.accompanist}"

    const val palette =
        "androidx.palette:palette-ktx:1.0.0"

    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.coroutines}"

    val firebaseData = listOf(
        Bom("com.google.firebase:firebase-bom:32.2.0"),
        "com.google.firebase:firebase-firestore-ktx",
        "com.google.firebase:firebase-storage-ktx"
    )

    val firebaseCrashlytics = listOf(
        Bom("com.google.firebase:firebase-bom:32.0.0"),
        "com.google.firebase:firebase-crashlytics-ktx",
        "com.google.firebase:firebase-analytics-ktx"
    )

    val firebaseUi = "com.firebaseui:firebase-ui-storage:8.0.2"

    const val gpuImage =
        "jp.co.cyberagent.android:gpuimage:${versions.gpuImage}"

    const val dagger =
        "com.google.dagger:dagger:${versions.dagger}"

    const val daggerCompiler =
        "com.google.dagger:dagger-compiler:${versions.dagger}"

    val retrofit = listOf(
        "com.squareup.retrofit2:retrofit:${versions.retrofit}",
        "com.squareup.retrofit2:converter-moshi:${versions.retrofit}",
        "com.squareup.okhttp3:logging-interceptor:4.11.0",
        "com.squareup.moshi:moshi-adapters:${versions.moshi}",
    )

    val moshiCompiler = "com.squareup.moshi:moshi-kotlin-codegen:${versions.moshi}"
    val moshi = "com.squareup.moshi:moshi-kotlin:${versions.moshi}"
    val room = listOf(
        "androidx.room:room-ktx:${versions.room}",
        "androidx.room:room-runtime:${versions.room}",
        "androidx.room:room-paging:${versions.room}",
    )
    const val roomCompiler =
        "androidx.room:room-compiler:${versions.room}"

    val unitTests = listOf(
        "junit:junit:4.13.2",
        "io.mockk:mockk:${versions.mockk}",
        "io.mockk:mockk-agent-jvm:${versions.mockk}",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${versions.coroutines}"
    )
    val androidTests = listOf(
        "androidx.test.ext:junit:1.1.5",
        "androidx.test.espresso:espresso-core:3.5.1",
        "androidx.compose.ui:ui-test-junit4:${versions.composeTest}"
    )
    const val uiautomator =
        "androidx.test.uiautomator:uiautomator:2.2.0"

    const val macroBenchmark =
        "androidx.benchmark:benchmark-macro-junit4:1.1.0-beta04"

    const val profileInstaller =
        "androidx.profileinstaller:profileinstaller:1.3.1"

    val landscapist = listOf(
        "com.github.skydoves:landscapist-glide:${versions.landscapist}",
        "com.github.skydoves:landscapist-placeholder:${versions.landscapist}",
        "com.github.skydoves:landscapist-animation:${versions.landscapist}",
        "com.github.skydoves:landscapist-transformation:${versions.landscapist}",
        "com.github.skydoves:landscapist-palette:${versions.landscapist}",
        "com.github.skydoves:landscapist-coil:${versions.landscapist}",
    )

    val amplitude = "com.amplitude:analytics-android:1.7.1"

    val logcat = "com.squareup.logcat:logcat:0.1"
    val debug = listOf(
        "androidx.compose.ui:ui-test-manifest:${versions.composeTest}"
    )
    val inAppReview = listOf(
        "com.google.android.play:review:${versions.inAppReview}",
        "com.google.android.play:review-ktx:${versions.inAppReview}",
    )
}