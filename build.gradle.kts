import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.DokkaConfiguration
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

val wicketVersion = "8.1.0"
val junitVersion = "5.3.1"
val servletApiVersion = "3.1.0"
val kotlinxHtmlVersion = "0.6.10"
val mavenPubName = "mavenJavaLibrary"

plugins {
    kotlin("jvm") version "1.3.0"
    id("jacoco")
    id("org.jetbrains.dokka") version "0.9.16"
    id("maven-publish")
}

//plugins.apply("org.jetbrains.dokka")

group = "org.kwicket"
version = "1.0.0-M1-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")

    compile("org.apache.wicket:wicket-core:$wicketVersion")
    compile("org.apache.wicket:wicket-devutils:$wicketVersion")
    compile("org.apache.wicket:wicket-bean-validation:$wicketVersion")

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
        create<MavenPublication>(mavenPubName) {
            from(components.getByName("java"))
            artifact(sourcesJar)
            artifact(javadocJar)
        }
    }
}