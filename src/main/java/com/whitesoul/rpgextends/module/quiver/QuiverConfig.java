package com.whitesoul.rpgextends.module.quiver;

import com.whitesoul.rpgextends.RPGExtends;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class QuiverConfig {
    private static File file;
    private static FileConfiguration config;
    public static void initConfig(){
        // 创建一个文件
        file = new File(RPGExtends.INSTANCE.getDataFolder(), "quiver.yml");
        config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()){
            RPGExtends.INSTANCE.saveResource("quiver.yml",false);
        }
    }
    public static FileConfiguration getConfig(){
        return config;
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}
