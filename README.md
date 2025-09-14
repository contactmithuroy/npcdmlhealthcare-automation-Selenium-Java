npcdmhealthcare-automation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── npcdmhealthcare/
│   │   │           ├── apis/                          # API classes
│   │   │           │   ├── AppointmentAPI.java
│   │   │           │   ├── DocumentsAPI.java
│   │   │           │   └── UserAPI.java
│   │   │           ├── base/                          # Base classes
│   │   │           │   ├── BasePage.java
│   │   │           │   └── DriverFactory.java
│   │   │           ├── pages/                         # Page Object Model classes
│   │   │           │   ├── appointment/             # Appointment management pages
│   │   │           │   │   └── AppointmentEmailPage.java
│   │   │           │   ├── chatbot/                   # Chatbot related pages
│   │   │           │   │   └── ChatBotPage.java
│   │   │           │   ├── clinical/                  # Clinical pages
│   │   │           │   │   ├── ChiefComplaintPage.java
│   │   │           │   │   └── ROMPage.java
│   │   │           │   ├── dashboard/                 # Dashboard pages
│   │   │           │   │   ├── AppointmentPage.java
│   │   │           │   │   ├── AssessmentsPage.java
│   │   │           │   │   └── DashboardPage.java
│   │   │           │   ├── documents/                 # Documents pages
│   │   │           │   │   └── DocumentsPage.java
│   │   │           │   └── login/                     # Login pages
│   │   │           │       ├── ForgotPasswordPage.java
│   │   │           │       ├── LoginPage.java
│   │   │           │       └── LogoutPage.java
│   │   │           └── utils/                         # Utility classes
│   │   │               ├── AllureReportManager.java
│   │   │               ├── ConfigReader.java
│   │   │               ├── CSVUtils.java
│   │   │               ├── JSONUtils.java
│   │   │               ├── LoggerUtil.java
│   │   │               ├── ScreenshotUtil.java
│   │   │               ├── TestListener.java
│   │   │               └── WaitUtil.java
│   │   └── resources/
│   │       ├── config/                                # Configuration files
│   │       │   ├── dev.json
│   │       │   ├── prod.json
│   │       │   ├── qa.json
│   │       │   └── staging.json
│   │       ├── testdata/                              # Test data files
│   │       │   ├── appointmentData.csv
│   │       │   ├── BrowserConfig.csv
│   │       │   ├── documentsData.csv
│   │       │   └── loginData.csv
│   │       ├── test-suites/                           # TestNG XML suites
│   │       │   ├── smoke-test.xml
│   │       │   ├── regression-test.xml
│   │       │   └── full-suite.xml
│   │       └── log4j2.xml                             # Logging configuration
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── npcdmhealthcare/
│       │           └── tests/                         # Test classes
│       │               ├── appointment/             # Appointment management tests
│       │               │   └── AppointmentEmailCheckTest.java
│       │               ├── chatbot/                   # Chatbot tests
│       │               │   └── ChatBotTest.java
│       │               ├── clinical/                  # Clinical tests
│       │               │   ├── ChiefComplaintTest.java
│       │               │   └── ROMTest.java
│       │               ├── dashboard/                 # Dashboard tests
│       │               │   ├── AppointmentTest.java
│       │               │   ├── AssessmentsTest.java
│       │               │   └── DashboardTest.java
│       │               ├── documents/                 # Documents tests
│       │               │   └── DocumentsTest.java
│       │               └── login/                     # Login tests
│       │                   ├── ForgetPasswordTest.java
│       │                   ├── LoginTest.java
│       │                   └── LogoutTest.java
│       └── resources/                                 # Test resources
│           ├── test-config/                           # Test-specific configs
│           └── test-data/                             # Test-specific data
├── target/                                            # Maven build output (auto-generated)
│   ├── classes/                                       # Compiled classes
│   ├── test-classes/                                  # Compiled test classes
│   ├── surefire-reports/                              # Surefire test reports
│   └── allure-results/                                # Allure results (if configured)
├── allure-results/                                    # Allure test results JSON files
├── logs/                                              # Application and test execution logs
├── reports/                                           # Test reports (HTML/PDF)
├── screenshots/                                       # Test failure screenshots
├── pom.xml                                           # Maven configuration
├── README.md                                         # Project documentation
├── requirement.properties                            # Project requirements/dependencies
├── num.test.txt                                      # Test numbering/count file
└── testing.xml                                       # TestNG test suite configuration