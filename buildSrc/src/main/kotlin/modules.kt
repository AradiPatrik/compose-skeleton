import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

data class Project(
    val scope: DependencyHandlerScope,
    val path: String
) {
    fun api() = scope.project(path + ":api")
    fun impl() = scope.project(path + ":impl")
    fun all() = arrayOf(
        api(),
        impl()
    )
}

fun DependencyHandlerScope.createProject(path: String) = Project(this, path)

fun DependencyHandlerScope.domain() = project(":common:domain")
fun DependencyHandlerScope.platform() = project(":common:platform")
fun DependencyHandlerScope.theme() = project(":common:theme")
fun DependencyHandlerScope.navigation() = project(":common:navigation")

val DependencyHandlerScope.data get() = createProject(":common:data")

// ===== feature modules =====
val DependencyHandlerScope.featuredMovies get() = createProject(":feature:featured-movies")
val DependencyHandlerScope.movieSearch get() = createProject(":feature:movie-search")
val DependencyHandlerScope.profile get() = createProject(":feature:profile")

fun DependencyHandlerScope.applyFeatureCommon() {
    implementation(
        domain(),
        platform(),
        theme(),
        navigation(),
        data.api(),
    )
}