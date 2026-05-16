# Bruxinha Náutica

A 2D Java Swing arcade shooter. Play as a witch firing elemental shots at dragons across two stages. In HARD mode, your shot's element must match the enemy's to deal damage.

## Requirements

- Java 8 or later to **run** the JAR (`java -version` to check)
- Java 17 or later to **compile** from source

## Running with VSCode (recommended)

1. Install the [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
2. Open the project folder in VSCode — it compiles automatically to `out/`
3. Open the Run & Debug panel and select **Application**, then click Run

## Running manually

**Compile** (from the project root, using Java 17+ `javac`):

```bash
# Linux / macOS
javac --release 8 -d out $(find . -name "*.java")

# Windows PowerShell
javac --release 8 -d out (Get-ChildItem -Recurse -Filter "*.java" | Where-Object { $_.FullName -notlike "*\out\*" } | Select-Object -ExpandProperty FullName)
```

> `--release 8` makes the output compatible with Java 8 runtimes.

**Run:**

```bash
cd out
java gui.Application
```

## Generating a runnable JAR

From the project root, after compiling to `out/`:

```bash
# Copy assets into the output directory
cp -r graphics out/

# Create the JAR with a manifest pointing to the main class
jar cfe BruxinhaNautica.jar gui.Application -C out .
```

**Windows PowerShell equivalent:**

```powershell
Copy-Item -Recurse graphics out\
jar cfe BruxinhaNautica.jar gui.Application -C out .
```

**Run the JAR:**

```bash
java -jar BruxinhaNautica.jar
```

> The `graphics/` folder must be on the classpath. The JAR above bundles it, so running with `-jar` from any directory works.

## Controls

| Action | Easy | Hard |
|--------|------|------|
| Move left / right | LEFT / RIGHT arrows | LEFT / RIGHT arrows |
| Switch card | — | LEFT / RIGHT arrows |
| Fire | SPACE | UP arrow |

## Gameplay

- Defeat 30 enemies, then a Boss, to advance to Stage 2
- Stage 2 enemies fire back
- **EASY**: any shot damages any enemy
- **HARD**: shot element must match the enemy's element; the Boss also requires the correct element
- Boss requires 49 hits to defeat
