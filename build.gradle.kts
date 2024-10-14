plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"
    id("com.gradleup.shadow") version "8.3.3"
    `maven-publish`
}

group = "org.sayandev"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.mamoe.yamlkt:yamlkt:0.13.0")
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.sayandev.MainKt"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["shadow"])
        }
    }

    repositories {
        maven {
            name = "sayandevelopment-repo"
            url = uri("https://repo.sayandev.org/snapshots/")

            credentials {
                username = System.getenv("REPO_SAYAN_USER") ?: project.findProperty("repo.sayan.user") as String?
                password = System.getenv("REPO_SAYAN_TOKEN") ?: project.findProperty("repo.sayan.token") as String?
            }
        }
    }
}