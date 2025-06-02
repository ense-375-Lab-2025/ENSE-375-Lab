## ENSE 375 - Software Testing and Validation - Laboratory

# Lab 1: JUnit

### University of Regina
### Faculty of Engineering and Applied Science - Software Systems Engineering

### Lab Instructor: [Trevor Douglas](mailto:trevor.douglas@uregina.ca)

## Introduction

## ✅ What is a Unit Test?

Unit - A single, identifiable, piece of logic. i.e., a method, set of methods or a class.

Unit Test - A test to validate a unit is working correctly.

- A unit test corresponds to a single scenario for a single piece of logic.

- For each unit there are typically many unit tests.

You may contrast this approach with testing a system end-to-end, e.g. including external modules and components like databases. This is called integration testing, and is not what we will be focusing on in this lab.

A unit test should be fast and repeatable. A complete set of unit tests should be easy to run, complete and up-to-date. Together these will give you courage to refactor bad code into good code without fear of breaking functionality.

## 🧪 What is JUnit?

**JUnit** is a **popular open-source testing framework** for the Java programming language. It is widely used for writing and running **automated unit tests**, which help ensure that individual parts of a program (called *units*) work as intended.

## ✅ What is JUnit Good For?

### 🔍 1. **Verifying Code Correctness**
JUnit allows developers to write tests that automatically check if methods return expected results. This reduces bugs and improves confidence in the code.

### 🔁 2. **Regression Testing**
As code evolves, tests ensure that existing functionality still works and that new changes don’t break old features.

### 🧹 3. **Cleaner, More Reliable Code**
Writing tests encourages better design and forces developers to think about edge cases and error handling.

### 🔧 4. **Fast Feedback for Developers**
You can run your JUnit tests quickly after each code change to make sure everything still works — perfect for **test-driven development (TDD)**.

### 🤝 5. **Supports Team Collaboration**
When working in teams, automated tests make sure that code changes by one person don’t accidentally break someone else’s work.

---

## 📦 JUnit Features

- Easy test creation with `@Test` annotations
- Built-in assertions (e.g., `assertEquals`, `assertThrows`)
- Lifecycle hooks like `@BeforeEach`, `@AfterEach`
- Parameterized tests for testing multiple inputs
- Integrates with IDEs (e.g., IntelliJ, Eclipse) and build tools (e.g., Maven, Gradle)

---

## ✅ JUnit Procedure

## 📦 Prerequisites
Make sure you have the following installed:

- [Java JDK 11+](https://adoptopenjdk.net/)  This should be installed on the lab machines
- [VSCode](https://code.visualstudio.com/)
- VSCode Extensions:
  - **Java Extension Pack** (from Microsoft)
  - **Test Runner for Java** (You need this to run tests and debug inside VSCode)
- [JUnit 5 Standalone JAR](https://search.maven.org/artifact/org.junit.platform/junit-platform-console-standalone/1.7.0/all)  This will be downloaded from the site automatically when using VSCode.

---

## Calculator Assignment example.
The first lab assignment you have is to make sure that you can click the link on URCourses and access the Calculator Example through our GitHub classroom.  Clone the template repository to use and make sure you can run through the sample.

## 📁 Project Structure
Here's a simple structure for your project:

```
your-project/
├── lib/                                # JUnit JAR goes here
├── src/
│   └── Calculator.java                 # Your main application (optional)
├── test/
│   └── CalculatorTest.java             # Your JUnit test file(s)
```

### ✅  Step 1: Create the Calculator Class src/Calculator.java
```java
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return (double) a / b;
    }
}
```
### ✅ Step 2: Create the Calculator Test Case test/CalculatorTest.java
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    void testAddition() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void testSubtraction() {
        assertEquals(1, calculator.subtract(4, 3));
    }

    @Test
    void testMultiplication() {
        assertEquals(12, calculator.multiply(3, 4));
    }

    @Test
    void testDivision() {
        assertEquals(2.0, calculator.divide(10, 5));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        });
    }
}
```
### ✅ Step 3: Run Tests in VS Code
#### 1. Install Required Extensions

Make sure these extensions are installed from the VS Code Marketplace:
- **Java Extension Pack**
- **Test Runner for Java**

---

#### 2. Open the Project Folder

Open the root folder of your project (e.g., `CalculatorApp/`) in VS Code.

> ⚠️ Don’t open just the `src/` or `test/` make sure VSCode is at the **entire project folder**.

---

#### 3. Fix Classpath & Errors

If you see red errors (especially in the test file), it’s usually because:
- JUnit isn’t available yet  
- VS Code doesn’t know where to look for your source files

To fix this:

1. Open the **Command Palette** (`Ctrl+Shift+P` or `Cmd+Shift+P` on Mac)  
2. Search for: `Java: Configure Classpath`  
3. Add the `/src` and `/test` folders


---

#### 4. Enable Java Tests

1. Click on `CalculatorTest.java`  
2. Look for the beaker icon in the left Activity Bar (Test Explorer)  
3. Click it and choose **Enable Java Tests**  
4. Select **JUnit 5** when prompted

This will:
- Download the JUnit 5 `.jar` files into a `lib/` folder  
- Set them up automatically for the project

---

#### 5. Run Your Tests

- In the test file, click **Run** above a test method or class  
- Or use the **Test Explorer** (from the beaker icon) to run all tests


## Assertions
From here, it is a good idea to take a run through the different Asserts to see what JUnit 5 is capable of. You can find these [here](https://junit.org/junit5/docs/5.0.1/api/org/junit/jupiter/api/Assertions.html). Some of the major families of assert functions:

- assertEquals
- assertNotEquals
- assertTrue
- assertFalse
- assertNull
- assertNotNull
- assertSame
- assertNotSame
- assertThrows  (exceptions)

For each of these, we may add an additional String argument at the end which will print to the console if the test fails.

---
## 📘 What is JavaDocs?

**JavaDocs** is a tool built into the Java Development Kit (JDK) that allows developers to generate **HTML documentation** from specially formatted comments in Java source code.

This documentation is helpful for:
- Explaining how classes, methods, and fields work
- Making your code easier to understand and use (by others or by you later)
- Creating API documentation like what you see for Java libraries

---

## 📝 Writing JavaDoc Comments

JavaDoc comments are written using `/** ... */` syntax **just before** the class, method, or field you want to document.

### 🔹 For a class:

```java
/**
 * This class represents a simple calculator.
 * It can perform basic arithmetic operations.
 */
public class Calculator {
    ...
}
```
In order to create javadocs first create a doc folder at the root of your project.  Then from the command line use the following command:

```bash
javadoc -d doc -sourcepath src -subpackages your.package.name

```
If you do not have any package names you could simply:

```bash
javadoc -d doc ./src/*.java

```
#### Replace:
- doc with the output directory you want for the HTML files.
- src with the path to your source folder.
- your.package.name with your actual package name, or use . for all packages.

---
## Procedure
You are to create a **Coffee Maker Simulation**. This coffee maker can store up to 4 recipes in its memory. A recipe is made up of coffee, milk, and sugar in units of parts. For example: my favourite recipe would be 2 parts coffee, 2 parts milk, 2 parts sugar (my version of the tall double double). The coffee maker has reservoirs to hold the coffee, milk, and sugar. Your program must have the following features:

- Be able to add and delete recipes. You should indicate to the user any problems or success.
- Your coffee maker must hold 4 recipes and have 20 units of coffee, milk, and sugar in its inventory. The inventory should be filled in the constructor.
- Make some coffee. Remember that your inventory should go down depending on the recipe being made.
- Initialization of variables in your classes should be done in your constructors.
- Generate JavaDocs for the source code given to you.
- Use the link to the Lab 1 Assignment on URCourses.  This is a link to the assignemnt in GitHub Classroom.  A GitHub template will be provide to you.
 

## Assignment
You are to create **JUnit tests** in order to test the functionality of the design. There is no need to create any UI. Just create the tests. Also, there is no need to create tests for something as simple as setters and getters. Just test your application when there is logic functionality. 


## Deliverables
Use your repository when creating your test code and make sure you have everything committed by the due date.
