# Boilerplate Starter Plugin

A Gradle plugin that automatically generates boilerplate code for Java Spring projects.  
This plugin helps developers focus on business logic by eliminating repetitive code setup such as controllers, services, repositories, DTOs, and MyBatis mappers.

---

## Features
- Generate **Controller**, **Service**, **Repository/Mapper** classes with common annotations.
- Create **DTOs** and **MyBatis mapper XML** files from database schema.
- Reduce repetitive setup and improve development speed.
- Follow common Spring Boot project conventions.

---

## Installation

Add the plugin to your Gradle build script.

### Kotlin DSL (`build.gradle.kts`)
```kotlin
plugins {
    id("io.github.myeongju-kim.codegen") version "0.1.0"
}
```

### Groovy DSL (`build.gradle`)
```groovy
plugins {
    id 'io.github.myeongju-kim.codegen' version '0.1.0'
}
```

---

## Usage

Run the provided Gradle task to generate boilerplate code:

```bash
./gradlew boilerplateGenerate
```

Generated files will be placed under the corresponding package structure:

```
src/main/java/com/example/project/
 ├─ controller/
 ├─ service/
 ├─ repository/
 └─ dto/
```

For MyBatis:
```
src/main/resources/mapper/
```

---

## Example

If your database schema defines a table `users`, the plugin will generate:

- `UserController.java`
- `UserService.java`
- `UserRepository.java`
- `UserDto.java`
- `UserMapper.xml`

---

## Requirements
- Java 17+
- Gradle 7.6+
- Spring Boot project structure
- (Optional) MyBatis for mapper generation

---

## Roadmap
- Add support for custom templates
- Improve schema-to-DTO mapping
- Support additional frameworks (e.g., JPA)

---

## License
This project is licensed under the MIT License.
