package com.whitesoul.rpgextends.module.decompose;

import com.whitesoul.rpgextends.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class DecomposeHolder implements InventoryHolder {
    private static Inventory inventory;
    @Override
    public Inventory getInventory() {
        return inventory;
    }
    public static void createInventory(){
        ItemBuilder button = new ItemBuilder(Material.STAINED_GLASS_PANE,1,"§c§l点击分解","§e该操作不可逆！");
        ItemBuilder glass = new ItemBuilder(Material.STAINED_GLASS_PANE,1,"§a§l分解物品 ↓");
        inventory = Bukkit.createInventory(new DecomposeHolder(),27, "§c§l物品分解");
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
