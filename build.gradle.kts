plugins {
    id("java-gradle-plugin")
    id("com.gradle.plugin-publish") version "1.2.1"
    kotlin("jvm") version "1.9.24"
    id("maven-publish")
}

group = "org.codegen"
version = "1.0.4"

repositories {
    mavenCentral()
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
    withSourcesJar()
    withJavadocJar()
}

dependencies {
    implementation("org.freemarker:freemarker:2.3.32")
    implementation("org.yaml:snakeyaml:2.2")
    implementation("com.mysql:mysql-connector-j:8.4.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

gradlePlugin {
    plugins {
        create("codegenPlugin") {
            id = "org.codegen"
            implementationClass = "org.codegen.CodegenPlugin"
            displayName = "DB Codegen Plugin"
            description = "Generates boilerplate code from DB schema at build time"
            website.set("https://github.com/myeongju-kim/boilerplate-starter")
            vcsUrl.set("https://github.com/myeongju-kim/boilerplate-starter.git")
            tags.set(listOf("codegen", "db", "generator", "spring"))
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
