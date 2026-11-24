---
name: android-delivery
description: Follow ORION AUTO SERVICE roadmap, diagnose GitHub Actions Android build failures, verify APK artifacts, and progress safely through release gates.
---

# Android delivery workflow

Use this skill when implementing the next ORION AUTO SERVICE milestone, diagnosing CI failures, or preparing an APK.

1. Read `AGENTS.md`, `ROADMAP.md`, and `DEVELOPMENT_STATUS.md`.
2. Inspect the latest run in GitHub Actions; record exact failing job, step, and error.
3. Resolve the earliest build blocker before starting unrelated feature work. Do not repeatedly toggle an action without inspecting its log.
4. Implement a focused patch with tests. Check Android Gradle Plugin, Kotlin, Compose, Java, Android SDK, and wrapper compatibility.
5. Push/commit the patch, observe the resulting run, and verify `testDebugUnitTest`, `assembleDebug`, and artifact upload. A queued or started run is not success.
6. Record verified commit, run URL, status, blocker, and next action in `DEVELOPMENT_STATUS.md`.
7. Only after P0 succeeds, implement P1–P9 in roadmap order, preserving offline-first functionality, privacy, and read-only vehicle diagnostics.
8. For release: verify signed artifact provenance, SHA-256, install/upgrade behavior, and GitHub Release asset URL. Never expose keys.

If execution access or CI results are unavailable, record the blocker and the next exact step; do not invent a completed build.
