import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.41"
  application
}

val ktorVersion by extra("1.2.2")
val koinVersion by extra("2.0.1")
group = "com.accenture.cfe.dev"
version = "0.1-SNAPSHOT"

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation("io.ktor:ktor-server-netty:$ktorVersion")
  implementation("org.koin:koin-ktor:$koinVersion")
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

application {
  mainClassName = "com.accenture.cfe.dev.rpncalc.ApplicationKt"
}
