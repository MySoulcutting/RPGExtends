package com.whitesoul.rpgextends.module.selectjob;

import com.whitesoul.rpgextends.module.decompose.DecomposeHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class SelectJobInventoryListener implements Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        if (inv == null) {
            return;
        }
        if (event.getClickedInventory().getHolder() instanceof SelectJobHolder) {
            // 禁止点击物品
            for (int i = 0; i < 8; i++) {
                if (event.getSlot() == i) {
                    event.setCancelled(true);
                }
            }
            // 点击按钮
            switch (event.getSlot()){
                case 1:
                    player.sendMessage("§a§l你选择成为一名战士！");
                    for (String cmd : SelectJobConfig.getConfig().getStringList("Job.战士.commands")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd.replace("%player%",player.getName()));
                    }
                    player.closeInventory();
                    break;
                case 4:
                    player.sendMessage("§a§l你选择成为一名弓箭手！");
                    for (String cmd : SelectJobConfig.getConfig().getStringList("Job.弓箭手.commands")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd.replace("%player%",player.getName()));
                    }                    player.closeInventory();
                    break;
                case 7:
                    player.sendMessage("§a§l你选择成为一名法师！");
                    for (String cmd : SelectJobConfig.getConfig().getStringList("Job.法师.commands")){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd.replace("%player%",player.getName()));
                    }
                    player.closeInventory();
                    break;
            }
            }
        }
    }
