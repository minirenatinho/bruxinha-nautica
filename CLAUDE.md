# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

**Bruxinha Náutica** is a 2D Java Swing arcade shooter game. A witch fires elemental shots at dragons across two stages, with a HARD mode requiring element-matching to deal damage.

## Build & Run

No Maven/Gradle — compilation is handled by the VSCode Java Extension (JavaSE-17).

- **Compile**: Open in VSCode with the Java Extension Pack; it compiles automatically to `out/`
- **Run**: Use VSCode's Run/Debug panel → "Application" configuration (launches `gui.Application`)
- **Manual compile**: `javac -d out $(find . -name "*.java")` from project root
- **Manual run**: `cd out && java gui.Application`

No tests exist in this project.

## Architecture

### Entry Point & Game Loop

`gui.Application` creates the JFrame window and calls `GameFacade.startHard()` which creates a `game.Stage` panel. `Stage` is a `JPanel` that implements `ActionListener` — a `javax.swing.Timer` fires every 5ms calling `actionPerformed()`, which is the entire game loop (move, shoot, collide, progress).

### Package Roles

| Package | Responsibility |
|---------|---------------|
| `gui/` | JFrame window setup |
| `facade/` | `GameFacade` — thin wrapper with `startEasy()` / `startHard()` |
| `game/` | `Stage` (game loop, rendering, state machine), `Difficulty` enum |
| `model/` | `Sprite` (base), `Player`, `Enemy`, `Shot`, `Card`, `Element`, `User` |
| `graphics/` | 26 PNG assets (sprites, backgrounds, cards) |

### Key Design Points

**`Sprite`** is the base class for all game objects — holds `x`, `y`, width, height, image, and visibility.

**`Player`** has a 5-card deck of `Element` types (WATER, FIRE, AIR, ELECTRIC, EARTH). In HARD mode, only a shot whose element matches the enemy's element deals damage. Card selection: LEFT/RIGHT arrows; fire: UP arrow (HARD) or SPACE (EASY).

**`Enemy`** has a randomly assigned element. In Stage 2, enemies fire back. A Boss enemy requires 49 hits to defeat.

**`Shot`** carries an `Element` reference (player shots) or `null` (enemy shots).

**Stage progression**: 30 enemies → Boss → Stage 2 (30 enemies with counter-fire) → Boss → win.

### Difficulty Modes

- **EASY**: SPACE to fire; any shot damages any non-boss enemy regardless of element
- **HARD**: UP to fire; shots must match enemy element; boss requires correct element too
