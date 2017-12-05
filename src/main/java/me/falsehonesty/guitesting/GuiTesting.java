package me.falsehonesty.guitesting;

import me.falsehonesty.guitesting.gui.CustomFont;
import me.falsehonesty.guitesting.gui.MainScreen;
import me.falsehonesty.guitesting.gui.NahrFont;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

import java.io.InputStream;

/**
 * Copyright 2017 (c) FalseHonesty
 */

@Mod(modid = "guitesting", name = "GuiTesting", version = "SNAPSHOT")
public class GuiTesting {
    public static CustomFont fr;
    public static CustomFont medFr;
    public static CustomFont bigFr;
    public static final int smallFontSize = 18;
    public static final int mediumFontSize = 26;
    public static final int largeFontSize = 36;

    private KeyBinding menuKey1;
    private KeyBinding menuKey2;

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        ClientRegistry.registerKeyBinding(
                menuKey1 = new KeyBinding("Open menu 1", Keyboard.KEY_L, "Podcrash Menu1")
        );

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {

    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (menuKey1.isPressed() && Minecraft.getMinecraft().currentScreen == null) {
            Minecraft.getMinecraft().displayGuiScreen(new MainScreen());
        }
    }
}
