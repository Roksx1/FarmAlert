# FarmAlert

A lightweight Fabric mod that displays configurable on-screen title alerts when you reach the edge of your farm or a custom teleport point.

The mod is designed for farms where you repeatedly move between lanes and need a clear visual indicator when it's time to change direction or teleport.

## Features

* Configurable left and right farm edge detection
* Optional custom teleport point alert
* Large in-game title notifications
* Configurable coordinates
* Adjustable detection distance
* Works entirely client-side
* Lightweight with minimal performance impact

---

## Installation

1. Install the Fabric Loader for the supported Minecraft version.
2. Install the Fabric API.
3. Download the latest release from the **Releases** page.
4. Place the `.jar` file inside your Minecraft `mods` folder.
5. Launch Minecraft.

---

## Configuration

Open the mod's configuration screen from Mod Menu (if installed) or use the provided configuration interface.

Configure:

* Left farm edge
* Right farm edge
* Teleport point
* Detection distance
* Title text

Save the configuration and the mod will begin monitoring your position.

---

## Building

Clone the repository:

```bash
git clone https://github.com/Roksx1/FarmAlert.git
cd REPOSITORY
```

Build the mod:

```bash
./gradlew build
```

The compiled mod will be located in:

```
build/libs/
```

---

## Requirements

* Minecraft (supported version)
* Fabric Loader
* Fabric API

---

## License

This project is licensed under the MIT License.
