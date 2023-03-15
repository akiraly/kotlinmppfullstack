plugins {
    kotlin("multiplatform") version "1.8.10"
    kotlin("plugin.serialization") version "1.8.10"
    application
}

group = "me.kiral"
version = "1.0-SNAPSHOT"

repositories {
    //jcenter()
    mavenCentral()
    //maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
    jvmToolchain(17)
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                //cssSupport.enabled = true
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(platform("org.http4k:http4k-bom:4.40.1.0"))
                implementation("org.http4k:http4k-core")
                implementation("org.http4k:http4k-server-netty")
                implementation("io.ktor:ktor-server-netty:2.2.3")
                implementation("io.ktor:ktor-server-html-builder-jvm:2.2.3")
                implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.8.0")
            }
        }
        val jvmTest by getting
        val jsMain by getting {
            dependencies {
                //React, React DOM + Wrappers (chapter 3)
                implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.512"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")

                //Kotlin React Emotion (CSS) (chapter 3)
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")

                //Video Player (chapter 7)
                implementation(npm("react-player", "2.10.1"))

                //Share Buttons (chapter 7)
                implementation(npm("react-share", "4.4.0"))

                //Coroutines & serialization (chapter 8)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
            }
        }
        val jsTest by getting
    }
}

application {
    mainClass.set("me.kiral.application.ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
    from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
    dependsOn(tasks.named<Jar>("jvmJar"))
    classpath(tasks.named<Jar>("jvmJar"))
}