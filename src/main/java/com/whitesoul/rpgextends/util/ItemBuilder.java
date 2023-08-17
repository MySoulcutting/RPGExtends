package com.whitesoul.rpgextends.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;

public class ItemBuilder extends ItemStack {
    public ItemBuilder(Material material, int amount, String name, String... lore) {
        super(material, amount);
        ItemMeta itemMeta = this.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(Arrays.asList(lore));
        this.setItemMeta(itemMeta);
    }

}
