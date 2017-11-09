package me.falsehonesty.guitesting.gui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

/**
 * Copyright 2017 (c) FalseHonesty
 */

public abstract class ScrollingItem extends GuiItem {
    protected int scrolled;

    public void handleMouseInput(int mouseX, int mouseY) {
        int i = Mouse.getEventDWheel();
        if (i == 0) return;

        i = clamp(i, -1, 1);

        i = GuiScreen.isShiftKeyDown() ? i * 10 : i * 20;

        this.scrolled -= i;
        this.scrolled = clamp(this.scrolled, 0, 100);
    }



    private int clamp(int toClamp, int min, int max) {
        return toClamp < min ? min : toClamp > max ? max : toClamp;
    }
}
