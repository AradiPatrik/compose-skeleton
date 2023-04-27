# Skeleton for new compose applications
## How to run
Put the following line into your `local.properties` file on the root of the project
```gradle
tmdb_key=replace_this_with_api_key
```

## Architecture
[Scalable multi-module architecture for Jetpack Compose apps with Pavlo Stavytsky](https://www.youtube.com/watch?v=j_rEor4YnNc)
## Architecture
The project implements a scalable multi-module architecture that aims to achieve the following goals:
- **Separation of concerns** - strict isolation and separation of logic between features for easier *testability* and *maintainability* of a codebase.
- **Scalability** - the architecture is applicable for projects that use hundreds or thousands Gradle modules.
- **Dependency graph optimization** - allows keeping a developer productivity on a decent level by preventing long build times and ensuring efficient incremental builds.

All the project modules could be separated into **3** types:
- **Feature modules** - which hold a logic related to a specific feature and consist of **API** and **Impl** modules. Former are framework-agnostic and hold lightweight interfaces as feature entry points.
- **Library modules** - commonly used logic by other features.
- **Injector modules** - modules that build a dependency graph of the app. E.g. *app* module.


![Feature module architecture](architecture.png)

## Navigatoin
[Modular Navigation with Jetpack Compose](https://medium.com/google-developer-experts/modular-navigation-with-jetpack-compose-fda9f6b2bef7)
