# DevOps Operations Tracker

**A Java Spring Boot REST API with automated testing, continuous integration, and application artifact delivery using GitHub Actions.**

**Prepared by:** Sunday Peter Adeoti

**Personal Brand:** TEECLOUDQ

**Internship:** CodeAlpha Virtual DevOps Internship

---



---

## 1. Project Overview

The DevOps Operations Tracker is a Java-based REST API designed to help teams create, manage, track, and filter operational tasks such as deployments, incidents, and maintenance activities.

The project demonstrates practical DevOps principles by combining application development with automated testing, continuous integration, build verification, and downloadable build artifacts.

The application uses Spring Boot to expose REST endpoints, Gradle to manage the build process, and an H2 database to store task records. GitHub Actions automatically builds the project and runs its tests whenever code is pushed to the configured branch or a pull request targets that branch.

The project was developed as part of the CodeAlpha Virtual DevOps Internship.

## 2. Project Objectives

The objectives of this project are to:

- Develop a functional Java REST API.
- Implement CRUD operations for operational tasks.
- Validate incoming requests and handle errors.
- Store application data in an H2 database.
- Write and execute automated tests.
- Use Gradle to automate application builds.
- Configure a CI pipeline using GitHub Actions.
- Verify that the application can be packaged successfully.
- Publish the executable application JAR as a downloadable CI artifact.
- Practise version control and professional project documentation.

## 3. Technologies Used

| Technology | Purpose |
|---|---|
| Java 21 | Application programming language |
| Spring Boot | REST API development |
| Gradle 9.8.1 | Build automation and dependency management |
| H2 Database | Application data storage |
| JUnit and Mockito | Automated testing and mocking |
| Git | Version control |
| GitHub | Remote repository and collaboration |
| GitHub Actions | Continuous integration automation |
| GitHub Actions Artifacts | Storage and download of the built application JAR |
| PowerShell | Windows command-line operations |
| Visual Studio Code | Source-code editing |

## 4. Application Features

### Task management

The API supports the following operations:

- Create an operational task.
- Retrieve all tasks.
- Retrieve a task by its ID.
- Update an existing task.
- Delete a task.
- Filter tasks by status.
- Filter tasks by priority.
- Filter tasks by type.

### Task types

The supported task types are:

- `DEPLOYMENT`
- `INCIDENT`
- `MAINTENANCE`
- `OTHER`

### Task priorities

The supported priority levels are:

- `LOW`
- `MEDIUM`
- `HIGH`
- `CRITICAL`

### Task statuses

The supported statuses are:

- `OPEN`
- `IN_PROGRESS`
- `RESOLVED`
- `CLOSED`

New tasks default to the `OPEN` status when no other status is provided and the application applies its default.

### Additional capabilities

- Request validation.
- Error handling for invalid or missing resources.
- Task creation and update timestamps.
- Automated service-layer tests.
- Persistent H2 file-based database configuration.
- Automated CI builds and tests.
- Executable JAR packaging and artifact upload.

## 5. REST API Endpoints

The application uses the following base URL during local development:

`http://localhost:8081/api/tasks`

| Method | Endpoint | Purpose | Expected response |
|---|---|---|---|
| POST | `/api/tasks` | Create a task | `201 Created` |
| GET | `/api/tasks` | Retrieve all tasks | `200 OK` |
| GET | `/api/tasks/{id}` | Retrieve one task | `200 OK` |
| PUT | `/api/tasks/{id}` | Update a task | `200 OK` |
| DELETE | `/api/tasks/{id}` | Delete a task | `204 No Content` |

The actual response may depend on the request, validation rules, and whether the requested task exists.

### Filtering tasks

The API also supports filtering using query parameters.

**Filter by status**

`GET /api/tasks?status=IN_PROGRESS`

**Filter by priority**

`GET /api/tasks?priority=HIGH`

**Filter by type**

`GET /api/tasks?type=DEPLOYMENT`

These filters help users retrieve tasks matching specific operational requirements.

## 6. Prerequisites

To run the project locally, install or configure:

- Windows 10 or Windows 11.
- Java Development Kit (JDK) 21.
- Git.
- A code editor such as Visual Studio Code.
- Internet access for downloading dependencies when they are not already cached.

The project includes the Gradle Wrapper, so a separate global Gradle installation is not required.

The project does not require a paid cloud account to build and test locally. GitHub Actions is used for the remote CI workflow.

## 7. Project Setup on Windows

### Step 1: Open the project directory

Open PowerShell and navigate to the project directory:

```powershell
cd "$HOME\Projects\codealpha_tasks\Task3_Java_Gradle_Application"
```

If you stored the project somewhere else, use its actual directory.

### Step 2: Verify Java

Run:

```powershell
java -version
```

Then:

```powershell
javac -version
```

Both commands should report Java 21.

### Step 3: Verify Git

Run:

```powershell
git --version
```

Git should return its installed version.

### Step 4: Verify the Gradle Wrapper

Run:

```powershell
Test-Path .\gradlew.bat
```

The expected result is:

```text
True
```

The wrapper allows the project to use its configured Gradle version.

### Step 5: Run automated tests

Run:

```powershell
.\gradlew.bat test
```

A successful run should finish with:

```text
BUILD SUCCESSFUL
```

### Step 6: Build the application

Run:

```powershell
.\gradlew.bat build
```

This command compiles the project, executes the configured verification tasks, and packages the application when the build succeeds.

The packaged files are written to:

```text
build\libs\
```

## 8. Running the Application

Start the application from the project directory:

```powershell
.\gradlew.bat bootRun
```

When startup succeeds, the application should listen on port `8081`.

The base API URL is:

`http://localhost:8081/api/tasks`

**Important:** Keep the PowerShell window running while testing the application. Stop the application using `Ctrl+C` in the terminal running it.

If port `8081` is already occupied, identify the process listening on that port before taking any action. Do not terminate unrelated Java processes or the Jenkins service simply to free a port.

## 9. Testing the REST API

The following examples use PowerShell's `Invoke-RestMethod`.

Keep the application running in one terminal and execute the requests from a second PowerShell terminal.

### Test 1: Retrieve all tasks

```powershell
Invoke-RestMethod -Method Get `
  -Uri "http://localhost:8081/api/tasks"
```

This retrieves the tasks currently stored in the database.

### Test 2: Create a task

```powershell
$body = @{
    title       = "Verify CodeAlpha project"
    description = "Confirm that the DevOps Operations Tracker API can create and retrieve tasks."
    type        = "MAINTENANCE"
    priority    = "MEDIUM"
} | ConvertTo-Json

$createdTask = Invoke-RestMethod `
    -Method Post `
    -Uri "http://localhost:8081/api/tasks" `
    -ContentType "application/json" `
    -Body $body

$createdTask
```

The response should contain the created task and its generated ID.

The application applies its default status if the request does not provide one.

### Test 3: Retrieve the created task

Use the ID returned by the create request:

```powershell
$taskId = $createdTask.id

Invoke-RestMethod `
  -Method Get `
  -Uri "http://localhost:8081/api/tasks/$taskId"
```

This verifies that the task can be retrieved individually.

### Test 4: Update a task

```powershell
$updateBody = @{
    title       = $createdTask.title
    description = $createdTask.description
    type        = $createdTask.type
    priority    = $createdTask.priority
    status      = "IN_PROGRESS"
} | ConvertTo-Json

Invoke-RestMethod `
    -Method Put `
    -Uri "http://localhost:8081/api/tasks/$taskId" `
    -ContentType "application/json" `
    -Body $updateBody
```

This example updates the task status to `IN_PROGRESS`. The request includes the task's existing fields so they can be retained during the update.

### Test 5: Filter tasks

Filter by status:

```powershell
Invoke-RestMethod `
  -Method Get `
  -Uri "http://localhost:8081/api/tasks?status=IN_PROGRESS"
```

Filter by priority:

```powershell
Invoke-RestMethod `
  -Method Get `
  -Uri "http://localhost:8081/api/tasks?priority=MEDIUM"
```

The results should include tasks matching the requested filter.

### Test 6: Delete a task

```powershell
Invoke-RestMethod `
  -Method Delete `
  -Uri "http://localhost:8081/api/tasks/$taskId"
```

A successful delete normally returns HTTP `204 No Content`, so there may be no response body to display.

Verify the deletion by retrieving the same ID:

```powershell
try {
    Invoke-RestMethod `
      -Method Get `
      -Uri "http://localhost:8081/api/tasks/$taskId"
}
catch {
    Write-Host "Task retrieval failed after deletion, as expected if the API returned 404."
}
```

The API should report that the task is not found after successful deletion.

**Testing note:** These examples create, modify, and delete real records in your configured database. Use test records when demonstrating the application.

## 10. Database Configuration

The project uses H2, a Java relational database, for application data storage.

The configured database is file-based:

```properties
spring.application.name=DevOpsOperationsTracker
spring.datasource.url=jdbc:h2:file:./data/operationsdb
```

The application also uses the configured H2 console for local development.

The H2 console URL is:

`http://localhost:8081/h2-console`

Use the JDBC URL configured in the application properties when connecting through the console.

The database directory is excluded from version control to avoid committing local database files.

**Security note:** The H2 console is intended for local development in this project. Do not expose it publicly or use development database settings as production security configuration.

## 11. Automated Testing

The project includes automated tests for the task service.

The tests cover important behaviours, including:

- Creating a task with the expected default status.
- Retrieving all tasks.
- Retrieving an existing task by ID.
- Handling a task that cannot be found.

JUnit is used for test execution, while Mockito supports isolated service-layer testing where dependencies are mocked.

Run the tests with:

```powershell
.\gradlew.bat test
```

To build the application and run the configured verification tasks, use:

```powershell
.\gradlew.bat build
```

The build must pass before the CI workflow can successfully publish the application artifact.

## 12. Continuous Integration with GitHub Actions

The project uses GitHub Actions to automate build verification.

Workflow file:

```text
.github/workflows/ci.yml
```

### Workflow triggers

The workflow is configured to run when:

- Code is pushed to the `main` or `master` branch.
- A pull request targets the `main` or `master` branch.
- The workflow is manually started using `workflow_dispatch`.

### CI workflow process

1. GitHub Actions checks out the repository source code.
2. Java 21 is configured on the runner.
3. Gradle is configured.
4. The Gradle Wrapper builds the application and runs the configured tests.
5. The workflow inspects the packaged JAR.
6. The executable application JAR is uploaded as a GitHub Actions artifact.

### Build command executed by CI

```bash
./gradlew build
```

The workflow runs on an Ubuntu-hosted GitHub Actions runner.

Using the Gradle Wrapper ensures that CI uses the Gradle version configured by the project rather than depending on a separately installed Gradle version.

### JAR inspection

After the build, the workflow checks the contents of the build output directory and reports the size of the executable JAR.

The expected application artifact is:

```text
build/libs/devops-operations-tracker-0.0.1-SNAPSHOT.jar
```

This step helps verify that the expected application package exists before it is uploaded.

### Artifact upload

The workflow uses:

```yaml
- name: Upload application JAR
  uses: actions/upload-artifact@v4
  with:
    name: devops-operations-tracker
    path: build/libs/devops-operations-tracker-0.0.1-SNAPSHOT.jar
    if-no-files-found: error
    retention-days: 7
```

The artifact is named:

```text
devops-operations-tracker
```

The artifact is retained for **seven days** and can be downloaded from the corresponding successful GitHub Actions workflow run.

This is useful because the application package can be retrieved without rebuilding the project locally.

### CI result

The workflow has successfully completed the build and test process, inspected the packaged application JAR, and uploaded the artifact.

This demonstrates a working CI pipeline for automated build verification and artifact delivery.

**Note:** Successful CI proves that the configured build and tests pass on the runner. It does not, by itself, demonstrate deployment to a production server.

## 13. Viewing the GitHub Repository and Workflow

**GitHub repository:**

https://github.com/stevenadeoti639/CodeAlpha-DevOps-Operations-Tracker

**GitHub Actions:**

https://github.com/stevenadeoti639/CodeAlpha-DevOps-Operations-Tracker/actions

The repository is currently private. You must sign in with an account that has permission to access it.

To inspect the latest workflow:

1. Open the GitHub Actions link.
2. Select the latest successful workflow run.
3. Review the build and test step.
4. Review the JAR inspection step.
5. Open the artifact section at the bottom of the run.
6. Download the `devops-operations-tracker` artifact if needed.

The downloaded artifact is provided as a ZIP file containing the executable JAR.

The artifact is available only while it remains within the configured retention period and the workflow run remains accessible.

## 14. Common Troubleshooting

### Problem 1: Java is not recognized

Check:

```powershell
java -version
```

If PowerShell reports that Java is not recognized, verify the JDK installation and the system `PATH` configuration.

### Problem 2: Gradle build fails

Run:

```powershell
.\gradlew.bat test
```

Read the error output to identify whether the problem relates to compilation, dependencies, tests, or application configuration.

### Problem 3: Port 8081 is already in use

Check which process owns the listening port:

```powershell
Get-NetTCPConnection -LocalPort 8081 -State Listen -ErrorAction SilentlyContinue |
    Select-Object LocalPort, OwningProcess
```

Inspect the identified process before stopping anything. Avoid terminating unrelated Java applications or Jenkins.

### Problem 4: H2 database file is locked

A database lock can occur when another running application instance is using the same database.

Stop the application instance that owns the database connection before starting another instance. Do not delete database files while the database is in use.

### Problem 5: GitHub Actions reports exit code 126

On Linux, a shell script may fail with exit code `126` when it cannot be executed because it lacks the required executable permission.

For this project's Gradle Wrapper, the executable permission was corrected in Git:

```powershell
git update-index --chmod=+x gradlew
```

Commit and push that change when necessary. Windows executes `gradlew.bat`, while the Ubuntu CI runner executes `./gradlew`.

### Problem 6: GitHub Actions cannot find the JAR

Check that the build step succeeded and that the expected JAR exists.

On Windows, run:

```powershell
Get-ChildItem .\build\libs\
```

Compare the generated filename with the `path` configured in the workflow's artifact upload step.

### Problem 7: The artifact is not visible

Confirm that the workflow run completed successfully and that the artifact upload step passed. Also check that you are signed in with permission to access the private repository.

Remember that the artifact is configured to expire after seven days.

## 15. Project Structure

The main project structure is organised as follows:

```text
Task3_Java_Gradle_Application/
|-- .github/
|   +-- workflows/
|       +-- ci.yml
|-- gradle/
|   +-- wrapper/
|-- src/
|   +-- main/
|   |   +-- java/com/codealpha/operations/
|   |   |   +-- controller/OperationalTaskController.java
|   |   |   +-- exception/ApiError.java
|   |   |   +-- exception/GlobalExceptionHandler.java
|   |   |   +-- model/OperationalTask.java
|   |   |   +-- model/TaskPriority.java
|   |   |   +-- model/TaskStatus.java
|   |   |   +-- model/TaskType.java
|   |   |   +-- repository/OperationalTaskRepository.java
|   |   |   +-- service/OperationalTaskService.java
|   |   |   +-- DevOpsOperationsTrackerApplication.java
|   |   +-- resources/application.properties
|   +-- test/java/com/codealpha/operations/
|       +-- DevOpsOperationsTrackerApplicationTests.java
|-- .gitignore
|-- build.gradle
|-- gradlew
|-- gradlew.bat
|-- settings.gradle
+-- README.md
```

The directory layout above describes the main project components. Individual filenames and package subdirectories should be checked against the actual source tree.

## 16. Version Control

Git is used to track source-code changes and maintain the project history.

Common commands used in this project include:

```powershell
git status
git add .
git commit -m "Describe the changes"
git push
```

To inspect recent commits:

```powershell
git log --oneline -5
```

Before committing changes, review the files being staged and ensure that database files, secrets, credentials, and generated build outputs are not being committed.

## 17. Security and Development Notes

- Do not commit passwords, access tokens, private keys, or other credentials.
- Keep local database files out of version control.
- Do not expose the development H2 console to the public internet.
- Validate incoming API requests.
- Review dependencies and update them when appropriate.
- Keep GitHub Actions permissions limited to what the workflow needs.
- Treat the generated JAR as a build artifact, not as proof of a production deployment.
- Configure production-grade authentication, authorisation, secrets management, database security, and deployment controls before using an application like this in a real production environment.

## 18. Future Improvements

Possible improvements include:

- Add integration tests for the REST controllers and database.
- Add more validation and API error-response tests.
- Generate API documentation using OpenAPI.
- Add authentication and role-based authorisation.
- Add structured application logging and health checks.
- Add code-quality and security-scanning stages to CI.
- Introduce environment-specific configuration.
- Add a deployment stage after defining a suitable target environment.
- Add monitoring and operational metrics.
- Add a dashboard for visualising operational tasks.

These are potential extensions and are not all implemented in the current version.

## 19. DevOps Learning Outcomes

This project provides practical experience with:

- Java application development.
- REST API design and CRUD operations.
- Relational database integration.
- Automated unit testing.
- Gradle build automation.
- Git version control.
- GitHub repository management.
- GitHub Actions workflow configuration.
- CI troubleshooting on a Linux runner.
- Executable application packaging.
- Build artifact publishing and retrieval.
- Technical documentation.

The project demonstrates how application code can be validated automatically and packaged into a downloadable artifact through a repeatable CI process.

## 20. Author

**Sunday Peter Adeoti**
**TEECLOUDQ**

CodeAlpha Virtual DevOps Internship

GitHub: https://github.com/stevenadeoti639

---

*This project was developed for learning and internship demonstration purposes. The current implementation focuses on the REST API, automated tests, continuous integration, and application artifact delivery.*





