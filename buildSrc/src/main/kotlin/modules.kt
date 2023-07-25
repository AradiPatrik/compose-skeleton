import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

data class Project(
    val scope: DependencyHandlerScope,
    val path: String
) {
    fun api() = scope.project("$path:api")
    fun impl() = scope.project("$path:impl")
    fun all() = arrayOf(
        api(),
        impl()
    )
}

fun DependencyHandlerScope.createProject(path: String) = Project(this, path)

fun DependencyHandlerScope.domain() = project(":core:domain")
fun DependencyHandlerScope.platform() = project(":core:platform")
fun DependencyHandlerScope.theme() = project(":core:theme")
fun DependencyHandlerScope.navigation() = project(":core:navigation")

val DependencyHandlerScope.data get() = createProject(":core:data")

fun DependencyHandlerScope.applyFeatureCommon() {
    implementation(
        domain(),
        platform(),
        theme(),
        navigation(),
        data.api(),
    )
}