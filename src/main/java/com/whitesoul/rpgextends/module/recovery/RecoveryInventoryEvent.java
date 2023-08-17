package com.whitesoul.rpgextends.module.recovery;

import com.whitesoul.rpgextends.util.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryEvent implements Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        if (inv == null){
            return;
        }
        if (inv.getHolder() instanceof RecoveryHolder){
            if (event.getSlot() >= 9){
                event.setCancelled(true);
                return;
            }
            Logger.debug("§e物品回收界面点击事件触发！");
        }
    }
}
