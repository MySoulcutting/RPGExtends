package com.whitesoul.rpgextends.module.quiver;

import com.whitesoul.rpgextends.RPGExtends;
import com.whitesoul.rpgextends.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class QuiverPlayerInteractListener implements Listener {
    private Set<UUID> cooldownPlayers = new HashSet<>();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
            // 判断主手是否为弓
            if (mainHand != null && mainHand.getType().equals(Material.BOW)) {
                for (String key : QuiverConfig.getConfig().getConfigurationSection("Quiver").getKeys(false)) {
                    // 判断玩家副手是否有箭袋
                    if (offHand != null && offHand.hasItemMeta() && offHand.getItemMeta().hasDisplayName() && offHand.getItemMeta().getDisplayName().equalsIgnoreCase(QuiverConfig.getConfig().getString("Quiver." + key + ".Name"))) {
                    // 判断是否为左键
                      if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                        // 发射
                          if (!cooldownPlayers.contains(player.getUniqueId())) {
                              Logger.debug("§e玩家左键发射箭");
                              Arrow arrow = player.launchProjectile(Arrow.class);
                              arrow.setVelocity(arrow.getVelocity().multiply(QuiverConfig.getConfig().getInt("Quiver." + key + ".Power")));
                              arrow.setKnockbackStrength(QuiverConfig.getConfig().getInt("Quiver." + key + ".KnockBack"));
                              arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                              cooldownPlayers.add(player.getUniqueId());
                              Bukkit.getScheduler().runTaskLater(RPGExtends.INSTANCE, () -> {
                                  cooldownPlayers.remove(player.getUniqueId());
                              }, 20 * QuiverConfig.getConfig().getLong("Quiver." + key + ".Cooldown"));
                          } else {
                              player.sendMessage("§c§l弓箭还在冷却中！");
                          }
                    }
                } else {
                        player.sendMessage("§c§l你没有箭袋！,无法发射箭！");
                    }
            }
        }
    }
}
