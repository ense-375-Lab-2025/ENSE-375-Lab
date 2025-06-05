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


### 🔧 Step 1: Install Required Extensions in VSCode

1. **Open VSCode.**
2. Go to the **Extensions view** .
3. Search and install the following:

Ensure you have JDK version 17 installed.  In the lab machines this is the case.

#### 🔹 [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
This pack includes:
- Language Support for Java™ by Red Hat
- Debugger for Java
- Maven for Java
- Java Test Runner
- Java Dependency Viewer

Once installed, **reload** VSCode if prompted.

---
### 🧱 Step 2: Create a Maven Project (No Terminal)

1. Install Apache Maven and put the bin directory in your path.
2. Open **VSCode**:  
   One the left menu click the Folders icon.

3. Click Create Java Project.

4. Selest the Project Type Maven. 

5. Select:
   - **Archetype**: Choose `maven-archetype-quickstart`
   - **Version**: Use the latest
   - **Group Id**: `com.example`
   - **Artifact Id**: `junit-tutorial`

6. Choose a folder to save the project.
7. Select the default 'version'
8. Confirm your configured properties.

✅ VSCode will automatically prompt to open the newly created project.

---

### 📦 Step 3: Ensure the proper JDK version.

1. Find out what version of java you have by java -version.
2. In the **Explorer** (left sidebar), open `pom.xml`.
3. For the lab machines, ensure the maven.compiler.source matches your installed version of java.  In the lab it is 17.  I have seen that it sometimes is 1.7 in the pom file.

```xml
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
  </properties>
```
3. Save the file.

4. In the **Maven Projects** view (click the **M** icon in the sidebar), right-click your project and select:
   - `Reload Project` or `Update Project`

This ensures Maven downloads all dependencies.

---

### 📦 Step 4: Add JUnit to the Project

1. In the **Explorer** (left sidebar), open `pom.xml`.
2. Add this inside the `<dependencies>` tag:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

> 📝 If there's already a JUnit 4 dependency, remove it to avoid conflicts.

3. Save the file.

4. In the **Maven Projects** view (click the **M** icon in the sidebar), right-click your project and select:
   - `Reload Project` or `Update Project`

This ensures Maven downloads all dependencies.  If you use the command line use `mvn clean install`.  This will update all dependencies.

---

## 🧪 Step 5: Run the default test.

1. In the folder: `src/test/java/com/example/`, open `AppTest.java`.
2. Notice you will get an error due to that this test is trying to use JUnit 4.
3. Update the to JUnit version 5.

Update the import statements:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
```

## ▶️ Step 6: Run the Tests from VSCode (No CLI)

### Option 1: Use the Testing Sidebar

1. Click the **Testing** icon (🧪) in the sidebar.
2. You’ll see your test class and test methods listed.
3. Click the **play ▶️ icon** next to each test or run all tests.

### Option 2: Right-Click in Code

1. Inside any test method, right-click and choose:
   - **Run Test** or **Debug Test**

### Option 3: Command line  
1. If you want to use the command line then: 
   - `mvn test`

   
VSCode will show test results in the **Test Output Panel**.

---
## 📘 What is JDBC?

**JDBC** stands for **Java Database Connectivity** — it’s a **Java API** that allows Java programs to connect to and interact with **relational databases** like:

- SQLite
- MySQL
- PostgreSQL
- Oracle
- SQL Server

---

## 🔧 What JDBC Lets You Do

Using JDBC, you can:
- **Connect** to a database
- **Run SQL queries** (`SELECT`, `INSERT`, `UPDATE`, `DELETE`)
- **Read results** from queries
- Handle **transactions**
- **Close resources** safely (connections, statements, result sets)

---

## 📦 Key JDBC Classes

| Class/Interface          | Description                                |
|--------------------------|--------------------------------------------|
| `DriverManager`          | Establishes connection to the DB           |
| `Connection`             | Represents a connection to a DB            |
| `Statement` / `PreparedStatement` | Executes SQL commands           |
| `ResultSet`              | Holds query results (`SELECT` statements)  |

---

## ✅ Example: JDBC with SQLite

```java
import java.sql.*;

public class Example {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:sample.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id INTEGER, name TEXT)");
            stmt.executeUpdate("INSERT INTO users (id, name) VALUES (1, 'Alice')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 💡 JDBC Drivers

JDBC works with **drivers** — small Java libraries specific to each database system. For SQLite, add this to your pom.xml file:

```xml
<dependency>
  <groupId>org.xerial</groupId>
  <artifactId>sqlite-jdbc</artifactId>
  <version>3.42.0.0</version>
</dependency>
```
---

## 🛠️ After Adding the Dependency

1. Save `pom.xml`
2. In terminal, run:

```bash
mvn clean compile
```

3. To run tests:

```bash
mvn test
```
---

## Assignment
You are to create a **Movie Recommender Application** in Java using Maven as a build environment and JUnit to test the application you have created. The following functionality must exist:
- JDBC to store movies, add and delete them.
- The ability to recommend movies based on genre.
- Import a Movie.csv to fill the database.
  
You are to create **JUnit tests** in order to test the functionality of the design. There is no need to create any UI. Just create the tests. Also, there is no need to create tests for something as simple as setters and getters. Just test your application when there is logic functionality. 


## 📦 Prerequisites
For the lab machines and perhaps your own you need to perform the following:
- [Maven](https://maven.apache.org)  Download the Binary archive.
- Students do not have access to modifying the system PATH or creating system environment variables.  You can modify your variables however.
- **JAVA_HOME** Create a JAVA_HOME variable and point it to: C:\jdk-17.0.1

\
Add to the user PATH
  - **CMD** C:\Windows\System32
  - **Java** %JAVA_HOME%
  - **Maven** Path to Maven bin directory.
---

## Deliverables
Use the link to the Lab 2 Assignment on URCourses.  

