package net.roks.farmalert.screen;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class FarmAlertScreen extends Screen {

    public FarmAlertScreen() {
        super(Component.literal("FarmAlert"));
    }

    @Override
    protected void init() {

        int centerX = this.width / 2;
        int bottom = this.height - 35;

        // Save
        this.addRenderableWidget(
                Button.builder(
                                Component.literal("Save"),
                                button -> {

                                    // TODO Save Config

                                })
                        .bounds(centerX - 105, bottom, 100, 20)
                        .build()
        );

        // Cancel
        this.addRenderableWidget(
                Button.builder(
                                Component.literal("Cancel"),
                                button -> this.onClose()
                        )
                        .bounds(centerX + 5, bottom, 100, 20)
                        .build()
        );

    }

    @Override
    public void extractRenderState(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta) {

        super.extractRenderState(
                graphics,
                mouseX,
                mouseY,
                delta
        );

        int centerX = this.width / 2;

        String title = "FarmAlert";

        graphics.text(
                this.font,
                title,
                centerX - this.font.width(title) / 2,
                15,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                this.font,
                "Edge Detection",
                40,
                50,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                this.font,
                "Teleport",
                40,
                130,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                this.font,
                "Title",
                40,
                220,
                0xFFFFFFFF,
                true
        );

        graphics.text(
                this.font,
                "Debug",
                40,
                290,
                0xFFFFFFFF,
                true
        );

    }

}