## ENSE 375 - Software Testing and Validation - Laboratory

# Lab 3: Test-Driven Development

### University of Regina
### Faculty of Engineering and Applied Science - Software Systems Engineering

### Lab Instructor: [Trevor Douglas](mailto:trevor.douglas@uregina.ca)
### Original Content Creator: Adam Tilson

---

## Test Driven Development (TDD)
**TDD (Test-Driven Development)** is a software development approach where tests are written *before* the actual code. It emphasizes short, repetitive development cycles and is a core practice in agile and extreme programming methodologies.

### 🧪 How TDD Works — The Red-Green-Refactor Cycle:
1. <span style="color:red">Red</span> – Write a test that defines a function or improvement but fails because the feature isn’t implemented yet.
2. <span style="color:green">Green</span> – Write the minimum amount of code necessary to make the test pass.
3. <span style="color:blue">Refactor</span> – Clean up the new code, ensuring it adheres to good design principles, without changing its behavior.

### 🔁 Example Workflow:
```java
// Step 1: Write a failing test
@Test
void testAddTwoNumbers() {
    assertEquals(5, Calculator.add(2, 3));
}

// Step 2: Implement just enough code to pass the test
public class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }
}

// Step 3: Refactor (if needed)
```

### ✅ Benefits of TDD:
- **Better design**: Encourages modular, loosely coupled code.
- **Immediate feedback**: Bugs are found early.
- **Improved test coverage**: Tests are always written, reducing the risk of regressions.
- **Confidence in changes**: Refactoring and adding features become safer.

### ⚠️ Challenges:
- **Initial time investment**: Writing tests first can feel slower initially.
- **Learning curve**: Requires practice to write effective tests.
- **Not ideal for all problems**: For highly experimental or UI-heavy features, it may be less effective.

It may be tempting to skip the <span style="color:red">Red</span> state. Don't do it! We need to see our tests fail to know they are actually running! We also should not be writing any code until we have an appropriate test.

---

In summary, **TDD helps developers write reliable, maintainable code by making testing a fundamental part of the development process from the start.**

## Project Setup

Test-Driven Development is best learned through experience. Thus, we will work together on a sample application: Date Validation

Assume users can enter dates of the following format:

- MMDDYYYY
- MM/DD/YYYY
- MM-DD-YYYY
- MM.DD.YYYY

Using Test-Driven Development, write a program which takes in one of these as an input, and returns a boolean indicating if the date is valid.

Let's set up our project in VSCode named DateValidator.  You can use Maven if you like but not required.

## Red... Green... Refactor... Round 1

We will begin the iterative process of test driven development

### First your tests must fail

To succeed, you must first fail. Why do we start with the red state? There are a few reasons, one is that it ensures that our tests are being run. Second, when doing test driven development, the procedure is always to fail first, solve our problems as simply as possible, and then refactor to better code. In other words, test must drive development, and if the test is already passing, how are we driving development?

The current state is such that our code always fails - because our validator always returns false.

---
