pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Skeleton"
include(":app")
include(":common:platform")
include(":common:theme")
include(":common:domain")
include(":common:data:impl")
include(":common:data:api")
include(":feature:movie-search:api")
include(":feature:movie-search:impl")
include(":common:navigation")
include(":feature:featured-movies:api")
include(":feature:featured-movies:impl")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:movie-details:api")
include(":feature:movie-details:impl")
