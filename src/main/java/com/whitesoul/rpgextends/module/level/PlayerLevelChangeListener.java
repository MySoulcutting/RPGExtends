package com.whitesoul.rpgextends.module.level;

import com.whitesoul.rpgextends.util.Logger;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerLevelChangeListener implements Listener {
    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent event) {
        for (String level : LevelConfig.getConfig().getConfigurationSection("Level").getKeys(false)) {
            if (event.getNewLevel() == LevelConfig.getConfig().getInt("Level." + level + ".level")){
                for (String command : LevelConfig.getConfig().getStringList("Level." + level + ".commands")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),PlaceholderAPI.setPlaceholders(event.getPlayer(), command.replace("&","§")));
                    Logger.debug("等级已执行后台命令");
                }
            }
        }
    }
}
