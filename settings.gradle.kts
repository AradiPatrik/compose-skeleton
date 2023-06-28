@file:Suppress("UnstableApiUsage")

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
include(":core:platform")
include(":core:theme")
include(":core:domain")
include(":core:data:impl")
include(":core:data:api")
include(":core:navigation")

include(":feature:movie-search:api")
include(":feature:movie-search:impl")
include(":feature:featured-movies:api")
include(":feature:featured-movies:impl")
include(":feature:profile:api")
include(":feature:profile:impl")
include(":feature:movie-details:api")
include(":feature:movie-details:impl")
