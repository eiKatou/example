import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "net.eikatou"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation (group="org.jetbrains.kotlinx", name="kotlinx-coroutines-core", version="1.3.0")
    implementation (group="com.amazonaws", name="aws-java-sdk-bom", version="1.11.908")
    implementation (group="com.amazonaws", name="aws-java-sdk-sns", version="1.11.908")
    implementation (group="com.amazonaws", name="aws-java-sdk-sqs", version="1.11.908")
    implementation (group="com.google.code.gson", name="gson", version="2.8.6")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClassName = "MainKt"
}