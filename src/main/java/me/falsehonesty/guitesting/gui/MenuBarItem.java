package me.falsehonesty.guitesting.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

/**
 * Copyright 2017 (c) FalseHonesty
 */

public class MenuBarItem extends GuiItem {
    private ArrayList<MenuItem> menuItems;

    public MenuBarItem() {
        this.menuItems = new ArrayList<>();
        int spacing = 20;

        ScaledResolution sr = new ScaledResolution(mc);
        float barWidth = fr.getStringWidth("HOMEIRCINFOSPONSORS") + (spacing * 3);
        int x = (int) ((sr.getScaledWidth() / 2) - barWidth + (spacing));

        this.menuItems.add(new MenuItem("HOME", x, false, null));
        x += (bigFr.getStringWidth("HOME")) + spacing;

        this.menuItems.add(new MenuItem("IRC", x, false, null));
        x += (bigFr.getStringWidth("IRC")) + spacing;

        this.menuItems.add(new MenuItem("INFO", x, false, null));
        x += (bigFr.getStringWidth("INFO")) + spacing;

        this.menuItems.add(new MenuItem("SPONSORS", x, true, null));
    }

    @Override
    public void drawItem(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc);

        for (MenuItem menuItem : menuItems) {
            menuItem.drawItem(mouseX, mouseY);
        }
    }

    @Override
    public void mouseClick(int mouseX, int mouseY) {

    }

    class MenuItem extends GuiItem {
        private static final float SCALE = 2.0f;
        private String name;
        private int x;
        private boolean isLast;
        private GuiScreen screenToOpen;

        public MenuItem(String name, int x, boolean isLast, GuiScreen screenToOpen) {
            this.name = name;
            this.x = x;
            this.isLast = isLast;
            this.screenToOpen = screenToOpen;
        }

        public void drawItem(int mouseX, int mouseY) {
            int strWidth = bigFr.getStringWidth(name);

            boolean mouseOver = collides(x, 20, (x + strWidth), 20 + bigFr.size,
                    mouseX, mouseY);

            int color = mouseOver ? 0xFFE6812A : 0xFFFFFFFF;
            int dropShadow = (color & 16579836) >> 2 | color & -16777216;

            bigFr.renderStringWithShadow(name, x, 20, color);

            if (!this.isLast) {
                GL11.glPushMatrix();
                    GL11.glScalef(2.5f, 1.0f, 1.0f);
                    drawVerticalLine((int) ((x + strWidth + 11) / 2.5f), 20, 20 + bigFr.size, 0xFFFFFFFF);
                GL11.glPopMatrix();
            }
        }

        @Override
        public void mouseClick(int mouseX, int mouseY) {
            int strWidth = fr.getStringWidth(name);
            boolean mouseOver = collides(x, 20, x + strWidth, 20 + fr.size, mouseX, mouseY);

            if (mouseOver && screenToOpen != null) {
                mc.displayGuiScreen(screenToOpen);
            }
        }
    }
}
