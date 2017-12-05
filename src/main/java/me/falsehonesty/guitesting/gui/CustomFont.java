package me.falsehonesty.guitesting.gui;

import net.minecraft.client.Minecraft;

public class CustomFont {
    private StringCache stringCache;
    private int[] colorCode;
    private String fontName;
    public int size;

    public CustomFont(String fontName, int fontSize) {
        this.setFont(fontName, fontSize);
    }

    public CustomFont(String fontName) {
        this.setFont(fontName);
    }

    public void setFont(String fontName) {
        this.setFont(fontName, 18);
    }

    public void setFont(String fontName, int fontSize) {
        this.colorCode = new int[32];

        for (int i = 0; i < 32; ++i)
        {
            int j = (i >> 3 & 1) * 85;
            int k = (i >> 2 & 1) * 170 + j;
            int l = (i >> 1 & 1) * 170 + j;
            int i1 = (i >> 0 & 1) * 170 + j;

            if (i == 6)
            {
                k += 85;
            }

            if (Minecraft.getMinecraft().gameSettings.anaglyph)
            {
                int j1 = (k * 30 + l * 59 + i1 * 11) / 100;
                int k1 = (k * 30 + l * 70) / 100;
                int l1 = (k * 30 + i1 * 70) / 100;
                k = j1;
                l = k1;
                i1 = l1;
            }

            if (i >= 16)
            {
                k /= 4;
                l /= 4;
                i1 /= 4;
            }

            this.colorCode[i] = (k & 255) << 16 | (l & 255) << 8 | i1 & 255;
        }

        this.stringCache = new StringCache(this.colorCode);
        this.stringCache.setDefaultFont(fontName, fontSize, true);
        this.fontName = fontName;
        this.size = fontSize;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void refresh() {
        this.setFont(fontName);
    }

    public int renderString(String str, float posX, float posY, int color, boolean shadow) {
        return this.stringCache.renderString(str, (int)posX, (int)posY, color, shadow);
    }

    public int renderStringWithShadow(String str, int posX, int posY, int color) {
        int sColor = (color & 16579836) >> 2 | color & -16777216;
        int var6 = this.renderString(str, posX + 0.5F, posY + 0.5F, sColor, true);
        var6 = Math.max(var6, this.renderString(str, posX, posY, color, false));
        return var6;
    }

    public int getStringWidth(String str) {
        return this.stringCache.getStringWidth(str);
    }

    public String stripColorCodes(String original) {
        String colorCode = "0123456789abcdefklmnor";
        for (int x = 0; x < colorCode.length(); x++)
            original = original.replaceAll("\247" + colorCode.indexOf(x), "");
        return original;
    }
}