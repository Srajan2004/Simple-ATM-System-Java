# ATM Management System (Java Swing & MySQL)

![Preview](https://github.com/user-attachments/assets/be3da1e1-0a01-46eb-a974-a6a04b40f039)




## Project Overview

This is a desktop-based ATM (Automated Teller Machine) Management System developed using Java Swing for the graphical user interface (GUI) and MySQL as the backend database. The system simulates basic ATM functionalities, allowing users to create accounts, log in, deposit funds, withdraw cash, change their PIN, view mini-statements, and check their account balance.

The project is designed to be a clear example of a multi-module Java application interacting with a relational database, demonstrating secure coding practices like using `PreparedStatement` to prevent SQL injection and proper resource management with `try-with-resources`.

## Features

* **User Registration (Sign Up):**
    * Multi-step registration process (Personal Details, Additional Details, Account Details).
    * Generates unique application form numbers, card numbers, and PINs.
* **User Login:**
    * Secure login using generated card number and PIN.
* **Transaction Operations:**
    * **Deposit:** Add funds to the account.
    * **Withdrawal:** Withdraw funds from the account (includes Fast Cash options).
    * **Balance Enquiry:** Check the current account balance.
    * **Mini Statement:** View a summary of recent transactions.
    * **PIN Change:** Allows users to change their account PIN.
* **Secure Database Interaction:**
    * Uses `PreparedStatement` to prevent SQL injection vulnerabilities.
    * Proper closing of database resources (`Connection`, `Statement`, `ResultSet`) using `try-with-resources`.
* **Error Handling:**
    * Includes basic error handling for user inputs and database operations, providing informative messages.
* **User-Friendly Interface:**
    * Interactive GUI built with Java Swing.

## Technologies Used

* **Language:** Java
* **GUI Framework:** Java Swing
* **Database:** MySQL
* **JDBC Driver:** MySQL Connector/J
* **Calendar Library:** `com.toedter.calendar.JDateChooser` (for date input in SignupOne)

## Prerequisites

Before running this project, ensure you have the following installed:

1.  **Java Development Kit (JDK):** Version 8 or higher.
2.  **MySQL Server:** A running MySQL database instance.
3.  **MySQL Connector/J JDBC Driver:** Download the JAR file (e.g., `mysql-connector-java-8.0.28.jar` or similar) from the official MySQL website. This JAR needs to be added to your project's classpath.
4.  **JCalendar Library:** Download the `jcalendar-1.4.jar` (or newer) for the `JDateChooser` component. This JAR also needs to be added to your project's classpath.

## Database Setup

1.  **Create Database:**
    Open your MySQL client (e.g., MySQL Workbench, command line) and execute the following SQL command to create the database:
    ```sql
    CREATE DATABASE bankmanagementsystem;
    USE bankmanagementsystem;
    ```

2.  **Create Tables:**
    Execute the following SQL commands to create the necessary tables (`signup`, `signuptwo`, `signupthree`, `login`, `bank`):

    ```sql
    -- Table for SignupOne details
    CREATE TABLE signup (
        formno VARCHAR(20) PRIMARY KEY,
        name VARCHAR(100),
        fname VARCHAR(100),
        dob VARCHAR(50),
        gender VARCHAR(10),
        email VARCHAR(100),
        marital VARCHAR(20),
        address VARCHAR(200),
        city VARCHAR(100),
        state VARCHAR(100),
        pincode VARCHAR(10)
    );

    -- Table for Signuptwo details
    CREATE TABLE signuptwo (
        formno VARCHAR(20) PRIMARY KEY,
        religion VARCHAR(50),
        category VARCHAR(50),
        income VARCHAR(50),
        education VARCHAR(50),
        occupation VARCHAR(50),
        PAN VARCHAR(20),
        Adhar VARCHAR(20),
        seniorCitezen VARCHAR(5),
        Existing_account VARCHAR(5)
    );

    -- Table for Signupthree details (Account details)
    CREATE TABLE signupthree (
        formno VARCHAR(20) PRIMARY KEY,
        account_type VARCHAR(50),
        card_number VARCHAR(20) UNIQUE,
        pin_number VARCHAR(10),
        services_required TEXT
    );

    -- Table for Login credentials
    CREATE TABLE login (
        formno VARCHAR(20),
        card_number VARCHAR(20) UNIQUE,
        pin_number VARCHAR(10),
        PRIMARY KEY (card_number),
        FOREIGN KEY (formno) REFERENCES signup(formno)
    );

    -- Table for Bank transactions
    CREATE TABLE bank (
        Pin VARCHAR(10),
        Date VARCHAR(50),
        Type VARCHAR(20),
        Amount DOUBLE,
        CardNumber VARCHAR(20)
    );
    ```

3.  **Update Database Credentials (if necessary):**
    Open `Conn.java` and update the `DriverManager.getConnection()` line with your MySQL username and password if they are different from `root` and `srajan2004`.
    ```java
    c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "your_mysql_username", "your_mysql_password");
    ```
    **Note:** For production environments, it is highly recommended to externalize these credentials (e.g., using environment variables or a configuration file) instead of hardcoding them.

## Installation and Running

1.  **Clone the Repository (or download the files):**
    If this project is in a Git repository, clone it:
    ```bash
    git clone <repository-url>
    cd <project-directory>
    ```
    Otherwise, ensure all `.java` files are in a single directory.

2.  **Add JARs to Classpath:**
    Place the downloaded `mysql-connector-java-*.jar` and `jcalendar-*.jar` files into a `lib` folder within your project directory, or add them directly to your project's build path in your IDE (e.g., IntelliJ IDEA, Eclipse).

3.  **Compile the Java Files:**
    Navigate to the project's root directory in your terminal and compile all Java files:
    ```bash
    javac -cp ".;lib/*" *.java  # For Windows
    # or
    javac -cp ".:lib/*" *.java  # For Linux/macOS
    ```
    (Replace `lib/*` with the actual path to your JARs if they are not in a `lib` folder.)

4.  **Run the Application:**
    After successful compilation, run the `Login.java` file (which contains the `main` method to start the application):
    ```bash
    java -cp ".;lib/*" Login  # For Windows
    # or
    java -cp ".:lib/*" Login  # For Linux/macOS
    ```

## Usage

1.  **Start Application:** Run the `Login.java` file. The login screen will appear.
2.  **New User:** Click "SIGN UP" to register a new account. Follow the multi-step form to provide details and create your account. Note down the generated Card Number and PIN.
3.  **Existing User:** Enter your Card Number and PIN on the login screen and click "SIGN IN".
4.  **Transactions:** Once logged in, you can perform various ATM operations:
    * **Deposit:** Enter the amount to deposit.
    * **Withdrawal / Fast Cash:** Select a pre-defined amount or enter a custom amount to withdraw.
    * **PIN Change:** Update your account PIN.
    * **Mini Statement:** View your last few transactions.
    * **Balance Enquiry:** Check your current balance.
5.  **Logout/Exit:** Use the "Exit" or "Back" buttons to navigate or close the application.

## Known Issues / Improvements

* **Security for Credentials:** Database credentials are currently hardcoded in `Conn.java`. For production, these should be loaded from secure configuration files or environment variables.
* **Error Handling Detail:** While basic error handling is present, a more robust logging mechanism (e.g., Log4j) would be beneficial for debugging.
* **UI Enhancements:** The UI is functional but could be enhanced with more modern Swing features, animations, or a more professional design.
* **Transaction Atomicity:** While `PreparedStatement` helps with security, ensure the database operations for transactions (e.g., withdrawal checking balance and then deducting) are truly atomic if not already handled by the database's transaction management.
* **Card Number/PIN Generation:** Currently, these are simple random number generations. For a real system, they would involve more complex algorithms, uniqueness checks, and secure storage (hashing PINs).
* **User Management:** No administrative user interface to manage user accounts or view all transactions.
* **Concurrency:** The current design might not be robust for multiple concurrent users; for a multi-user environment, connection pooling and more advanced concurrency handling would be needed.
