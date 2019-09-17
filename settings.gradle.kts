val kotlinVersion: String by settings

val artifactoryPluginVersion: String by settings
val bintrayPluginVersion: String by settings
val dokkaPluginVersion: String by settings
val releasePluginVersion: String by settings

pluginManagement {
    plugins {
        kotlin("jvm") version kotlinVersion
        id("org.jetbrains.dokka") version dokkaPluginVersion
        id("net.researchgate.release") version releasePluginVersion
        id("com.jfrog.artifactory") version artifactoryPluginVersion
        id("com.jfrog.bintray") version bintrayPluginVersion
    }
}

rootProject.name = "kwicket-core"
