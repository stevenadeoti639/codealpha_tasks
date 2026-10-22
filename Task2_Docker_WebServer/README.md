# TEECLOUDQ DevOps Deployment Hub

A containerized static website built with Nginx and Docker for the CodeAlpha Virtual DevOps Internship.

## Project objectives

This project demonstrates:

- Docker image creation and container deployment.
- Hosting a static website with Nginx.
- Container lifecycle management.
- Health checks, logging, and troubleshooting.
- Container resource limits and restart policies.
- Read-only root filesystem hardening.
- Deployment verification with PowerShell.

## Technology stack

- Docker Desktop
- Nginx Alpine
- HTML
- PowerShell
- Windows 11

## Project structure

```text
CodeAlpha_Docker_WebServer/
├── site/
│   └── index.html
├── scripts/
│   └── verify-deployment.ps1
├── config/
├── evidence/
├── Dockerfile
├── .dockerignore
└── README.md
```

## Build the image

```powershell
docker build -t tecloudq/deployment-hub:1.1 .
```

## Run the hardened container

```powershell
docker run -d `
  --name tecloudq-webserver-v11-test `
  --publish 8089:80 `
  --read-only `
  --tmpfs /var/cache/nginx `
  --tmpfs /var/run `
  --tmpfs /tmp `
  --memory 256m `
  --cpus 1 `
  tecloudq/deployment-hub:1.1
```

Open http://localhost:8089 in your browser.

## Verify the deployment

The verification script checks the container state, health status, read-only root filesystem, HTTP response, and expected page title.

```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
.\scripts\verify-deployment.ps1
```

The execution-policy change applies only to the current PowerShell session.

## Useful Docker commands

```powershell
docker ps
docker ps -a
docker logs tecloudq-webserver-v11-test
docker inspect tecloudq-webserver-v11-test
docker stop tecloudq-webserver-v11-test
docker start tecloudq-webserver-v11-test
```

## Security and operational practices

- Use explicit image tags to identify releases.
- Apply CPU and memory limits.
- Use a read-only root filesystem where compatible.
- Provide temporary writable paths only where required.
- Configure and inspect health checks.
- Review logs when troubleshooting.
- Keep evidence and deployment instructions with the source code.

## Evidence

The `evidence/` directory contains:

- `deployment-verification-final.txt` — earlier final PowerShell verification transcript.
- `deployment-verification-success.txt` — latest successful verification output.
- `deployment-verification-success.png` — screenshot showing the passing verification result.
- `deployment-comparison.md` — comparison of the deployment versions.
- `Dockerfile-v1.0.txt` — preserved copy of the original version 1.0 Dockerfile.
- `hardening-test-report.txt` — report on the version 1.1 hardening test.
- `requirements-mapping.md` — mapping of project requirements to implementation.
- `website-browser.png` — original screenshot showing the website in a browser.
- `website-browser-final.png` — latest website screenshot for final project evidence.

Separate image-inspection and container-inspection records are not currently listed
in this evidence folder. The final verification transcript and other evidence files
should be reviewed alongside the source code.

## Current validation results

The version 1.1 test container was verified as running and healthy, with a read-only root filesystem. The website returned HTTP 200 and the expected page title was found.

The original version 1.0 container was retained as a rollback option during testing.

## Limitations

This project hosts a static website. It does not include a database, authentication system, or public production hosting. The current deployment is intended for local demonstration and educational evaluation.

## Author

Sunday Peter Adeoti - TEECLOUDQ

CodeAlpha Virtual DevOps Internship

## Deployment versions and verification

This project maintains two local deployments for comparison.

| Version | Container | Local URL | Purpose |
|---|---|---|---|
| 1.0 | `tecloudq-webserver` | http://localhost:8088 | Original deployment and rollback option |
| 1.1 | `tecloudq-webserver-v11-test` | http://localhost:8089 | Hardened test deployment |

Version 1.1 has a read-only root filesystem and temporary writable
paths for Nginx runtime operation. The read-only restriction was
tested by attempting to create a file in the website directory;
the write was correctly rejected.

The inspected version 1.1 container has a 256 MiB memory limit and
uses Docker's `no` restart policy, so Docker does not automatically
restart it under a configured restart policy. These are the actual
settings of the current test container, not a claim that automatic
restarts are enabled.

### Automated verification

Run the verification script from PowerShell:

```powershell
Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
.\scripts\verify-deployment.ps1
```

The script currently verifies the version 1.1 test container:
- Container is running.
- Container health status is healthy.
- Read-only root filesystem is enabled.
- Website responds with HTTP 200.
- Expected page title is present.

The script exits with a nonzero status when its checks fail.

### Important operational notes

- Start Docker Desktop before running Docker commands.
- After restarting Windows, check whether the containers are running.
- Start existing containers with `docker start` when they are stopped.
- Keep version 1.0 available until the hardened deployment is accepted.
- These deployments are local demonstrations, not public production hosting.
- Keep the GitHub repository private unless its visibility is deliberately changed.




