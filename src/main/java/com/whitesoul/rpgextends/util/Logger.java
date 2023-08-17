package com.whitesoul.rpgextends.util;

import com.whitesoul.rpgextends.RPGExtends;
import org.bukkit.plugin.Plugin;

public class Logger {
    private static Plugin plugin = RPGExtends.INSTANCE;
    public static void info(String message) {
        plugin.getLogger().info("§f" + message);
    }
    public static void warning(String message) {
        plugin.getLogger().warning(message);
    }
    public static void error(String message) {
        plugin.getLogger().info("§c" + message);
    }
    public static void debug(String message) {
        if (plugin.getConfig().getBoolean("debug")) {
            info("§f[DEBUG] §e" + message);
        }
    }
}