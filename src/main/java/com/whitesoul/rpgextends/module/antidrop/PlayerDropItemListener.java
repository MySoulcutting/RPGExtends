package com.whitesoul.rpgextends.module.antidrop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {
    @EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent event){
        if (event.getPlayer().isOp()){
            return;
        }
        event.setCancelled(true);
        event.getPlayer().sendMessage("§c§l无法丢弃物品！");
    }
}
