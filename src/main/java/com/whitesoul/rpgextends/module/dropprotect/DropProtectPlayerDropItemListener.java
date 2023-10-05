package com.whitesoul.rpgextends.module.dropprotect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import java.util.HashMap;

public class DropProtectPlayerDropItemListener implements Listener {
    public static HashMap<String,Boolean> dropProtect = new HashMap<>();
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event){
        event.setCancelled(true);
        event.getPlayer().sendMessage("§c§l无法丢弃物品！");
    }
}
