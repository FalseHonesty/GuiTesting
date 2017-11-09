package me.falsehonesty.guitesting.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright 2017 (c) FalseHonesty
 */

public class SettingsItem extends ScrollingItem {
    private List<String> linesToDraw = Arrays.asList(
            "Setting 1", "Setting 2", "Setting 3", "Setting 4", "Setting 5",
            "Setting 6", "Setting 7", "Setting 8", "Setting 9", "Setting 10"
    );

    @Override
    public void drawItem(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc);

        drawBox(100, 50, 150, 90, 0xFFFFFFFF);
        drawString(mc.fontRendererObj, "Settings", 105, 60, 0xFFFFFFFF);

        for (int i = 0; i < linesToDraw.size(); i++) {
            String toDraw = linesToDraw.get(i);

            int y = (100 * (i + 1)) - this.scrolled;

            if (y < 100 || y > sr.getScaledHeight() - 50) continue;

            int color = collidesText(y, toDraw, mouseX, mouseY) ? 0xFFF47A42 : 0xFFFFFFFF;
            drawString(mc.fontRendererObj, toDraw, 100, y, color);
        }
    }

    @Override
    public void mouseClick(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc);

        for (int i = 0; i < linesToDraw.size(); i++) {
            String toDraw = linesToDraw.get(i);

            int y = (100 * (i + 1)) - this.scrolled;

            if (y < 100 || y > sr.getScaledHeight() - 50) continue;

            if (collidesText(y, toDraw, mouseX, mouseY)) {
                //TODO: DO SOMETHING
            }
        }
    }

    private boolean collidesText(int y, String toDraw, int mouseX, int mouseY) {
        FontRenderer fr = mc.fontRendererObj;

        return collides(100, y, 100 + fr.getStringWidth(toDraw),
                y + fr.FONT_HEIGHT, mouseX, mouseY);
    }

    private void drawBox(int x, int y, int height, int width, int color) {
        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;

        GL11.glPushMatrix();
        GL11.glColor4f(f, f1, f2, f3);
        //GL11.glLineWidth(2f);

        GL11.glBegin(GL11.GL_QUADS);
            GL11.glVertex2i(x, y);
            GL11.glVertex2i(x + width, y);
            GL11.glVertex2i(x + width, y + height);
            GL11.glVertex2i(x, y + height);
        GL11.glEnd();

        GL11.glPopMatrix();
    }
}
