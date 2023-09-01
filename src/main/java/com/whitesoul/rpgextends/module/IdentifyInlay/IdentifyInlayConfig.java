package com.whitesoul.rpgextends.module.IdentifyInlay;

import com.whitesoul.rpgextends.RPGExtends;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class IdentifyInlayConfig {
    private static File file;
    private static FileConfiguration config;
    public static void initConfig(){
        // 创建一个文件
        file = new File(RPGExtends.INSTANCE.getDataFolder(), "identifyinlay.yml");
        config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()){
            RPGExtends.INSTANCE.saveResource("identifyinlay.yml",false);
        }
    }
    public static FileConfiguration getConfig(){
        return config;
    }
    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }
}
