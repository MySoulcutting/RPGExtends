package com.whitesoul.rpgextends.module.decompose;

import com.whitesoul.rpgextends.RPGExtends;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;
import java.util.Set;

public class DecomposeConfig {
    private static File file;
    private static FileConfiguration config;
    public static void initConfig(){
        // 创建一个文件
        file = new File(RPGExtends.INSTANCE.getDataFolder(), "decompose.yml");
        config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()){
            RPGExtends.INSTANCE.saveResource("decompose.yml",false);
        }
    }
    public static FileConfiguration getConfig(){
        return config;
    }
    public List getList(String key){
        return (List) getConfig().get(key);
    }
    public Set<String> keys(String predefine){
        return getConfig().getConfigurationSection(predefine).getKeys(false);
    }
    public Set<String> keys(){
        return getConfig().getKeys(false);
    }
}
