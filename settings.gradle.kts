pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "AndroidCrypto"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":features")
include(":features:rsa")
include(":design-system")
include(":features:aes")
include(":features:ecc")
include(":features:aes-rsa")
