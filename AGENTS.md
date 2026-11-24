# ORION AUTO SERVICE — Codex project instructions

## Mission
Deliver a genuinely installable, tested, native Android APK for a workshop-management and diagnostic assistant. The source of truth for scope and acceptance gates is [ROADMAP.md](ROADMAP.md). Keep the user-facing application in Kotlin + Jetpack Compose; do not replace it with a WebView or a demo-only shell.

## Operating loop
1. Read this file, ROADMAP.md, README.md, and the latest GitHub Actions run before modifying code.
2. Select the earliest incomplete phase P0–P9. Break it into a small, testable change; prioritize build blockers before features.
3. Implement source, tests, and necessary Gradle/manifest changes together.
4. Run available tests/build checks; commit changes and inspect the newly triggered CI run. If CI fails, read the exact failing step and logs, fix the root cause, and repeat. Never label a run successful merely because it was queued.
5. Update DEVELOPMENT_STATUS.md with confirmed evidence (run URL, conclusion, commit, blockers, next action). Mark ROADMAP.md checkboxes only when all acceptance criteria for the phase are verified.
6. Continue to the next unresolved item without asking the user to reauthorize routine repository work. Stop for secrets, signing credentials, account connections, hardware pairing, destructive vehicle actions, or other approvals that genuinely require the user.

## Engineering rules
- Android client is standalone; ORION home PC remains the optional primary server. Offline-first workflows must work without the server.
- Use Android runtime Bluetooth permissions and a testable ELM327 simulator. Read-only Mode 01 and 03 by default; never erase codes or issue ECU programming commands automatically.
- Do not fabricate car repair specs, diagnostic measurements, test results, APKs, or download links.
- No credentials, signing keys, tokens, personal vehicle records, or customer data in Git.
- Prefer small cohesive commits, schema migrations, tests, rollback-safe changes, and explicit error handling.
- Avoid parallel duplicate APK workflows; ensure one canonical build pipeline and artifact name.
- Report failures and blockers clearly. Never claim a release is ready until an APK artifact or GitHub Release asset is verified.

## Definition of done
All ROADMAP.md gates completed; GitHub Actions build and tests pass; installation and upgrade path validated; release APK signed, versioned, checksummed, and downloadable from GitHub Releases.

## Codex reusable procedure
For the detailed CI-and-roadmap workflow, read .agents/skills/android-delivery/SKILL.md.
