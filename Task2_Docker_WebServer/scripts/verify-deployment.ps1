$ErrorActionPreference = "Stop"

$ContainerName = "tecloudq-webserver-v11-test"
$WebsiteUrl = "http://localhost:8089"
$Failures = 0

Write-Host "`n=== TEECLOUDQ DEPLOYMENT VERIFICATION ===" -ForegroundColor Cyan

try {
    $Container = docker inspect $ContainerName | ConvertFrom-Json
    $State = $Container[0].State

    Write-Host "Container status: $($State.Status)"
    Write-Host "Health status: $($State.Health.Status)"
    Write-Host "Read-only root filesystem: $($Container[0].HostConfig.ReadonlyRootfs)"

    if ($State.Status -ne "running") {
        Write-Host "FAIL: Container is not running."
        $Failures++
    }

    if ($State.Health.Status -ne "healthy") {
        Write-Host "FAIL: Container is not healthy."
        $Failures++
    }

    if (-not $Container[0].HostConfig.ReadonlyRootfs) {
        Write-Host "FAIL: Root filesystem is not read-only."
        $Failures++
    }

    $Response = Invoke-WebRequest -Uri $WebsiteUrl -UseBasicParsing
    Write-Host "HTTP status: $([int]$Response.StatusCode)"

    if ([int]$Response.StatusCode -ne 200) {
        $Failures++
        Write-Host "FAIL: HTTP status is not 200."
    }

    if ($Response.Content.Contains("DevOps Deployment Hub")) {
        Write-Host "PASS: Expected page title found."
    } else {
        $Failures++
        Write-Host "FAIL: Expected page title not found."
    }
}
catch {
    $Failures++
    Write-Host "Verification error: $($_.Exception.Message)"
}

if ($Failures -eq 0) {
    Write-Host "`nRESULT: ALL CHECKS PASSED" -ForegroundColor Green
} else {
    Write-Host "`nRESULT: $Failures CHECK(S) FAILED" -ForegroundColor Red
}

exit $Failures
