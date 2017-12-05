package me.falsehonesty.guitesting.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

/**
 * Copyright 2017 (c) FalseHonesty
 */

public abstract class GuiItem extends Gui {
    protected Minecraft mc = Minecraft.getMinecraft();
    protected static CustomFont fr = new CustomFont("Bebas", 18);
    protected static CustomFont medFr = new CustomFont("Bebas", 26);
    protected static CustomFont bigFr = new CustomFont("Bebas", 36);

    public abstract void drawItem(int mouseX, int mouseY);

    public abstract void mouseClick(int mouseX, int mouseY);

    public boolean collides(int minX, int minY, int maxX, int maxY, int actualX, int actualY) {
        return minX <= actualX && actualX <= maxX
                && minY <= actualY && actualY <= maxY;
    }

    public void drawStringWithBox(String text, int startX, int startY, CustomFont fr) {
        GL11.glPushMatrix();
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
            GL11.glLineWidth(3.0f);

            int textWidth = fr.getStringWidth(text);
            int textHeight = fr.size;
            drawRect(startX, startY, (startX + textWidth + 20), (startY + textHeight + 15), 0xFFFFFFFF);

            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);

            fr.renderStringWithShadow(text, (startX + 7), (startY + 7), 0xFFFFFFFF);
        GL11.glPopMatrix();
    }

    public static int easeIn(int start, int finish, double jump) {
        double diff = finish - start;
        //System.out.println("diff " + diff);
        //System.out.println(diff * jump);
        //System.out.println((int) Math.floor(diff * jump));
        return (int) Math.floor(diff * jump) + start;
    }

    public static int easeOut(int start, int finish, double jump) {
        double diff = start - finish;
        //System.out.println("diff " + diff);
        //System.out.println(diff * jump);
        //System.out.println((int) Math.floor(diff * jump));
        return (int) Math.floor(diff * jump) + finish;
    }
}
