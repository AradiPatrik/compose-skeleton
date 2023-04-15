import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.devtools.ksp") version versions.ksp apply false
    id("com.github.ben-manes.versions") version versions.versionPlugin
}

tasks.register<Delete>("clean") {
    group = "clean"
    delete(rootProject.buildDir)
}

tasks.withType<DependencyUpdatesTask> {
    group = "dependency-check"
    rejectVersionIf {
        candidate.version.contains("alpha") or
                candidate.version.contains("-Beta")
    }
}