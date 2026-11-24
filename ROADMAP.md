# ORION AUTO SERVICE — Product and engineering plan
Status: active development; do not claim complete until release checklist passes.

## Goal
Native Kotlin / Jetpack Compose Android workshop agent, offline first, no WebView. Usable for real car-service operations. Non-destructive on vehicle, no automated ECU programming.

## Work phases / acceptance gates
- [ ] P0 — reproducible GitHub Actions debug APK, unit tests, secure manifest, package identifier.
- [ ] P1 — Room entities with migrations: clients, vehicles (VIN uniqueness), visits, inspection items, work orders, labor operations, parts, inventory movements, documents, payments, reminders, notes. CRUD/search/filter, local persistence, export/import with backup.
- [ ] P2 — Workshop workflow: appointment -> check-in -> inspection -> estimate -> customer approval -> repairs -> QA -> delivery. State transitions guarded; audit log. VAT/tax configurable; prices stored in minor units.
- [ ] P3 — Vehicle record, history, photos, mileage, attachments via scoped storage. Consent/privacy, deletion/export.
- [ ] P4 — Service jobs and parts: labor catalog, estimates, invoices/PDF, printable checklists, inventory reservations, purchase list.
- [ ] P5 — Bluetooth OBD-II ELM327: runtime permission, discovery, pairing, connection FSM, adapter identification, read-only Mode 01 live data and Mode 03 DTCs. Simulator and integration tests. No erase DTCs, actuators or coding without separate safeguards.
- [ ] P6 — AI service agent: configurable ORION server or Ollama endpoint, explicit connectivity and authentication, private by default, local rules-based fallback, advice traceability, source-linked technical manuals when licensed/available, no fabricated repair specs.
- [ ] P7 — Calendar, notifications, reminders, roles and access control for mechanics and owner; offline multi-user sync only after auth/conflicts tested.
- [ ] P8 — Security: encrypted credentials, HTTPS transport to server, backup encryption, tests, accessibility, crash handling, data migration.
- [ ] P9 — QA: Android emulator UI tests, Gradle build, release signing configured via secrets (never commit keys), tagged GitHub Release APK with version + SHA-256 and upgrade test.

## Progress discipline
Every change must be committed with tests and verified CI; do not say APK exists until Actions artifact is verified. When CI cannot be read, report verification blocker and do not invent results. Produce small coherent commits, report unfinished items accurately.
## Immediate priority
Fix first APK build (Compose / Gradle compiler), add core persistence and navigation, then address subsequent gates.
