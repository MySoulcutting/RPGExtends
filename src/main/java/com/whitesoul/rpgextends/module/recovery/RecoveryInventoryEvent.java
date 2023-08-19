package com.whitesoul.rpgextends.module.recovery;

import com.whitesoul.rpgextends.RPGExtends;
import com.whitesoul.rpgextends.module.decompose.DecomposeInventoryEvent;
import com.whitesoul.rpgextends.util.InvClose;
import com.whitesoul.rpgextends.util.Logger;
import me.yic.xconomy.api.XConomyAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecoveryInventoryEvent implements Listener {
    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        long startTime = System.currentTimeMillis();
        ConcurrentHashMap<UUID, Integer> data = new ConcurrentHashMap<>();
        Player player = (Player) event.getWhoClicked();
        UUID uuid = player.getUniqueId();
        String name = player.getName();
        Inventory inv = event.getClickedInventory();
        if (inv == null) {
            return;
        }
        if (event.getClickedInventory().getHolder() instanceof RecoveryHolder) {
            // 禁止点击物品
            for (int i = 0; i < 9; i++) {
                if (event.getSlot() == i) {
                    event.setCancelled(true);
                }
            }
            // 点击按钮
            if (event.getSlot() == 4) {
                for (int i = 9; i < 27; i++) {
                    // 回收物品价格 正则表达式
                    if (inv.getItem(i) != null && inv.getItem(i).getItemMeta().hasLore() && inv.getItem(i).getItemMeta().getLore().size() > 0) {
                        // 获取物品最后一行lore(回收价格
                        String lore = inv.getItem(i).getItemMeta().getLore().get(inv.getItem(i).getItemMeta().getLore().size() - 1);
                        String pattern = "§e回收价格: §f(\\d+) §e金币";
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(lore);
                        // 判断是否为回收物品
                        if (m.find()) {
                            // 回收物品数量
                            int amount = inv.getItem(i).getAmount();
                            // 回收物品价格
                            int slotPrice = Integer.parseInt(m.group(1)) * amount;
                            // 汇总所有格子价格
                            data.put(uuid, data.getOrDefault(uuid, 0) + slotPrice);
                            // 清除物品
                            inv.setItem(i, null);
                        }
                    }
                }
                try {
                    XConomyAPI xConomyAPI = new XConomyAPI();
                    BigDecimal price = new BigDecimal(data.get(uuid));
                    xConomyAPI.changePlayerBalance(uuid, name, price, true);
                    data.remove(uuid);
                    player.sendMessage("§a§l你回收获得了" + price + "金币！");
                } catch (Exception ignored) {
                }
                // 计算耗时
                long endTime = System.currentTimeMillis();
                Logger.debug("§e物品回收耗时: " + (endTime - startTime) + "ms");
                Logger.debug("§e物品回收界面点击按钮触发");

            }
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        if (event.getInventory().getHolder() instanceof RecoveryHolder) {
            InvClose.close(event);
        }
    }
}
