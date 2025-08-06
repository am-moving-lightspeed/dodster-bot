plugins {
    kotlin("jvm") version "1.9.25"
}

group = "com.github.am_moving_lightspeed"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.inject:guice:7.0.0")
    implementation("org.telegram:telegrambots:6.9.7.1")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.github.am_moving_lightspeed.tg.bot.dodster.MainKt"
        attributes["Class-Path"] = configurations
            .runtimeClasspath.get()
            .files
            .joinToString(" ") { it.path }
    }
}
