package me.falsehonesty.guitesting;

import me.falsehonesty.guitesting.gui.MainScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

/**
 * Copyright 2017 (c) FalseHonesty
 */

@Mod(modid = "guitesting", name = "GuiTesting", version = "SNAPSHOT")
public class GuiTesting {

    private KeyBinding menuKey1;
    private KeyBinding menuKey2;

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        ClientRegistry.registerKeyBinding(
                menuKey1 = new KeyBinding("Open menu 1", Keyboard.KEY_L, "Podcrash Menu1")
        );

//        ClientRegistry.registerKeyBinding(
//                menuKey2 = new KeyBinding("Open menu 2", Keyboard.KEY_K, "Podcrash Menu2")
//        );

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onTick(TickEvent e) {
        if (menuKey1.isPressed() && Minecraft.getMinecraft().currentScreen == null) {
            Minecraft.getMinecraft().displayGuiScreen(new MainScreen());
        }

//        if (menuKey2.isPressed() && Minecraft.getMinecraft().currentScreen == null) {
//            Minecraft.getMinecraft().displayGuiScreen(new RadialScreen());
//        }
    }
}
