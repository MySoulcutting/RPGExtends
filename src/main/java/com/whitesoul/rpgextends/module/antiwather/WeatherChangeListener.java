package com.whitesoul.rpgextends.module.antiwather;

import com.whitesoul.rpgextends.RPGExtends;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {
    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        for (String world : RPGExtends.INSTANCE.getConfig().getStringList("AntiWeatherWorlds")) {
            if (event.getWorld().getName().equalsIgnoreCase(world)) {
                event.setCancelled(true);
            }
        }
    }
}
