
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	id("org.flywaydb.flyway") version "6.0.8"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("plugin.jpa") version "1.3.50"
	kotlin("plugin.allopen") version "1.3.50"
//	kotlin("kapt") version "1.3.50"
}

group = "com.tmp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")

//	kapt("org.springframework.boot:spring-boot-configuration-processor")

	// Log
	implementation("net.logstash.logback:logstash-logback-encoder:6.2")
	implementation("ch.qos.logback.contrib:logback-json-classic:0.1.5")
	implementation("ch.qos.logback.contrib:logback-jackson:0.1.5")

	// Mysql
	implementation("mysql:mysql-connector-java:5.1.47")

	// JWT
	implementation("io.jsonwebtoken:jjwt:0.9.1")

	// rest API
	implementation("org.apache.httpcomponents:httpclient:4.5")

	// Flyway
	implementation("org.flywaydb:flyway-core")

	// AWS
	implementation(platform("com.amazonaws:aws-java-sdk-bom:1.11.228"))

	// QueryDSL
	implementation("com.querydsl:querydsl-jpa:4.2.1")
//	kapt("com.querydsl:querydsl-apt:4.2.1:jpa")

	// Swagger
	implementation("io.springfox:springfox-swagger-ui:2.9.2")
	implementation("io.springfox:springfox-swagger2:2.9.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}


sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
	kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

