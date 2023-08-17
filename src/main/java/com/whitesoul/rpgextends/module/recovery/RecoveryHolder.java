package com.whitesoul.rpgextends.module.recovery;

import com.whitesoul.rpgextends.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;


public class RecoveryHolder implements InventoryHolder {
    private static Inventory inventory;
    @Override
    public Inventory getInventory() {
        return null;
    }
    public static void createInventory(){
        ItemBuilder button = new ItemBuilder(Material.STAINED_GLASS_PANE,1,"§c§l点击回收","§e该操作不可逆！");
        ItemBuilder glass = new ItemBuilder(Material.STAINED_GLASS_PANE,1,"§a§l回收物品 ↓");
        inventory = Bukkit.createInventory(new RecoveryHolder(),27, "§c§l物品回收");
        inventory.setItem(4,button);
        int[] slots = {0,1,2,3,5,6,7,8};
        for (int slot : slots) {
            inventory.setItem(slot,glass);
        }
    }
    public static void openInventory(Player player){
        createInventory();
        player.openInventory(inventory);
    }
    public static InventoryHolder getHolder(){
        return inventory.getHolder();
    }
}
