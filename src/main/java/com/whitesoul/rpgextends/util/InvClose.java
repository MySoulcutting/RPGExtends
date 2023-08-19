package com.whitesoul.rpgextends.util;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class InvClose {
    public static void close(InventoryCloseEvent event) {
        Inventory inv = event.getInventory();
        for (int i = 9; i < 27; i++) {
            if (inv.getItem(i) != null) {
                event.getPlayer().getInventory().addItem(inv.getItem(i));
                if (event.getPlayer().getInventory().firstEmpty() == -1) {
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), inv.getItem(i));
                    event.getPlayer().sendMessage("§c§l背包已满，物品已掉落至地面！");
                }
            }
        }
    }
}
