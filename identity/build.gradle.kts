import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.10"
    id("java-test-fixtures")
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.graalvm.buildtools.native") version "0.9.24"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

group = "com.amm"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_19
    targetCompatibility = JavaVersion.VERSION_19
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(19))
    }
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(19))
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "19"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

springBoot {
    mainClass.value("com.amm.identity.infrastructure.framework.IdentityApplicationKt")
}


repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2022.0.4"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.kafka:spring-kafka")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    // test containers
    testImplementation("org.testcontainers:testcontainers:1.19.0")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
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
