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

# 🧪 What is JUnit?

**JUnit** is a **popular open-source testing framework** for the Java programming language. It is widely used for writing and running **automated unit tests**, which help ensure that individual parts of a program (called *units*) work as intended.

---

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
Let's create an example of a Calculator and run some tests against it.

## 📦 Prerequisites

Make sure you have the following installed:

- [Java JDK 11+](https://adoptopenjdk.net/)  This should be installed on the lab machines
- [VSCode](https://code.visualstudio.com/)
- VSCode Extensions:
  - **Java Extension Pack** (from Microsoft)
  - **Test Runner for Java** (You need this to run tests and debug inside VSCode)
- [JUnit 5 Standalone JAR](https://search.maven.org/artifact/org.junit.platform/junit-platform-console-standalone/1.7.0/all)  This will be downloaded from the site automatically when using VSCode.

---

## 📁 Project Structure

Here's a simple structure for your project:

```
your-project/
├── bin/                        # Compiled classes go here
├── lib/                        # JUnit JAR goes here
├── src/
│   └── Calculator.java               # Your main application (optional)
├── test/
│   └── CalculatorTest.java          # Your JUnit test file(s)
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


