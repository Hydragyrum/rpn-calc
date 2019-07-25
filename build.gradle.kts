import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.41"
  groovy
  application
}

val junitVersion by extra("5.5.1")
val spockVersion by extra("1.3-groovy-2.5")
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

  testImplementation("org.spockframework:spock-core:$spockVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine:$junitVersion")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "1.8"
}

application {
  mainClassName = "com.accenture.cfe.dev.rpncalc.ApplicationKt"
}
