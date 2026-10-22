# CodeAlpha Docker Web Server — Requirements Mapping

Author: Sunday Peter Adeoti (TEECLOUDQ)

## 1. Docker containerization basics
**Status:** Demonstrated

Evidence:
- `Dockerfile`
- `evidence/Dockerfile-v1.0.txt`
- Docker image versions 1.0 and 1.1 built and inspected.

## 2. Deploy and manage a web server inside Docker
**Status:** Demonstrated

Evidence:
- `site/index.html`
- `Dockerfile`
- Local HTTP tests on ports 8088 and 8089.
- `evidence/website-browser-final.png`
- `evidence/deployment-verification-success.txt`
- `evidence/deployment-verification-success.png`
- `evidence/deployment-verification-final.txt`
- `evidence/deployment-comparison.md`

## 3. Understand container lifecycle and commands
**Status:** Demonstrated

Activities:
- Created, inspected, stopped and restarted containers.
- Examined container configuration and logs.
- Verified image and container status.

## 4. Monitor container health and troubleshoot
**Status:** Demonstrated

Evidence:
- Docker health checks.
- Successful HTTP response and page-title verification.
- Deliberately unhealthy test container and subsequent cleanup.
- `scripts/verify-deployment.ps1`
- `evidence/hardening-test-report.txt`

## 5. Explore container deployment best practices
**Status:** Demonstrated

Practices:
- Explicit image tags and metadata.
- Resource limit: 256 MiB configured and verified.
- Restart policy: `no` on version 1.1; automatic restart is not enabled.
- Docker health checks.
- Read-only root filesystem with temporary writable paths.
- `.dockerignore` and `.gitignore`.
- Version 1.0 retained as a rollback option during testing.

## Final validation

The version 1.1 test container was verified as running and healthy. Its root filesystem is read-only, its memory limit is 256 MiB, and its restart policy is `no` (automatic restart is not enabled).
The website returned HTTP 200 and the expected page title was found.

Version 1.0 remains available as the original deployment and rollback option; version 1.1 is the hardened test deployment.

## Repository

GitHub: https://github.com/stevenadeoti639/CodeAlpha_Docker_WebServer

Repository visibility: Private









