plugins {
  id("org.springframework.boot") version "3.5.3"
  id("io.spring.dependency-management") version "1.1.7"
  kotlin("jvm") version "1.9.23"
}

group = "com.techie"
version = "0.0.1-SNAPSHOT"
description = "spring-ai-rag-tutorial"

java {
  sourceCompatibility = JavaVersion.VERSION_21
  targetCompatibility = JavaVersion.VERSION_21
}

repositories {
  mavenCentral()
}

extra["springAiVersion"] = "1.0.0"

dependencyManagement {
  imports {
    mavenBom("org.springframework.ai:spring-ai-bom:${property("springAiVersion")}")
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.ai:spring-ai-starter-model-ollama")
  implementation("org.springframework.ai:spring-ai-advisors-vector-store")
  implementation("org.springframework.ai:spring-ai-starter-vector-store-pgvector")
  // Enable to use docker
  //runtimeOnly("org.springframework.boot:spring-boot-docker-compose")
  implementation("org.springframework.ai:spring-ai-tika-document-reader")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.boot:spring-boot-testcontainers")
  testImplementation("org.springframework.ai:spring-ai-spring-boot-testcontainers")
  testImplementation("org.testcontainers:junit-jupiter")
  testImplementation("org.testcontainers:ollama")
  testImplementation("org.testcontainers:postgresql")
}

tasks.withType<Test> {
  useJUnitPlatform()
}