## ENSE 375 - Software Testing and Validation - Laboratory

# Lab 6: Widget Testing with Web / Mobile

### University of Regina
### Faculty of Engineering and Applied Science - Software Systems Engineering

### Lab Instructor: [Trevor Douglas](mailto:trevor.douglas@uregina.ca)

## Introduction

Flutter provides a robust testing framework that allows developers to automate and validate various aspects of their app's behavior, including unit testing, widget testing, and integration testing. Testing in Flutter not only helps catch bugs early in the development cycle but also promotes code quality and facilitates a smoother user experience.

In this lab you will be introduced to Unit and Widget testing.

## Background

## Procedure
For the pre-lab, we are going to recreate the BMI Calculator but this time we are going to include automation testing.

Let's start by setting up the project again:

Paste this as your new main file...


<details>
<summary>expand main.dart</summary>

in `main.dart`

```dart
import 'package:flutter/material.dart';
import 'bmi.dart';

void main() {
   runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
    );
  }
}


```

</details>

Create `lib/bmi.dart` and paste in the following:

<details>
<summary>expand bmi.dart</summary>

in `bmi.dart`

```dart
import 'package:flutter/material.dart';

class BMI {

    double calculateBMI(double height, double weight) {
        double heightInCM = height;
        double weightInKg = weight;
        double heightInM = heightInCM / 100;
        double heightSquared = heightInM * heightInM;
        double result = weightInKg / heightSquared;
        return result;
    }


}
```
</details>

## Unit Tests
A unit test tests a single function, method, or class. The goal of a unit test is to verify the correctness of a unit of logic under a variety of conditions. External dependencies of the unit under test are generally mocked out. Unit tests generally don't read from or write to disk, render to screen, or receive user actions from outside the process running the test. 

All the test files in a Flutter app, except for integration tests, are placed in the test directory.

### Create a new test file
First, you'll test the calculateBMI() method in the BMI class to verify that your bmi calculation is correct.  By convention, the directory structure in the test directory mimics that in the lib directory and the Dart files have the same name with _test appended.


Create a bmi_test.dart file.

<details>

<summary>expand bmi_test.dart</summary>

in `bmi_test.dart`

```dart

import '../lib/bmi.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  group('Testing BMI', () {
    var bmi = BMI();

    test('Testing the BMI calculation', () {
      double bmiCalc = bmi.calculateBMI(175, 90);
      expect('29.39', bmiCalc.toStringAsFixed(2));
    });
  });
}
```
</details>
