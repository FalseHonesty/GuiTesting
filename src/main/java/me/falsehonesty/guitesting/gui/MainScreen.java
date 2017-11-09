package me.falsehonesty.guitesting.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Copyright 2017 (c) FalseHonesty
 */

public class MainScreen extends GuiScreen {
    private ArrayList<GuiItem> items;

    public MainScreen() {
        items = new ArrayList<>();
        items.add(new LogoItem());
        items.add(new SettingsItem());
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawDefaultBackground();

        for (GuiItem item : items) {
            item.drawItem(mouseX, mouseY);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        for (GuiItem item : items) {
            item.mouseClick(mouseX, mouseY);
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        ScaledResolution scaledResolution = new ScaledResolution(mc);

        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();
        int mouseX = Mouse.getX() * width / mc.displayWidth;
        int mouseY = height - Mouse.getY() * height / mc.displayHeight - 1;

        for (GuiItem item : items) {
            if (item instanceof ScrollingItem) {
                ((ScrollingItem) item).handleMouseInput(mouseX, mouseY);
            }
        }
    }
}
