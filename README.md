# FarmAlert

A lightweight Fabric mod that displays configurable on-screen title alerts when you reach the edge of your farm or a custom teleport point.

The mod is designed for Minecraft farms (like on Hypixel SkyBlock) where you repeatedly move between lanes and need a clear visual indicator when it's time to change direction or teleport.

## Features

* Configurable left and right farm edge detection
* Optional custom teleport point alert with delayed command triggers
* Large in-game title notifications
* Configurable coordinates via in-game menu
* Works entirely client-side
* Lightweight with minimal performance impact

---

## Installation

1. Ensure you have the Fabric Loader installed for Minecraft 26.2.
2. Install the **Fabric API** and **Cloth Config API**.
3. Place the `FarmAlert-mc26.2-*.jar` file inside your Minecraft `mods` folder.
4. (Optional) Install **Mod Menu** to access the in-game configuration screen easily.
5. Launch Minecraft.

---

## Configuration

Open the mod's configuration screen by using **Mod Menu** or press the config hotkey (unbound by default, configure in Minecraft's controls settings under **FarmAlert**).

Within the config GUI you can adjust:
* **Edge Detection**: Min/Max bounds on X and Z axes along with precision tolerances.
* **Teleport alert**: Custom XYZ coordinate targets, custom command delay timings, and command strings to dispatch (e.g. `/warp garden`).

---

## Building

Clone the repository:

```bash
git clone https://github.com/Roksx1/FarmAlert.git
cd FarmAlert
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

* Minecraft `26.2`
* Fabric Loader `>=0.19.3`
* Fabric API `>=0.155.0+26.2`
* Cloth Config API `>=26.2.155`
* (Optional) Mod Menu `>=20.0.0`

---

## License

This project is licensed under the MIT License.
