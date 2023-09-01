package com.whitesoul.rpgextends.module.IdentifyInlay;

import com.whitesoul.rpgextends.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class IdentifyInlayInventoryListener implements Listener {
    @EventHandler
    public void onInventoryClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            // 获取方块位置
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && player.isOp() && event.getItem().getType().equals(Material.STICK)) {
                // 获取玩家右键方块的位置
                String world = event.getClickedBlock().getWorld().getName();
                double x = event.getClickedBlock().getLocation().getX();
                double y = event.getClickedBlock().getLocation().getY();
                double z = event.getClickedBlock().getLocation().getZ();
                player.sendMessage("方块当前位置: 世界: " + world + " X: " + x + " Y: " + y + " Z: " + z);
            }
        }catch (Exception ignored){
        }
        // 判断镶嵌方块位置
        for (String block : IdentifyInlayConfig.getConfig().getConfigurationSection("Block").getKeys(false)) {
            World world = Bukkit.getWorld(IdentifyInlayConfig.getConfig().getString("Block." + block + ".world"));
            double x = IdentifyInlayConfig.getConfig().getDouble("Block." + block + ".x");
            double y = IdentifyInlayConfig.getConfig().getDouble("Block." + block + ".y");
            double z = IdentifyInlayConfig.getConfig().getDouble("Block." + block + ".z");
            Location location = new Location(world, x, y, z);
            // 判断玩家右键的方块
            if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !event.getClickedBlock().getLocation().equals(location)) {
                Logger.debug("§e玩家右键的方块: " + event.getClickedBlock().getLocation() + "不是镶嵌台");
                return;
            }
        }
        // 判断镶嵌物品名字
        for (String item : IdentifyInlayConfig.getConfig().getConfigurationSection("Item").getKeys(false)) {
            if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(IdentifyInlayConfig.getConfig().getString("Item." + item + ".ItemName"))) {
                Logger.debug("§e玩家右键的物品: " + event.getItem().getItemMeta().getDisplayName() + "是镶嵌物品");
                event.getPlayer().sendMessage("§e你打开了镶嵌界面");
                String gui = IdentifyInlayConfig.getConfig().getString("Item." + item + ".InlayGUIName");
                Bukkit.dispatchCommand(player,"ie open " + gui + " 1");
            } else {
                Logger.debug("§e玩家右键的物品: " + event.getItem().getItemMeta().getDisplayName() + "不是镶嵌物品");
            }
        }
    }
}
