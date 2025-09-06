package com.ren.rensMinecraftMathLibrary;

import org.bukkit.plugin.java.JavaPlugin;

public final class RensMinecraftMathLibrary extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("RensMinecraftMathLibrary " + getDescription().getVersion() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RensMinecraftMathLibrary has been disabled!");
    }
}
