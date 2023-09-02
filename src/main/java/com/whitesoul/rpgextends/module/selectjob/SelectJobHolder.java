package com.whitesoul.rpgextends.module.selectjob;

import com.whitesoul.rpgextends.module.decompose.DecomposeHolder;
import com.whitesoul.rpgextends.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SelectJobHolder implements InventoryHolder{
    private static Inventory inventory;
    @Override
    public Inventory getInventory() {
        return inventory;
    }
    public static void createInventory(){
        ItemBuilder zhanshi = new ItemBuilder(Material.PAPER,1,"§a§l战士","§e点击选择战士职业");
        ItemBuilder gongjianshou = new ItemBuilder(Material.PAPER,1,"§a§l弓箭手","§e点击选择弓箭手职业");
        ItemBuilder fashi = new ItemBuilder(Material.PAPER,1,"§a§l法师","§e点击选择法师职业");
        inventory = Bukkit.createInventory(new SelectJobHolder(),9, "§c§l物品分解");
        inventory.setItem(1,zhanshi);
        inventory.setItem(4,gongjianshou);
        inventory.setItem(7,fashi);
    }
    public static void openInventory(Player player){
        createInventory();
        player.openInventory(inventory);
    }
    public static InventoryHolder getHolder(){
        return inventory.getHolder();
    }
}
