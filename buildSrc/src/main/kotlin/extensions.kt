import org.gradle.api.artifacts.dsl.DependencyHandler

data class Bom(val bom: String)

fun DependencyHandler.implementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        applyDependency("implementation", dep)
    }
}

fun DependencyHandler.debugImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        applyDependency("debugImplementation", dep)
    }
}

fun DependencyHandler.androidTestImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        applyDependency("androidTestImplementation", dep)
    }
}

fun DependencyHandler.testImplementation(dependencyNotation: List<Any>) {
    for (dep in dependencyNotation) {
        applyDependency("testImplementation", dep)
    }
}

fun DependencyHandler.applyDependency(configurationName: String, dependency: Any) {
    if (dependency is Bom) {
        add(configurationName, platform(dependency.bom))
    } else {
        add(configurationName, dependency)
    }
}