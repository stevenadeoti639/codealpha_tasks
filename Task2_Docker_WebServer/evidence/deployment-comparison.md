# Docker Deployment Comparison

Project: TEECLOUDQ DevOps Deployment Hub
Author: Sunday Peter Adeoti

## Version 1.0 — Original deployment

- Container: tecloudq-webserver
- Image: tecloudq/deployment-hub:1.0
- URL: http://localhost:8088
- HTTP result: 200
- Expected page title: found
- Health: healthy
- Read-only root filesystem: disabled

## Version 1.1 — Hardened test deployment

- Container: tecloudq-webserver-v11-test
- Image: tecloudq/deployment-hub:1.1
- URL: http://localhost:8089
- HTTP result: 200
- Expected page title: found
- Health: healthy
- Read-only root filesystem: enabled
- Write test: rejected with "Read-only file system"

## Conclusion

Both versions successfully serve the website. Version 1.1
demonstrates an additional filesystem security restriction while
preserving successful HTTP responses and container health.

Version 1.0 is retained as the rollback option.
