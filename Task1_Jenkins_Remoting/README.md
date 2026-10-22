# CodeAlpha Task 1: Jenkins Remoting Project

**Prepared by:** Sunday Peter Adeoti  
**Student ID:** CADF1/322076  
**Project:** CodeAlpha Virtual DevOps Internship  
**Technology:** Jenkins, Jenkins Remoting, Java 21, Windows

## 1. Project Overview

This project demonstrates how Jenkins can distribute build execution from a controller machine to a separate remote agent. The controller manages jobs, while the remote agent executes assigned build tasks.

The project uses two Windows computers connected over the same local network.

## 2. Project Objectives

- Configure a Jenkins controller and a remote build agent.
- Connect the remote agent securely to the controller.
- Assign jobs to a specific agent using a Jenkins label.
- Execute build tasks on a separate computer.
- Compile and run a Java application on the remote agent.
- Capture build logs and verification evidence.

## 3. Architecture

| Component | Role |
|---|---|
| HP EliteBook | Jenkins controller |
| HP ProBook | Remote build agent |
| Jenkins | Job scheduling and build orchestration |
| Jenkins Remoting | Communication between controller and agent |
| Java 21 | Java runtime and compiler on the remote agent |
| Windows | Operating system on both computers |

The remote node is named `probook-agent` and uses the label `probook-agent`.

## 4. Remote Agent Configuration

| Setting | Value |
|---|---|
| Node name | `probook-agent` |
| Executors | 1 |
| Remote root directory | `C:\Jenkins\agent` |
| Label | `probook-agent` |
| Usage | Only jobs matching the node's label |
| Launch method | Agent connects to the controller |
| Availability | Keep online as much as possible |

The agent connection secret is intentionally excluded from this repository.

## 5. Implemented Jobs

### Job 1: Probook-Remote-Build

This job verifies that Jenkins can execute a Windows batch script on the remote agent.

The job reports:
- The remote computer hostname.
- The Windows operating system version.
- The installed Java version.
- The remote workspace directory.

**Verified result:** `SUCCESS`

### Job 2: ProBook-Java-Remote-Build

This job creates a Java source file in the remote workspace, compiles it using `javac`, and executes the resulting application using Java.

The application confirms that it ran on the remote build machine.

**Verified result:** `SUCCESS`

## 6. Verification Results

Both jobs completed successfully on `probook-agent`.

The recorded console output confirms remote execution. The Java job also confirms successful compilation and execution of the Java application.

The agent configuration and connection status were recorded as project evidence.

## 7. Evidence Directory

The `evidence/` directory contains:

- `agent-configuration.txt` — remote agent configuration summary.
- `jenkins-agent-connected.png` — screenshot showing the connected agent and successful jobs.
- `Probook-Remote-Build-console.txt` — console output from the remote build job.
- `ProBook-Java-Remote-Build-console.txt` — console output from the remote Java build.
- `remote-build-verification.txt` — remote build verification summary.
- `remote-java-build-verification.txt` — remote Java build verification summary.
- `jenkins-remote-build-success.png` — screenshot of the successful remote build console output.
- `jenkins-remote-java-build-success.png` — screenshot of the successful remote Java build console output.

## 8. Security and Reliability Considerations

- Keep Jenkins authentication credentials and agent secrets private.
- Restrict network access to the Jenkins controller to trusted devices.
- Use dedicated agent workspaces for build execution.
- Check console output when diagnosing failed builds.
- Keep the agent connected while remote jobs are expected to run.
- For a production deployment, configure appropriate authentication, authorization, network protection, and secure agent communication.

## 9. Skills Demonstrated

- Jenkins controller and agent configuration.
- Distributed build execution.
- Jenkins node labels and job assignment.
- Windows command-line scripting.
- Java compilation and execution.
- Build verification and console-log analysis.
- Technical documentation and evidence collection.

## 10. Conclusion

This project demonstrates a working Jenkins Remoting setup in which a controller delegates build tasks to a remote Windows agent. Two jobs were executed successfully, including a Java compilation and execution task on the agent.

The project provides practical experience with distributed CI workloads, remote execution, and Jenkins build verification.

---

**Project author:** Sunday Peter Adeoti  
**CodeAlpha Student ID:** CADF1/322076
