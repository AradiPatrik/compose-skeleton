// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.google.devtools.ksp") version versions.ksp apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}