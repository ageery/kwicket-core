import com.jfrog.bintray.gradle.BintrayExtension
import groovy.lang.GroovyObject
import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import java.net.URL

val publicationName = "maven"

val wicketVersion: String by project
val junitVersion: String by project
val servletApiVersion: String by project
val kotlinxHtmlVersion: String by project

val bintrayUser: String? by project
val bintrayKey: String? by project

plugins {
    kotlin("jvm")
    `maven-publish`
    id("jacoco")
    id("org.jetbrains.dokka")
    id("com.jfrog.bintray")
    id("com.jfrog.artifactory")
    id("net.researchgate.release")
}

group = "org.kwicket"

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += "-XXLanguage:+InlineClasses"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {
    compile(kotlin("stdlib-jdk8"))

    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")

    compile("javax.validation:validation-api:2.0.1.Final") // FIXME: why doesn't bean-validation pull this in?

    compile("org.apache.wicket:wicket-core:$wicketVersion")
    compile("org.apache.wicket:wicket-devutils:$wicketVersion")
    compile("org.apache.wicket:wicket-bean-validation:$wicketVersion")
    compile("org.apache.wicket:wicket-spring:$wicketVersion")
    compile("org.apache.wicket:wicket-native-websocket-core:$wicketVersion")
    compile("org.apache.wicket:wicket-guice:$wicketVersion")

    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testCompile("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")

    compile("javax.servlet:javax.servlet-api:$servletApiVersion")
    testRuntime("junit:junit:4.12")

    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
}

val dokkaJavadocTask = tasks.withType<DokkaTask> {
    outputFormat = "html"
    externalDocumentationLink(delegateClosureOf<DokkaConfiguration.ExternalDocumentationLink.Builder> {
        url = URL("https://ci.apache.org/projects/wicket/apidocs/8.x/")
    })
    cacheRoot = "default"
}

val sourcesJar by tasks.creating(Jar::class) {
    classifier = "sources"
    from(sourceSets["main"].allSource)
    setDuplicatesStrategy(DuplicatesStrategy.EXCLUDE)
}

val javadocJar by tasks.creating(Jar::class) {
    dependsOn(dokkaJavadocTask)
    classifier = "javadoc"
}

configure<PublishingExtension> {
    publications {
        create<MavenPublication>(publicationName) {
            from(components.getByName("java"))
            artifact(sourcesJar)
            artifact(javadocJar)
        }
    }
}

bintray {
    user = bintrayUser
    key = bintrayKey
    setPublications(publicationName)
    publish = true
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "kwicket-core"
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/ageery/kwicket-core.git"
        githubRepo = "ageery/kwicket-core"
        githubReleaseNotesFile = "README.adoc"
        version(delegateClosureOf<BintrayExtension.VersionConfig> {
            name = "$version"
            vcsTag = "kwicket-core-$version"
        })
    })
}

artifactory {
    setContextUrl("https://oss.jfrog.org/artifactory")
    publish(delegateClosureOf<PublisherConfig> {
        repository(delegateClosureOf<GroovyObject> {
            setProperty("repoKey", "oss-snapshot-local")
            setProperty("username", bintrayUser)
            setProperty("password", bintrayKey)
            setProperty("maven", true)
        })
        defaults(delegateClosureOf<GroovyObject> {
            invokeMethod("publications", publicationName)
            setProperty("publishArtifacts", true)
            setProperty("publishPom", true)
        })
    })
}
