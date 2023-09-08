package com.whitesoul.rpgextends.module.autorespawn;

import com.whitesoul.rpgextends.RPGExtends;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class AutoRespawnListener implements Listener {
    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event){
        Bukkit.getScheduler().scheduleSyncDelayedTask(RPGExtends.INSTANCE, () -> {
            event.getEntity().spigot().respawn();
        }, 4L);
    }
}
