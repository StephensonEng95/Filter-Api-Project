Filter API Project
👨‍💻 About Me

I'm an Electronics Engineering graduate who discovered a passion for software development after completing my degree. 
The software bootcamp further ignited my interest in programming, and I'm currently working as a freelancer while continuing to expand my software engineering skills.

🚀 Getting Started

bash
git clone https://github.com/StephensonEng95/Filter-Api-Project.git

do mvn clean compile first, then do

mvn test

🛠️ Tech Stack

https://img.shields.io/badge/Java-JDK%252017-blue
https://img.shields.io/badge/Build-Maven-blue
https://img.shields.io/badge/Testing-JUnit%25205-blue
https://img.shields.io/badge/Methodology-TDD-blue

Java JDK 17 - Leveraging modern Java features including Records

Maven - Dependency management and build automation

JUnit 5 - Comprehensive testing framework

TDD - Test-Driven Development methodology

🚀 Project Overview
A flexible and extensible Java Filter API that implements Clean Architecture principles along with Strategy Design Pattern and Composite Design Pattern to provide a clean,
maintainable solution for resource filtering operations.

🏗️ Architecture & Design Patterns
Core Interface
java
public interface Filter {
    boolean isMatch(Map<String, String> resource);
    String asString();
}
Filter Types
Leaf Filters (Java Records): Equals, GreaterThan, LessThan, PropertyExists, Regex

Boolean Filters: TrueFilter, FalseFilter

Composite Filters: AND, OR, NOT (Composite Pattern)

📚 Design Principles
Clean Architecture & SOLID
Framework Independence - Core logic decoupled from infrastructure

Open/Closed Principle - Extensible without modifying existing code

Strategy Pattern - Interchangeable filtering algorithms

Composite Pattern - Recursive filter composition

🧪 Testing & Methodology
Test-Driven Development
✅ Red-Green-Refactor cycle for all features

✅ Tests first approach

✅ Comprehensive test coverage

✅ All tests passing

📁 Project Structure
text
src/
├── main/java/com/stephenson/filter/filter_library/
│   ├── / core/ #Filter.java
│   ├── / propertyfilters/    # EqualFilter, GreaterThan, LessThan, etc.
│   ├── booleanfilters/    # True, False filters
│   └── logicalfilters/  # AND, OR, NOT filters
└── test/java/com/stephenson/filter/filter_library/filtertest/ #Filtertest.java

💡 Learning Outcomes
Clean Architecture implementation

Design Patterns in practice

TDD methodology

Professional code organization

Demonstrating modern Java development with professional architecture patterns.
