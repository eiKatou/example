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
    implementation("com.amazonaws:aws-lambda-java-core:1.2.1")
    implementation("com.amazonaws:aws-lambda-java-events:3.1.0")
    runtimeOnly("com.amazonaws:aws-lambda-java-log4j2:1.2.0")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
tasks {
    // thank you: https://github.com/davidmerrick/aion/blob/master/build.gradle.kts

    val buildZip by creating(Zip::class) {
        from(compileKotlin)
        from(processResources)
        into("lib") {
            from(configurations.runtimeClasspath)
        }
    }
}
application {
    mainClassName = "MainKt"
}