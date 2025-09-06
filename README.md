
```bash
npcdmlhealthcare-automation/
│── pom.xml                     # Maven/Gradle config (dependencies, build plugins)
│── requirements.properties     # Environment configs
│── README.md                   # Project documentation
│── .gitignore                  # Ignore target, screenshots, reports, etc.

├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/npcdmlhealthcare/
│   │           ├── base/
│   │           │   └── DriverFactory.java         # WebDriver setup, parallel execution
│   │           │   └── BaseTest.java              # Common setup/teardown
│   │           │
│   │           ├── pages/                         # Page Object Model classes
│   │           │   ├── LoginPage.java
│   │           │   ├── LogoutPage.java
│   │           │   ├── RegistrationPage.java
│   │           │   ├── ForgotPasswordPage.java
│   │           │   ├── DashboardPage.java
│   │           │   ├── AssessmentsPage.java
│   │           │   ├── AppointmentPage.java
│   │           │   ├── ChatBotPage.java
│   │           │   ├── ChiefComplaintPage.java
│   │           │   ├── ROMPage.java
│   │           │   └── DocumentsPage.java
│   │           │
│   │           ├── utils/                         # Utility classes
│   │           │   ├── ConfigReader.java          # Read properties
│   │           │   ├── ExcelUtils.java            # Read/write Excel
│   │           │   ├── CSVUtils.java              # Handle CSV test data
│   │           │   ├── JsonUtils.java             # Parse JSON configs
│   │           │   ├── TestDataGenerator.java     # Dynamic fake data (faker lib)
│   │           │   ├── APIUtils.java              # API helper methods
│   │           │   ├── WaitUtils.java             # Explicit waits
│   │           │   ├── ScreenshotUtils.java       # Capture screenshots
│   │           │   └── Logger.java                # Centralized logging
│   │           │
│   │           ├── api/                           # For API level tests (future-ready)
│   │           │   ├── AppointmentAPI.java
│   │           │   ├── UserAPI.java
│   │           │   └── AuthAPI.java
│   │           │
│   │           └── reports/                       # Reporting setup
│   │               └── AllureReportManager.java
│   │
│   └── test/
│       └── java/
│           └── com/npcdmlhealthcare/tests/
│               ├── LoginTest.java
│               ├── LogoutTest.java
│               ├── RegistrationTest.java
│               ├── ForgotPasswordTest.java
│               ├── DashboardTest.java
│               ├── AssessmentsTest.java
│               ├── AppointmentTest.java
│               ├── ChatBotTest.java
│               ├── ChiefComplaintTest.java
│               ├── ROMTest.java
│               └── DocumentsTest.java

├── testdata/
│   ├── users.csv                # Test data for registration/login
│   ├── appointments.csv         # Appointment test cases
│   └── config.json              # API tokens, extra settings

├── screenshots/                 # Failed test screenshots
│   └── <timestamp>.png

├── allure-results/              # Raw results
├── allure-report/               # Generated reports
└── logs/                        # Execution logs
 
```