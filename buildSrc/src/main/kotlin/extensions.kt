import com.android.build.gradle.LibraryExtension
import org.gradle.api.artifacts.dsl.DependencyHandler

data class Bom(val bom: String)

fun DependencyHandler.implementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        if (dep is List<*>) {
            implementation(dep as List<Any>)
        } else {
            applyDependency("implementation", dep)
        }
    }
}

fun DependencyHandler.implementation(vararg dependencyNotation: Any) {
    implementation(dependencyNotation.toList())
}

fun DependencyHandler.debugImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        if (dep is List<*>) {
            debugImplementation(dep as List<Any>)
        } else {
            applyDependency("debugImplementation", dep)
        }
    }
}

fun DependencyHandler.debugImplementation(vararg dependencyNotation: Any) {
    debugImplementation(dependencyNotation.toList())
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        if (dep is List<*>) {
            androidTestImplementation(dep as List<Any>)
        } else {
            applyDependency("androidTestImplementation", dep)
        }
    }
}

fun DependencyHandler.androidTestImplementation(vararg dependencyNotation: Any) {
    androidTestImplementation(dependencyNotation.toList())
}

fun DependencyHandler.testImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        if (dep is List<*>) {
            testImplementation(dep as List<Any>)
        } else {
            applyDependency("testImplementation", dep)
        }
    }
}

fun DependencyHandler.testImplementation(vararg dependencyNotation: Any) {
    testImplementation(dependencyNotation.toList())
}

fun DependencyHandler.applyDependency(configurationName: String, dependency: Any) {
    if (dependency is Bom) {
        add(configurationName, platform(dependency.bom))
    } else {
        add(configurationName, dependency)
    }
}

fun LibraryExtension.applyCompose() {
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = versions.composeCompiler
    }
}