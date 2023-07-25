import org.gradle.kotlin.dsl.DependencyHandlerScope

// ===== feature modules =====
val DependencyHandlerScope.movieDetails get() = createProject(":feature:movie-details")
val DependencyHandlerScope.featuredMovies get() = createProject(":feature:featured-movies")
val DependencyHandlerScope.movieSearch get() = createProject(":feature:movie-search")
val DependencyHandlerScope.profile get() = createProject(":feature:profile")
