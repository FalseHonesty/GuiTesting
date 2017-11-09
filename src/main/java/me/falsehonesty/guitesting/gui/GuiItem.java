package me.falsehonesty.guitesting.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

/**
 * Copyright 2017 (c) FalseHonesty
 */

public abstract class GuiItem extends Gui {
    protected Minecraft mc = Minecraft.getMinecraft();

    public abstract void drawItem(int mouseX, int mouseY);

    public abstract void mouseClick(int mouseX, int mouseY);

    public boolean collides(int minX, int minY, int maxX, int maxY, int actualX, int actualY) {
        return minX <= actualX && actualX <= maxX
                && minY <= actualY && actualY <= maxY;
    }
}
