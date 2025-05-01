## ENSE 375 - Software Testing and Validation - Laboratory

# Lab 2: JUnit Part 2 and Maven

### University of Regina
### Faculty of Engineering and Applied Science - Software Systems Engineering

### Lab Instructor: [Trevor Douglas](mailto:trevor.douglas@uregina.ca)

## Objective
Introduce students to a powerful build automation tool, while also providing hands-on experience in writing unit tests for an application. 

## What is Maven?

**Maven** is a **build automation tool** primarily used for managing Java-based projects. It simplifies the process of building, packaging, testing, and managing dependencies in software projects.

Maven is used to manage the entire project lifecycle and helps developers automate common tasks like compiling code, running tests, and creating project artifacts (such as JAR files).

### Key Features of Maven

- **Dependency Management**: Automatically downloads and manages project dependencies (libraries or frameworks) from a central repository.
- **Build Automation**: Automates the compilation, testing, packaging, and deployment of Java projects.
- **Project Object Model (POM)**: Uses a single XML file (`pom.xml`) to define project configurations such as dependencies, plugins, goals, and more.
- **Standardization**: Follows standard directory structures and naming conventions, making it easier to understand and collaborate on projects.
- **Reproducible Builds**: Ensures that the build process is consistent across different environments, leading to fewer issues and bugs.

### What is Maven Used For?

1. **Managing Dependencies**  
   Maven simplifies the process of managing external libraries or frameworks your project needs. For example, if your project depends on a specific version of JUnit for testing, Maven will download the right version and handle it automatically.

2. **Building Projects**  
   Maven automates the process of compiling, testing, and packaging your application. You can run a single Maven command (`mvn clean install`) to compile your code, run your unit tests, and create a package (like a `.jar` or `.war` file).

3. **Running Unit Tests**  
   Maven integrates with testing frameworks like **JUnit** and **TestNG** to run unit tests automatically as part of the build process. This makes it easy to verify that your code works before deploying it.

4. **Deploying Artifacts**  
   Once your project is built, Maven can deploy the artifact (e.g., a `.jar` file) to a repository (like **Maven Central** or **Nexus**). This makes it easy to share your project with other developers or use it as a dependency in other projects.

5. **Continuous Integration**  
   Maven is often integrated with **CI/CD pipelines** (Continuous Integration / Continuous Deployment) to automate building and testing whenever changes are made to the codebase. Tools like **Jenkins**, **GitLab CI**, and **Travis CI** work seamlessly with Maven.

6. **Project Documentation**  
   Maven can generate detailed project documentation and reports (like code coverage or test results) as part of the build process.

### How Does Maven Work?

Maven works through the `pom.xml` file, which is the heart of a Maven project. The `pom.xml` file contains:
- **Dependencies**: External libraries that the project needs.
- **Plugins**: Tools that run during the build (such as compilers or test runners).
- **Build Lifecycle**: Phases that define how to compile, test, package, and deploy your project.
- **Goals**: Specific tasks that Maven will execute, such as `mvn compile` (compiles the code) or `mvn test` (runs the unit tests).

### Example `pom.xml`

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <groupId>com.example</groupId>
    <artifactId>my-java-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

In this example, Maven knows to:
- Download the JUnit 5 dependency to run tests.
- Compile the project into a JAR file.

### Summary
- Maven is a tool for automating project builds and managing dependencies.
- It uses a `pom.xml` file to manage configurations, dependencies, plugins, and more.
- Maven helps standardize project structures and build processes, making it easier to maintain and scale projects.
- It is widely used in Java-based applications, and also supports other languages and tools.

For more information, you can visit the [official Maven website](https://maven.apache.org).

## 📘 JUnit 5 Annotations Cheat Sheet

JUnit annotations define **how test methods behave**, when they run, and how they are organized.

---

### ✅ Basic Test Annotations

| Annotation              | Description |
|-------------------------|-------------|
| `@Test`                 | Marks a method as a test method. |
| `@DisplayName("...")`   | Sets a custom name for the test (for better readability in test reports). |

```java
@Test
@DisplayName("Adding 2 and 3 should return 5")
void testAddition() {
    assertEquals(5, new Calculator().add(2, 3));
}
```
---

## 🔁 Lifecycle Annotations

| Annotation              | Runs... |
|-------------------------|---------|
| `@BeforeEach`           | Before **each** test method. Used for setup. |
| `@AfterEach`            | After **each** test method. Used for cleanup. |
| `@BeforeAll`            | Once **before all** tests in the class (must be static). |
| `@AfterAll`             | Once **after all** tests in the class (must be static). |

```java
@BeforeEach
void init() {
    calc = new Calculator();
}

@AfterEach
void tearDown() {
    calc = null;
}
```

---
## Procedure
You are to create a **Movie Recommender Simulation**. 

## Assignment
You are to create **JUnit tests** in order to test the functionality of the design. There is no need to create any UI. Just create the tests. Also, there is no need to create tests for something as simple as setters and getters. Just test your application when there is logic functionality. 

## Deliverables
Use the link to the Lab 2 Assignment on URCourses.  

