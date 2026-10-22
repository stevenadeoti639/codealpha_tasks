# CodeAlpha Virtual DevOps Internship Projects

**Prepared by:** Sunday Peter Adeoti

**Personal brand:** TEECLOUDQ

This repository contains three practical projects prepared for the CodeAlpha Virtual DevOps Internship. They demonstrate remote build execution, containerized web deployment, Java application automation, testing, and continuous integration.

## Task 1 — Jenkins Remoting

**Technologies:** Jenkins, Jenkins Remoting, Java 21, Windows

Demonstrates distributed build execution using a Jenkins controller and a separate Windows agent connected over a local network. Remote build and Java execution jobs completed successfully.

- **Documentation:** [Task 1 README](Task1_Jenkins_Remoting/README.md)
- **Evidence folder:** Task1_Jenkins_Remoting/

## Task 2 — TEECLOUDQ DevOps Deployment Hub

**Technologies:** Docker, Docker Desktop, Nginx, HTML, PowerShell

Demonstrates Docker image creation, static website deployment, container lifecycle management, health checks, troubleshooting, and deployment hardening. The website has been verified locally; it is not presented as a publicly hosted service.

- **Documentation:** [Task 2 README](Task2_Docker_WebServer/README.md)
- **Evidence folder:** Task2_Docker_WebServer/evidence/

## Task 3 — DevOps Operations Tracker

**Technologies:** Java 21, Spring Boot, Gradle, H2, JUnit, GitHub Actions

A REST API for creating, managing, and filtering operational tasks. The project includes automated tests, a Gradle build, a GitHub Actions CI workflow, and an application JAR artifact.

The local Gradle tests passed. Evidence of an earlier successful GitHub Actions build and artifact upload is included. The latest local workflow change to support both main and master still requires verification on GitHub.

- **Documentation:** [Task 3 README](Task3_Java_Gradle_Application/README.md)
- **Evidence folder:** Task3_Java_Gradle_Application/evidence/

## Repository Structure

    codealpha_tasks/
    |-- Task1_Jenkins_Remoting/
    |-- Task2_Docker_WebServer/
    |-- Task3_Java_Gradle_Application/
    |-- .gitignore
    +-- README.md
## Verification Summary

| Project | Verification |
|---|---|
| Jenkins Remoting | Remote Windows agent connected; remote build and Java execution jobs succeeded. |
| Docker Web Server | Local HTTP response returned 200; container health and read-only filesystem checks passed. |
| Java Gradle Application | Local Gradle tests passed; an earlier GitHub Actions build and artifact upload succeeded. |

## Security and Reproducibility

- Review each project's README for setup, execution, and verification instructions.
- Generated build outputs and local database files are excluded from version control.
- Do not publish Jenkins agent secrets, passwords, access tokens, private keys, or other credentials.
- The Docker website is a local deployment, not a public hosting service.
- The Java JAR is a build artifact and does not, by itself, demonstrate a production deployment.

## Internship

This repository documents the projects and their verification evidence. Completion or certification remains subject to CodeAlpha's review and submission requirements.


