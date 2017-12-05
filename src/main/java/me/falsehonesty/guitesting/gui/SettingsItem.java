package me.falsehonesty.guitesting.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright 2017 (c) FalseHonesty
 */

public class SettingsItem extends ScrollingItem {
    private static final int START_Y = 120;

    private List<Expander> expanders;

    public SettingsItem() {
        expanders = new ArrayList<>();

        int height = 0;

        for (int i = 0; i < 5; i++) {
            List<ListItem> listItems = new ArrayList<>();

            for (int j = 0; j < 10; j++) {
                listItems.add(new ListItem("Setting " + j, null, 0xFF7096FF));
            }

            expanders.add(new Expander("Header " + i, 0xFFBFD0FF, listItems.toArray(new ListItem[listItems.size()])));
        }

        int scaledHeight = new ScaledResolution(mc).getScaledHeight();
        height -= scaledHeight - 80;
        this.maxScroll = height;
    }

    @Override
    public void handleMouseInput(int mouseX, int mouseY) {
        if (!collides(100, START_Y, 200,new ScaledResolution(mc).getScaledHeight() - 50, mouseX, mouseY)) {
            return;
        }

        super.handleMouseInput(mouseX, mouseY);
    }

    @Override
    public void drawItem(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc);

        drawStringWithBox("SETTINGS", 100, START_Y - 50, bigFr);

        int y = START_Y;

        for (Expander expander : expanders) {
            y += expander.drawItem(100, y - this.scrolled, mouseX, mouseY);
        }

//        int numHeaders = 0;
//        int numEnders = 0;
//        for (int i = 0; i < listItems.size(); i++) {
//            ListItem listItem = listItems.get(i);
//
//            int y = (15 * i) + (15 * numHeaders) + (20 * numEnders) + START_Y - this.scrolled;
//
//            if (y < START_Y - 5 || y > sr.getScaledHeight() - 50) continue;
//
//            listItem.render(100, y, mouseX, mouseY);
//
//            if (listItem.header) numHeaders++;
//            if (i < listItems.size() - 1 && listItems.get(i + 1).header) numEnders++;
//        }
    }

    @Override
    public void mouseClick(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc);

//        int numHeaders = 0;
//        int numEnders = 0;
//        for (int i = 0; i < listItems.size(); i++) {
//            ListItem listItem = listItems.get(i);
//
//            int y = (15 * i) + (15 * numHeaders) + (20 * numEnders) + START_Y - this.scrolled;
//
//            if (y < START_Y - 5 || y > sr.getScaledHeight() - 50) continue;
//
//            listItem.mouseClicked(100, y, mouseX, mouseY);
//
//            if (listItem.tit) numHeaders++;
//            if (i < listItems.size() - 1 && listItems.get(i + 1).header) numEnders++;
//        }
    }

    protected boolean collidesText(int y, String toDraw, int mouseX, int mouseY, CustomFont fr) {
        return collides(100, y, 100 + fr.getStringWidth(toDraw),
                y + fr.size, mouseX, mouseY);
    }

    protected int calcHeight() {
        int height = 0;

        for (Expander expander : expanders) {
            height += expander.getCurrentHeight();
        }

        return height;
    }

    class Expander {
        private String header;
        private List<ListItem> items;
        private boolean expanded;
        private int expandedHeight;
        private int color;
        private int jump;

        public Expander(String header, int color, ListItem... items) {
            this.header = header;
            this.items = Arrays.asList(items);
            this.expanded = true;
            this.expandedHeight = 0;
            this.color = color;

            for (ListItem item : this.items) {
                expandedHeight += item.getHeight();
            }
        }

        public int drawItem(int x, int y, int mouseX, int mouseY) {
            bigFr.renderStringWithShadow(this.header, x, y, this.color);
            int initialY = y;
            y += bigFr.size + 5;

            jump = expanded ? jump + 5 : jump - 5;
            jump = clamp(jump, 1, 100);

            if (this.expanded) {
                for (ListItem item :  this.items) {
                    item.render(x, y, mouseX, mouseY);

                    y += item.getHeight();

                    if (y - initialY > getCurrentHeight()) break;
                }
            }

            return y;
        }

        public void mouseClick(int x, int y, int mouseX, int mouseY) {
            if (this.expanded) {
                if (collidesText(y, header, mouseX, mouseY, bigFr)) {
                    this.setExpanded(false);
                    return;
                }

                int initialY = y;
                y += bigFr.size + 5;

                for (ListItem item :  this.items) {
                    item.mouseClicked(x, y, mouseX, mouseY);

                    y += item.getHeight();

                    if (y - initialY > getCurrentHeight()) break;
                }
            } else {
                if (collidesText(y, header, mouseX, mouseY, bigFr)) {
                    this.setExpanded(true);
                }
            }
        }

        public void setExpanded(boolean expanded) {
            this.expanded = expanded;
        }

        public int getCurrentHeight() {
            return easeOut(bigFr.size, expandedHeight, jump);
        }
    }

    class ListItem {
        private String title;
        private GuiScreen screenToOpen;
        private int color;
        private int jump;

        public ListItem(String title, GuiScreen screenToOpen, int color) {
            this.title = title;
            this.screenToOpen = screenToOpen;
            this.color = color;
            this.jump = 5;
        }

        protected void render(int x, int y, int mouseX, int mouseY) {
            int offset = GuiItem.easeIn(5, 20, (double) jump / (double) 100);
            int color = GuiItem.easeOut(0xFFFFFFFF, this.color, (double) jump / (double) 100);

            boolean hovering = collidesText(y, title, mouseX - offset, mouseY, medFr);

            jump = hovering ? jump + 5 : jump - 5;
            jump = clamp(jump, 1, 100);

            medFr.renderStringWithShadow(title, x + offset, y, color);
        }

        protected void mouseClicked(int x, int y, int mouseX, int mouseY) {
            if (collidesText(y, title, mouseX, mouseY, medFr) && screenToOpen != null) {
                mc.displayGuiScreen(screenToOpen);
            }
        }

        public int getHeight() {
            return medFr.size + 2;
        }
    }
}
