plugins {
    idea
	java
	id("java-test-fixtures")
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "com.amm"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_19
}

springBoot {
	mainClass.value("com.amm.certs.infrastructure.CertificationsApplication")
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
    // rest model validation
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    //
	implementation("org.flywaydb:flyway-core")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    // openapi
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.3")
    // ShedLock
    val shedlockVersion = "5.2.0"
    implementation("net.javacrumbs.shedlock:shedlock-spring:$shedlockVersion")
    implementation("net.javacrumbs.shedlock:shedlock-provider-jdbc-template:$shedlockVersion")
	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

testing {
	suites {
		fun setupDependenciesFor(moduleName: String) {
			// this is for lib dependency inheritance
			with(configurations) {
				named("${moduleName}Implementation") {
					extendsFrom(configurations["testImplementation"])
				}
				named("${moduleName}RuntimeOnly") {
					extendsFrom(configurations["testRuntimeOnly"])
				}
			}
		}

		val test by getting(JvmTestSuite::class) {
			useJUnitJupiter()
		}

		configureEach {
			if (this is JvmTestSuite) {
				useJUnitJupiter()
				dependencies {
					implementation(project())
					compileOnly(project())
					runtimeOnly(project())
					annotationProcessor(project())
				}
				targets {
					all {
						testTask.configure {
							shouldRunAfter(test)
						}
					}
				}
			}
		}

		val acceptanceTest by registering(JvmTestSuite::class)
		val contractTest by registering(JvmTestSuite::class)
		val integrationTest by registering(JvmTestSuite::class)
		setupDependenciesFor(acceptanceTest.name)
		setupDependenciesFor(contractTest.name)
		setupDependenciesFor(integrationTest.name)
		tasks.named("check") {
			dependsOn(testing.suites.named(acceptanceTest.name))
			dependsOn(testing.suites.named(contractTest.name))
			dependsOn(testing.suites.named(integrationTest.name))
		}
	}
}
