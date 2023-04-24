import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentSelectionWithCurrent

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.devtools.ksp") version versions.ksp apply false
    id("com.github.ben-manes.versions") version versions.versionPlugin
}

buildscript {
    dependencies {
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}

tasks.register<Delete>("clean") {
    group = "clean"
    delete(rootProject.buildDir)
}

tasks.withType<DependencyUpdatesTask> {
    group = "dependency-check"
    rejectVersionIf {
        alphaVersions() or
                candidate.version.contains("-Beta")
    }
}

fun ComponentSelectionWithCurrent.alphaVersions() =
    candidate.version.contains("paging-compose").not() and candidate.version.contains("alpha")