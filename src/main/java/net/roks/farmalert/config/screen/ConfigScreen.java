package net.roks.farmalert.config.screen;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.roks.farmalert.config.EdgeConfig;
import net.roks.farmalert.config.FarmAlertConfig;
import net.roks.farmalert.config.Profile;
import net.roks.farmalert.config.TeleportConfig;
import net.roks.farmalert.constant.Constants;
import net.roks.farmalert.service.ConfigService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Factory class for creating the Cloth Config GUI screen with profiles support.
 */
public final class ConfigScreen {

    private ConfigScreen() {
    }

    /**
     * Builds and returns the configuration screen using Cloth Config.
     */
    public static Screen create(Screen parent) {

        // Initialize Builder
        ConfigBuilder builder = ConfigBuilder.create();

        // Fetch current configurations
        FarmAlertConfig config = ConfigService.getConfig();

        builder.setParentScreen(parent);
        builder.setTitle(
                Component.translatable("farmalert.title")
        );

        // General settings category (for profiles and global configurations)
        ConfigCategory generalCategory = builder.getOrCreateCategory(
                Component.translatable("category.general")
        );

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // 1. Active Profile Dropdown
        generalCategory.addEntry(
                entryBuilder.startStringDropdownMenu(
                                Component.translatable("setting.active_profile"),
                                config.activeProfile
                        )
                        .setSelections(config.profiles.keySet())
                        .setDefaultValue("default")
                        .setSaveConsumer(value -> config.activeProfile = value)
                        .build()
        );

        // 2. Create Profile Input
        final StringBuilder newProfileName = new StringBuilder();
        generalCategory.addEntry(
                entryBuilder.startStrField(
                                Component.translatable("setting.create_profile"),
                                ""
                        )
                        .setDefaultValue("")
                        .setSaveConsumer(value -> {
                            String trimmed = value.trim();
                            if (!trimmed.isEmpty()) {
                                newProfileName.append(trimmed);
                            }
                        })
                        .build()
        );

        // 3. Delete Profile Dropdown
        final StringBuilder profileToDelete = new StringBuilder();
        List<String> deleteChoices = new ArrayList<>();
        deleteChoices.add(""); // Empty choice = no deletion
        deleteChoices.addAll(config.profiles.keySet());

        generalCategory.addEntry(
                entryBuilder.startStringDropdownMenu(
                                Component.translatable("setting.delete_profile"),
                                ""
                        )
                        .setSelections(deleteChoices)
                        .setDefaultValue("")
                        .setSaveConsumer(value -> {
                            String trimmed = value.trim();
                            if (!trimmed.isEmpty()) {
                                profileToDelete.append(trimmed);
                            }
                        })
                        .build()
        );

        // 4. Global Mod Settings
        generalCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Component.translatable("setting.debug_mode"),
                                config.debugMode
                        )
                        .setDefaultValue(false)
                        .setSaveConsumer(value -> config.debugMode = value)
                        .build()
        );

        generalCategory.addEntry(
                entryBuilder.startBooleanToggle(
                                Component.translatable("setting.play_sound"),
                                config.playSound
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value -> config.playSound = value)
                        .build()
        );

        generalCategory.addEntry(
                entryBuilder.startIntField(
                                Component.translatable("setting.title_duration"),
                                config.titleDuration
                        )
                        .setDefaultValue(40)
                        .setMin(0)
                        .setSaveConsumer(value -> config.titleDuration = value)
                        .build()
        );

        generalCategory.addEntry(
                entryBuilder.startFloatField(
                                Component.translatable("setting.title_scale"),
                                config.titleScale
                        )
                        .setDefaultValue(1.0F)
                        .setMin(0.1F)
                        .setSaveConsumer(value -> config.titleScale = value)
                        .build()
        );

        // Iterate and render individual categories for each profile
        for (Map.Entry<String, Profile> entry : config.profiles.entrySet()) {
            String profileName = entry.getKey();
            Profile profile = entry.getValue();
            EdgeConfig edge = profile.edge;
            TeleportConfig teleport = profile.teleport;

            // Use the profile name directly as the category title
            ConfigCategory profileCategory = builder.getOrCreateCategory(
                    Component.literal(profileName)
            );

            var xAxis = entryBuilder.startSubCategory(
                    Component.translatable("subcategory.x")
            );

            var zAxis = entryBuilder.startSubCategory(
                    Component.translatable("subcategory.z")
            );

            var teleportDetection = entryBuilder.startSubCategory(
                    Component.translatable("subcategory.teleport")
            );

            xAxis.add(
                    entryBuilder.startBooleanToggle(
                                    Component.translatable("setting.enable"),
                                    edge.xEnabled
                            )
                            .setDefaultValue(false)
                            .setSaveConsumer(value -> edge.xEnabled = value)
                            .build()
            );

            xAxis.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.min_x"),
                                    edge.edgeXMin
                            )
                            .setDefaultValue(-10.5)
                            .setSaveConsumer(value -> edge.edgeXMin = value)
                            .build()
            );

            xAxis.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.max_x"),
                                    edge.edgeXMax
                            )
                            .setDefaultValue(10.5)
                            .setSaveConsumer(value -> edge.edgeXMax = value)
                            .build()
            );

            xAxis.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.x_tolerance"),
                                    edge.xTolerance
                            )
                            .setDefaultValue(Constants.DEFAULT_EDGE_TOLERANCE)
                            .setMin(0.0)
                            .setSaveConsumer(value -> edge.xTolerance = value)
                            .build()
            );

            zAxis.add(
                    entryBuilder.startBooleanToggle(
                                    Component.translatable("setting.enable"),
                                    edge.zEnabled
                            )
                            .setDefaultValue(false)
                            .setSaveConsumer(value -> edge.zEnabled = value)
                            .build()
            );

            zAxis.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.min_z"),
                                    edge.edgeZMin
                            )
                            .setDefaultValue(-10.5)
                            .setSaveConsumer(value -> edge.edgeZMin = value)
                            .build()
            );

            zAxis.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.max_z"),
                                    edge.edgeZMax
                            )
                            .setDefaultValue(10.5)
                            .setSaveConsumer(value -> edge.edgeZMax = value)
                            .build()
            );

            zAxis.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.z_tolerance"),
                                    edge.zTolerance
                            )
                            .setDefaultValue(Constants.DEFAULT_EDGE_TOLERANCE)
                            .setMin(0.0)
                            .setSaveConsumer(value -> edge.zTolerance = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startBooleanToggle(
                                    Component.translatable("setting.enable"),
                                    teleport.enabled
                            )
                            .setDefaultValue(false)
                            .setSaveConsumer(value -> teleport.enabled = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.target_x"),
                                    teleport.targetX
                            )
                            .setDefaultValue(0.0)
                            .setSaveConsumer(value -> teleport.targetX = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.target_y"),
                                    teleport.targetY
                            )
                            .setDefaultValue(0.0)
                            .setSaveConsumer(value -> teleport.targetY = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.target_z"),
                                    teleport.targetZ
                            )
                            .setDefaultValue(0.0)
                            .setSaveConsumer(value -> teleport.targetZ = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.x_tolerance"),
                                    teleport.xTolerance
                            )
                            .setDefaultValue(Constants.DEFAULT_TELEPORT_TOLERANCE)
                            .setMin(0.0)
                            .setSaveConsumer(value -> teleport.xTolerance = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.y_tolerance"),
                                    teleport.yTolerance
                            )
                            .setDefaultValue(Constants.DEFAULT_TELEPORT_TOLERANCE)
                            .setMin(0.0)
                            .setSaveConsumer(value -> teleport.yTolerance = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.z_tolerance"),
                                    teleport.zTolerance
                            )
                            .setDefaultValue(Constants.DEFAULT_TELEPORT_TOLERANCE)
                            .setMin(0.0)
                            .setSaveConsumer(value -> teleport.zTolerance = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startDoubleField(
                                    Component.translatable("setting.command_delay"),
                                    teleport.commandDelay
                            )
                            .setDefaultValue(2.0)
                            .setMin(0.0)
                            .setSaveConsumer(value -> teleport.commandDelay = value)
                            .build()
            );

            teleportDetection.add(
                    entryBuilder.startStrField(
                                    Component.translatable("setting.command"),
                                    teleport.command
                            )
                            .setDefaultValue("")
                            .setSaveConsumer(value -> teleport.command = value)
                            .build()
            );

            profileCategory.addEntry(xAxis.build());
            profileCategory.addEntry(zAxis.build());
            profileCategory.addEntry(teleportDetection.build());
        }

        // Configure save and action triggers
        builder.setSavingRunnable(() -> {
            // 1. Process profile deletion
            String toDelete = profileToDelete.toString();
            if (!toDelete.isEmpty() && config.profiles.containsKey(toDelete)) {
                if (config.profiles.size() > 1) {
                    config.profiles.remove(toDelete);
                    if (config.activeProfile.equals(toDelete)) {
                        config.activeProfile = config.profiles.keySet().iterator().next();
                    }
                }
            }

            // 2. Process profile creation
            String toAdd = newProfileName.toString();
            if (!toAdd.isEmpty() && !config.profiles.containsKey(toAdd)) {
                Profile newProfile = new Profile();
                Profile active = config.getActiveProfile();
                
                // Copy settings from current active profile
                newProfile.edge.xEnabled = active.edge.xEnabled;
                newProfile.edge.zEnabled = active.edge.zEnabled;
                newProfile.edge.edgeXMin = active.edge.edgeXMin;
                newProfile.edge.edgeXMax = active.edge.edgeXMax;
                newProfile.edge.edgeZMin = active.edge.edgeZMin;
                newProfile.edge.edgeZMax = active.edge.edgeZMax;
                newProfile.edge.xTolerance = active.edge.xTolerance;
                newProfile.edge.zTolerance = active.edge.zTolerance;

                newProfile.teleport.enabled = active.teleport.enabled;
                newProfile.teleport.targetX = active.teleport.targetX;
                newProfile.teleport.targetY = active.teleport.targetY;
                newProfile.teleport.targetZ = active.teleport.targetZ;
                newProfile.teleport.xTolerance = active.teleport.xTolerance;
                newProfile.teleport.yTolerance = active.teleport.yTolerance;
                newProfile.teleport.zTolerance = active.teleport.zTolerance;
                newProfile.teleport.commandDelay = active.teleport.commandDelay;
                newProfile.teleport.command = active.teleport.command;

                config.profiles.put(toAdd, newProfile);
                config.activeProfile = toAdd;
            }

            // Save the config configuration to file
            ConfigService.save();
        });

        return builder.build();

    }

}
