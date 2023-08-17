package com.whitesoul.rpgextends.module.decompose;

import com.whitesoul.rpgextends.util.Logger;
import com.whitesoul.rpgextends.util.NiItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class DecomposeInventoryEvent implements Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        long startTime = System.currentTimeMillis();
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        String name = player.getName();
        Inventory inv = event.getClickedInventory();
        if (inv == null) {
            return;
        }
        if (event.getClickedInventory().getHolder() instanceof DecomposeHolder) {
            // 禁止点击物品
            for (int i = 0; i < 9; i++) {
                if (event.getSlot() == i) {
                    event.setCancelled(true);
                }
            }
            // 点击按钮
            if (event.getSlot() == 4) {
                for (int i = 9; i < 27; i++) {
                    // 判断物品名字
                    if (inv.getItem(i) != null && inv.getItem(i).getItemMeta().hasDisplayName() && inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("test")){
                        // 获取物品数量
                        int amount = inv.getItem(i).getAmount();
                        // 清除物品
                        inv.setItem(i, null);
                        // 给予物品
                        NiItem.give(player,"蜗牛壳",amount);
                    }
                }
                long endTime = System.currentTimeMillis();
                Logger.debug("§e物品分解耗时: " + (endTime - startTime) + "ms");
            }
        }
    }
}
