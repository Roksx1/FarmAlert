package net.roks.farmalert.config.screen;


import net.roks.farmalert.service.ConfigService;
import net.roks.farmalert.constant.Constants;
import net.roks.farmalert.config.FarmAlertConfig;
import net.roks.farmalert.config.EdgeConfig;
import net.roks.farmalert.config.TeleportConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class ConfigScreen {

    private ConfigScreen() {
    }

    public static Screen create(Screen parent) {

        ConfigBuilder builder = ConfigBuilder.create();
        builder.setSavingRunnable(ConfigService::save);
        FarmAlertConfig config = ConfigService.getConfig();
        EdgeConfig edgeConfig = config.edge;
        TeleportConfig teleport = config.teleport;

        builder.setParentScreen(parent);

        builder.setTitle(
                Component.translatable("farmalert.title")
        );

        ConfigCategory edgeCategory =
                builder.getOrCreateCategory(
                        Component.translatable("category.edge")
                );
        ConfigCategory teleportCategory =
                builder.getOrCreateCategory(
                        Component.translatable("category.teleport")
                );

        ConfigEntryBuilder entryBuilder =
                builder.entryBuilder();
        var xAxis = entryBuilder.startSubCategory(
                Component.translatable("subcategory.x")
        );

        var zAxis = entryBuilder.startSubCategory(
                Component.translatable("subcategory.z")
        );

        var teleportDetection = entryBuilder.startSubCategory(
                Component.translatable("subcategory.teleport")
        );
        EdgeConfig edge = config.edge;

        xAxis.add(

                entryBuilder.startBooleanToggle(
                                Component.translatable("setting.enable"),
                                edge.xEnabled
                        )
                        .setDefaultValue(true)
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
                                Component.literal("X Tolerance"),
                                edge.xTolerance
                        )
                        .setDefaultValue(0.15)
                        .setSaveConsumer(value -> edge.xTolerance = value)
                        .build()

        );

        zAxis.add(

                entryBuilder.startBooleanToggle(
                                Component.literal("Enable"),
                                edge.zEnabled
                        )
                        .setDefaultValue(true)
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
                                Component.literal("Z Tolerance"),
                                edge.zTolerance
                        )
                        .setDefaultValue(0.15)
                        .setSaveConsumer(value -> edge.zTolerance = value)
                        .build()

        );

        teleportDetection.add(

                entryBuilder.startBooleanToggle(
                                Component.literal("Enable"),
                                teleport.enabled
                        )
                        .setDefaultValue(true)
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
                        .setSaveConsumer(value -> teleport.xTolerance = value)
                        .build()

        );
        teleportDetection.add(

                entryBuilder.startDoubleField(
                                Component.translatable("setting.y_tolerance"),
                                teleport.yTolerance
                        )
                        .setDefaultValue(Constants.DEFAULT_TELEPORT_TOLERANCE)
                        .setSaveConsumer(value -> teleport.yTolerance = value)
                        .build()

        );
        teleportDetection.add(

                entryBuilder.startDoubleField(
                                Component.translatable("setting.z_tolerance"),
                                teleport.zTolerance
                        )
                        .setDefaultValue(Constants.DEFAULT_TELEPORT_TOLERANCE)
                        .setSaveConsumer(value -> teleport.zTolerance = value)
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

        edgeCategory.addEntry(
                xAxis.build()
        );

        edgeCategory.addEntry(
                zAxis.build()
        );

        teleportCategory.addEntry(
                teleportDetection.build()
        );

        return builder.build();

    }

}