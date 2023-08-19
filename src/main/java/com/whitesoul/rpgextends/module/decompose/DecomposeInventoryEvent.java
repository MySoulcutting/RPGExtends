package com.whitesoul.rpgextends.module.decompose;

import com.whitesoul.rpgextends.util.InvClose;
import com.whitesoul.rpgextends.util.Logger;
import com.whitesoul.rpgextends.util.NiItem;
import me.yic.xconomy.api.XConomyAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;


import java.math.BigDecimal;
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
                // 遍历物品
                for (int i = 9; i < 27; i++) {
                    // 遍历物品名字
                    for (String key : DecomposeConfig.getConfig().getConfigurationSection("Item").getKeys(false)) {
                        if (inv.getItem(i) != null && inv.getItem(i).getItemMeta().hasDisplayName() && inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(DecomposeConfig.getConfig().getString("Item." + key + ".ItemName"))) {
                            // 判断是否需要金币分解
                            if (DecomposeConfig.getConfig().getBoolean("Item." + key + ".MoneyEnable")) {
                                XConomyAPI xConomyAPI = new XConomyAPI();
                                BigDecimal bigDecimal = new BigDecimal(DecomposeConfig.getConfig().getInt("Item." + key + ".NeedMoney"));
                                // 金币是否足够
                                if (xConomyAPI.getPlayerData(uuid).getBalance().intValue() >= bigDecimal.intValue()) {
                                    xConomyAPI.changePlayerBalance(uuid, name, bigDecimal, false);
                                } else {
                                    player.sendMessage("§c§l金币不足！无法分解！");
                                    return;
                                }
                            }
                            // 分解成功几率
                            int min = 1; // 定义随机数的最小值
                            int max = 101; // 定义随机数的最大值
                            // 1-100随机数
                            int s = (int) (min + (Math.random() * (max - min)));
                            Logger.debug("§e随机数: " + s);
                            // 是否启动成功几率
                                if (s <= DecomposeConfig.getConfig().getInt("Item." + key + ".SuccessRate")) {
                                    // 获取物品数量
                                    int amount = inv.getItem(i).getAmount();
                                    // 清除物品
                                    inv.setItem(i, null);
                                    // 给予物品
                                    for (String item : DecomposeConfig.getConfig().getStringList("Item." + key + ".Items")) {
                                        String[] itemSplit = item.split(":");
                                        String[] itemAmount = itemSplit[1].split("-");
                                        int minAmount = Integer.parseInt(itemAmount[0]);
                                        int maxAmount = Integer.parseInt(itemAmount[1]);
                                        int itemAmounts = (int) (minAmount + (Math.random() * (maxAmount - minAmount)));
                                        NiItem.give(player,itemSplit[0], itemAmounts * amount);
                                    }
                                } else {
                                    // 清除物品
                                    inv.setItem(i, null);
                                    player.sendMessage("§c§l分解失败！");
                                }
                            }

                        }
                    }
                }
                long endTime = System.currentTimeMillis();
                Logger.debug("§e物品分解耗时: " + (endTime - startTime) + "ms");
            }
        }
    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof DecomposeHolder) {
            InvClose.close(event);
        }
    }
}
