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

Test-Driven Development is best learned through experience. Thus, work through a sample application: Date Validation

Assume users can enter dates of the following format:

- MMDDYYYY
- MM/DD/YYYY
- MM-DD-YYYY
- MM.DD.YYYY

Using Test-Driven Development, write a program which takes in one of these as an input, and returns a boolean indicating if the date is valid.

Click on the assignment link on URCourses to create your assignment repository.  Now setup your project in VSCode named DateValidator.  You can use Maven if you like but not required.

### Red... Green... Refactor... Round 1

We will begin the iterative process of test driven development

### First your tests must fail

To succeed, you must first fail. Why do we start with the red state? There are a few reasons, one is that it ensures that our tests are being run. Second, when doing test driven development, the procedure is always to fail first, solve our problems as simply as possible, and then refactor to better code. In other words, test must drive development, and if the test is already passing, how are we driving development?

The current state is such that our code always fails - because our validator always returns false.

---

```java
import org.junit.jupiter.api.Test; //JUnit5
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidatorTest {
    
    @Test
    public void Validate_Today_True(){
        DateValidator dateValidator = new DateValidator();
        boolean legalDate = dateValidator.validate("06202025");
        assertTrue(legalDate);

    }
}
```

Reminder: We are using the [Osherove test naming method](https://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html):  [UnitOfWork_StateUnderTest_ExpectedBehavior]

Also note, in considering how our validation function should run from a test, we actually decided what the datatype for the argument should be: String. This is because we needed a value that started with a '0', String made the most sense. This is an important aspect of test driven development: don't prejudge your design. In order for tests to drive development, the tests are instrumental in defining design.

---

Run the tests again. They should still fail. Red state.

What is the minimum amount of code we could write to make this succeed? We could simply check if today's date was entered and if so return true...

```java
public class DateValidator {
    public boolean validate(String date){
        if (date == "06202025"){
            return true;
        } else {
            return false;
        }
    }
}
```
... I kind of feel dirty writing this code. This is common in TDD. But technically it works with the minimum amount of code, and this is our goal.

Run our tests. Pass. Green state.

We don't have anything to refactor yet, so let's try it again...

---

## Red... Green... Refactor... Round 2

Let's add another date, this time tomorrow. We start with a test...

```java
import org.junit.jupiter.api.Test; //JUnit5
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateValidatorTest {
    
    @Test
    public void Validate_Today_True(){
        DateValidator dateValidator = new DateValidator();
        boolean legalDate = dateValidator.validate("06202025");
        assertTrue(legalDate);
    }

    @Test
    public void Validate_Tomorrow_True(){
        DateValidator dateValidator = new DateValidator();
        boolean legalDate = dateValidator.validate("06212025");
        assertTrue(legalDate);
    }
}
```

Run our test and now... 1 passes and 1 fails. Great. Red state. This is what we expect.

Okay, let's make it pass with as little work as possible... Maybe just another branch in the if/else branch?


```java
public class DateValidator {
    public boolean validate(String date){
        if (date == "06202025"){
            return true;
        } else if (date == "06212025") {
            return true;
        } else {
            return false;
        }
    }
}
```

Run our tests again. Now they pass. Green State. Great, these two units are both passing. Now what? We should refactor.

How can we simplify these two strings? Well, if we totally turn our brains off, the common part is that they are both length 8. We could simply validate all length 8 Strings:

```java
public class DateValidator {
    public boolean validate(String date){
        if (date.length() == 8){
            return true;
        } else {
            return false;
        }
    }
}
```

---

## Red... Green... Refactor... Round 3

So... if all eight digit Strings succeed, how can we make our code fail? We need to add a test case which should not be true - an illegal date.

Let's try a day which doesn't exist, Jan 32

```java
import static org.junit.jupiter.api.Assertions.assertFalse;

    ...

    @Test
    public void Validate_Jan32_False(){
        DateValidator dateValidator = new DateValidator();
        boolean illegalDate = dateValidator.validate("01322025");
        assertFalse(illegalDate);
    }
```

Compile and run tests. Do they pass? 

Nope, we are back to a Red state... and it no longer seems like there is a trivial solution.

This is a good time for an aside: On writing good tests:

---

### Aside 1: On writing Good Tests

A unit test should test a unit for a single scenario, thus we typically want just one assert per unit test. There are some exceptions, like testing all the fields of a constructor, but generally this is the case.

Principles to follow:
- A test should test one item of functionality only
- Only have one assert in a test method, unless you need to evaluate multiple values to learn the outcome
- Write tests to check units of business logic, not individual methods. Not all methods need a test. Some methods need multiple tests.
    - no 1-to-1 relationship with methods to tests
        - if you have a 1-to-1 relationship, this is a code smell, you may be missing out on testing business logic
- Tests need to be repeatable - adding code for a new test should not cause a previous test to fail!
- Tests should also work at all times 
    - For example, we used today's date as a test, which is fine, today's date will always be valid. 
    - What if we were testing hours that a business is open?
        - If we used "now", the test will succeed during business hours, but fail if you run it at night.

- Tests must be thorough, that is, have good test coverage. We'll dig into this more in a future lab. However, some questions you can start asking:

4 Questions to help us figure this out:
1. What should our success logic be?
2. What is the opposite of our logic?
3. Are there any edge cases?
4. Are there any error conditions?

Performance typically isn't something that is addressed in TDD 
    - However, if it is important to the project, you could include it as a consideration

---

Back to business, how do we actually solve our problem?

From here we have a few options: We could simply break up the String into three parts, day, month and year, with legal ranges for each...

```java
public class DateValidator {
    public boolean validate(String date){
        if (date.length() == 8){
            String mm = date.substring(0,2);
            String dd = date.substring(2,4);
            String yyyy = date.substring(4,8);

            int month = Integer.parseInt(mm);
            int day = Integer.parseInt(dd);
            int year = Integer.parseInt(yyyy);

            if (day >= 32 || month >= 13) {
                return false;
            }

        } else {
            return false;
        }
        return true;
    }
}
```

on't pre-judge design. This is enough to make this test pass. 

If we are already anticipating how this will fail, that's okay, but don't skip to writing more code than necessary to pass this test. If we want more functionality, we must first add another appropriate test. 

Compile, run, green.

There isn't really a way to merge these success or failure conditions, so let's skip refactor for now, and add another test case...

## Red... Green... Refactor... Round 4

```java
    @Test
    public void Validate_Feb31_False(){
        DateValidator dateValidator = new DateValidator();
        boolean illegalDate = dateValidator.validate("02312025");
        assertFalse(illegalDate);
    }
```

Run our tests... the new test fails.

As we know, this date is illegal. If we think about it, how many dates like this are illegal, which are not already accounted for by our larger umbrella logic.. How many days in each month

Month:|J | F | M | A | M | J | J | A | S | O | N | D
--:|---|---|---|---|---|---|---|---|---|---|---|---
Days:|31 | 28-29 | 31 | 30 | 31 | 30 | 31 | 31 | 30 | 31 | 30 | 31

Based upon this, how many days possible days would be validated incorrectly?

Month:|J | F | M | A | M | J | J | A | S | O | N | D
--:|---|---|---|---|---|---|---|---|---|---|---|---
Exceptions:|0 | 2-3 | 0 | 1 | 0 | 1 | 0 | 0 | 1 | 0 | 1 | 0

So it looks like we would only need to add in seven exception cases, and our validation checker will work:

```java
public class DateValidator {
    public boolean validate(String date){
        if (date.length() == 8){
            String mm = date.substring(0,2);
            String dd = date.substring(2,4);
            String yyyy = date.substring(4,8);

            int month = Integer.parseInt(mm);
            int day = Integer.parseInt(dd);
            int year = Integer.parseInt(yyyy);

            if ( day >= 32 || month >= 13 ||
                (month == 2  && day == 31) ||
                (month == 4  && day == 31) ||
                (month == 6  && day == 31) ||
                (month == 9  && day == 31) ||
                (month == 11 && day == 31) ||
                (month == 2  && day == 30) ||
                (month == 2  && day == 29) ) {
                    return false;
                }
        } else {
            return false;
        }
        return true;
    }
}
```

So, we are pretty close now... And definitely have some repeated code, suggesting that a refactor is definitely a good idea. 

But first, let's add one more test case accounting for leap years...

## Red... Green... Refactor... Round 5

```java
    @Test
    public void Validate_LeapDay_True(){
        DateValidator dateValidator = new DateValidator();
        boolean legalDate = dateValidator.validate("02292020");
        assertTrue(legalDate);
    }
```

Run your code and ensure that it fails... Red State.

Now let's modify our code to account for this...

```java
public class DateValidator {
    public boolean validate(String date){
        if (date.length() == 8){
            String mm = date.substring(0,2);
            String dd = date.substring(2,4);
            String yyyy = date.substring(4,8);

            int month = Integer.parseInt(mm);
            int day = Integer.parseInt(dd);
            int year = Integer.parseInt(yyyy);

            if ( day >= 32 || month >= 13 ||
                (month == 2  && day == 31) ||
                (month == 4  && day == 31) ||
                (month == 6  && day == 31) ||
                (month == 9  && day == 31) ||
                (month == 11 && day == 31) ||
                (month == 2  && day == 30) ||
                (month == 2  && day == 29 && (year % 4 != 0)) ) {
                    return false;
                }
        } else {
            return false;
        }
        return true;
    }
}
```

Compile and run your tests. Everything working! Good. We have a decent date validator with only 5 test cases!

We are definitely ready for a refactor. Let's extract out some of these codes into helper functions...

The rats nest of logic is clearly an eyesore, let's sweep it under the rug with into a helper function...

```java
public class DateValidator {

    private boolean checkIllegalDays(int month, int day, int year){
        return 
        // obviously illegal
        day >= 32 || month >= 13 ||
        // not obviously illegal
        ( month == 2  && day == 31 ) ||
        ( month == 4  && day == 31 ) ||
        ( month == 6  && day == 31 ) ||
        ( month == 9  && day == 31 ) ||
        ( month == 11 && day == 31 ) ||
        ( month == 2  && day == 30 ) ||
        //illegal...                  except on leap years
        ( month == 2  && day == 29 && (year % 4 != 0));
    }

    public boolean validate(String date){
        if (date.length() == 8){
            String mm = date.substring(0,2);
            String dd = date.substring(2,4);
            String yyyy = date.substring(4,8);

            int month = Integer.parseInt(mm);
            int day = Integer.parseInt(dd);
            int year = Integer.parseInt(yyyy);

            if ( checkIllegalDays(month, day, year) ) {
                    return false;
                }
        } else {
            return false;
        }
        return true;
    }
}
```

Still awful, but at least our main function is slightly less awful. Plus we explained our logic for the rest. 

I will leave further refactoring of this function to you.

This seems like a good time for a second aside: On refactoring:

---

### Aside 2: On Refactoring

Once you have a decently reliable code base with decently reliable test cases, now you are able to start doing refactoring

The idea here is that you can make changes safely assured you didn't break anything as long as your tests are still working correctly.

Some common refactoring operations:

- Rename Variable - Replace a variable name to make your code more descriptive
- Rename Method - Ditto. Can you figure out what a function does and returns just from the name?
- Rename Class - Ditto. Has a class's purpose changed over time?
- Extract Method - Bring logic into a separate method, eg. a helper function
- Extract Constants - Minimize "magic numbers", maximize reusability and modifyability
- Extract Class / Super class / Interface - More of a design refactor.
- Control Flow refactor - are your if statements logical? any double negatives? How deeply does it nest?

- After doing the refactors, rerun your tests to ensure you haven't broken anything.

*We can refactor our tests as well, and may need to from time to time, eg. to rename a method.

---

Run your tests. Did you break anything? Nope. Cool. This is part of the power of refactoring, it allows us to modify our code significantly while maintaining confidence we haven't lost any key functionality. Let's try to tackle that extra case with the separators between the days, months and years.

## Red... Green... Refactor... Round 6

```java
    @Test
    public void Validate_TodaySeparated_True(){
        DateValidator dateValidator = new DateValidator();
        boolean legalDate = dateValidator.validate("02/04/2021");
        assertTrue(legalDate);
    }
```

Run tests. Fail. Cool...

So how do we fix this? Well, we can notice two things, first that dates of these formats always have ten digits, and two that the same data is encoded, just with different offsets. We want to reuse as much existing code as possible, so what's our smallest change to make it valid?

```java
public class DateValidator {

    private boolean checkIllegalDays(int month, int day, int year){
        return 
        // obviously illegal
        day >= 32 || month >= 13 ||
        // not obviously illegal
        ( month == 2  && day == 31 ) ||
        ( month == 4  && day == 31 ) ||
        ( month == 6  && day == 31 ) ||
        ( month == 9  && day == 31 ) ||
        ( month == 11 && day == 31 ) ||
        ( month == 2  && day == 30 ) ||
        //illegal...                   except on leap years
        ( month == 2  && day == 29 && (year % 4 != 0));
    }

    public boolean validate(String date){

        String mm, dd, yyyy;
        if (date.length() == 8){

            mm = date.substring(0,2);
            dd = date.substring(2,4);
            yyyy = date.substring(4,8);

        } else if (date.length() == 10) {

            mm = date.substring(0,2);
            dd = date.substring(3,5);
            yyyy = date.substring(6,10);

        } else {
            return false;
        }

        int month = Integer.parseInt(mm);
        int day   = Integer.parseInt(dd);
        int year  = Integer.parseInt(yyyy);

        if ( checkIllegalDays(month, day, year) ) {
                return false;
        } else {
            return true;
        }
    }
}
```

We can do a small refactor...

```java
public class DateValidator {

    private boolean checkIllegalDays(int month, int day, int year){
        return 
        // obviously illegal
        day >= 32 || month >= 13 ||
        // not obviously illegal
        ( month == 2  && day == 31 ) ||
        ( month == 4  && day == 31 ) ||
        ( month == 6  && day == 31 ) ||
        ( month == 9  && day == 31 ) ||
        ( month == 11 && day == 31 ) ||
        ( month == 2  && day == 30 ) ||
        //illegal...                   except on leap years
        ( month == 2  && day == 29 && (year % 4 != 0));
    }

    public boolean validate(String date){

        String mm, dd, yyyy;
        if (date.length() == 8){

            mm = date.substring(0,2);
            dd = date.substring(2,4);
            yyyy = date.substring(4,8);

        } else if (date.length() == 10) {

            mm = date.substring(0,2);
            dd = date.substring(3,5);
            yyyy = date.substring(6,10);

        } else {
            return false;
        }

        int month = Integer.parseInt(mm);
        int day   = Integer.parseInt(dd);
        int year  = Integer.parseInt(yyyy);

        return checkIllegalDays(month, day, year);
    }
}
```

This implementation is somewhat forgiving, in that the separator character can be anything, and might not even match. Let's add a test to add just a bit more reliability in that they have to match, even if we don't care what they are. Let's make a test to unsure that this isn't the case.

## Red... Green... Refactor... Round 7

```java
    @Test
    public void Validate_TodaySeparatorsMismatch_False(){
        DateValidator dateValidator = new DateValidator();
        boolean illegalDate = dateValidator.validate("06/20-2025");
        assertFalse(illegalDate);
    }
```
Compile tests and run. Failure. Good.

Now to fix it...

```java
public class DateValidator {

    private boolean isDateIllegal(int month, int day, int year){
        return 
        // obviously illegal
        day >= 32 || month >= 13 ||
        // not obviously illegal
        ( month == 2  && day == 31 ) ||
        ( month == 4  && day == 31 ) ||
        ( month == 6  && day == 31 ) ||
        ( month == 9  && day == 31 ) ||
        ( month == 11 && day == 31 ) ||
        ( month == 2  && day == 30 ) ||
        //illegal...           except on leap years
        ( month == 2  && day == 29 && (year % 4 != 0));
    }

    public boolean validate(String date){

        String mm, dd, yyyy;
        if (date.length() == 8){

            mm = date.substring(0,2);
            dd = date.substring(2,4);
            yyyy = date.substring(4,8);

        } else if (date.length() == 10) {

            String letter2 =  date.substring(2,3);
            String letter5 =  date.substring(5,6);

            if (!letter2.equals(letter5)) {
                return false;
            }

            mm = date.substring(0,2);
            dd = date.substring(3,5);
            yyyy = date.substring(6,10);

        } else {
            return false;
        }

        int month = Integer.parseInt(mm);
        int day   = Integer.parseInt(dd);
        int year  = Integer.parseInt(yyyy);

        return ! isDateIllegal(month, day, year);

    }
}
```

Compile, run tests, should pass. Good! This forgiving format is likely fine, as it allows users to choose their separator of choice, and will still be likely to catch some typos.

## Red... Green... Refactor... Round 8

Finally, what if the user entered in a junk string of 8 or 10 characters? In either of these cases, our system should return false.

Let's make some tests...

```java
    @Test
    public void Validate_NonNumeric8_False(){
        DateValidator dateValidator = new DateValidator();
        boolean illegalDate = dateValidator.validate("abcdefgh");
        assertFalse(illegalDate);
    }
```

More tests!...

```java
    @Test
    public void Validate_NonNumeric10_False(){
        DateValidator dateValidator = new DateValidator();
        boolean illegalDate = dateValidator.validate("ab/cd/efgh");
        assertFalse(illegalDate);
    }
```

You should try to implement this functionality.

We've now done several iterations. You should have a pretty good idea how the process works. Time to try it yourself...

---

## Assignment

Your assignment is to continue and extend this application, using Test Driven Development, but add in another combination of date formats:

```
"Friday, June 20, 2025"
```

or

```
"Fri, June 20, 2025"
```
That is, validate dates which include the weekday and the month as words, in both a long or abbreviated format, or a combination thereof.

The Weekday must match the day it would appear as on a modern western (Gregorian) calendar.

i.e. 

```
"Friday, June 20, 2025"
```

Is a legal date, because this is a valid date, with the correct weekday, however, 

```
"Thursday, June 20, 2025"
```

Is not a legal date, because even though the date is valid, the weekday is wrong.

Of course, illegal dates must be rejected, regardless of the given weekday.

Hint: You may investigate using an internal Java class for doing this, or attempt to adapt the [algorithm listed here](https://cs.uwaterloo.ca/~alopez-o/math-faq/node73.html).

A list of legal words and abbreviations which are allowed are listed here:

Weekdays

|Long Name   |Short Name   |
|---|---|
|Sunday   |Sun   |
|Monday   |Mon   |
|Tuesday   |Tues   |
|Wednesday   |Wed   |
|Thursday   |Thurs   |
|Friday   |Fri   |
|Saturday   |Sat   |


Months

|Long Name   |Common Short Names   |
|---|---|
|January   |Jan   |
|February   |Feb   |
|March   |Mar   |
|April   |Apr   |
|May   |May   |
|June   |Jun   |
|July   |Jul   |
|August   |Aug   |
|September   |Sep   |
|October   |Oct   |
|November   |Nov   |
|December   |Dec   |

Remember - the purpose is to learn Test Driven Development, so ensure that you are writing your tests first, don't write code until you have a corresponding test case, and always cycling through: Red, Green, Refactor.

---

## Submission
Nothing to submit because you are using your GitHub repository.

## References

M. Greencroft, [Practical Test-Driven Development for Java Programmers](https://www.lynda.com/Software-Development-tutorials/Practical-Test-Driven-Development-Java-Programmers/777389-2.html), Lynda from LinkedIn, 2018.

R. Osherove, [Naming standards for unit tests](https://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html), Agile & XP Consulting and Training, 2005.
