package com.example;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Mod(modid = ForgeExampleMod.MODID, name = "Forge Example Mod", version = "1.0.0")
public class ForgeExampleMod {
    public static final String MODID = "forge-example-mod";

    public static final Logger LOGGER = LogManager.getLogManager().getLogger(MODID);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        LOGGER.info("Hello from Minecraft 1.7.2!");
    }
}
