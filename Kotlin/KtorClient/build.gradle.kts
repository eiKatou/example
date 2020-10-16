import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    application
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-cio:1.4.0")
    implementation("io.ktor:ktor-client-apache:1.4.0")
    implementation("io.ktor:ktor-client-gson:1.4.0")
    implementation("io.ktor:ktor-client-logging-jvm:1.4.0")
    implementation("org.slf4j:slf4j-simple:1.7.26")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
application {
    mainClassName = "MainKt"
}