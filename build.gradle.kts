import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "me.klop233"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    // Minecraft development
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

dependencies {
    testImplementation(kotlin("test"))
    // Bungeecord
    compileOnly("net.md-5:bungeecord-api:1.19-R0.1-SNAPSHOT")
    compileOnly("com.github.azbh111:spigot-1.12.2:R")
    compileOnly("io.github.dreamvoid:MiraiMC-Integration:1.7.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}