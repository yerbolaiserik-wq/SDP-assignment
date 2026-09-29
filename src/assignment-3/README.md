# Assignment 3: Adapter and Bridge Patterns
Aisyerik yerbol from SE-2536

Topic: Operating System Windows

This beginner-level project shows two design patterns:

- Bridge Pattern separates a Windows notice from the way it is displayed.
- Adapter Pattern connects an old Command Prompt tool to the new output interface.

## How to Run

From this folder:

```powershell
.\scripts\run.ps1
```

If Maven is installed, you can also run the unit tests:

```powershell
mvn test
```

## Bridge Pattern

Bridge is used when one idea can change in two directions.

In this project:

- `SystemNotice` is the abstraction.
- `UpdateNotice` and `SecurityNotice` are refined abstractions.
- `OutputChannel` is the implementor.
- `ToastChannel`, `EventLogChannel`, and `CommandPromptAdapter` are concrete implementors.

This means the same Windows notice can be displayed as a toast, saved in event log, or printed in old command prompt style.

## Adapter Pattern

Adapter is used when an old class does not match the interface required by the new system.

In this project:

- `LegacyCommandPrompt` is the old class.
- `CommandPromptAdapter` adapts it to the `OutputChannel` interface.

## Sample Output

```text
=== Bridge Pattern ===
Toast notification displayed Windows update notice with code UPDATE-2026
Event log saved Windows security notice with code SECURITY-401

=== Adapter Pattern ===
Old command prompt displayed Windows update notice with code CMD-1-L
```
