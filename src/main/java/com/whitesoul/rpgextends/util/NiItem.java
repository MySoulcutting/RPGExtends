package com.whitesoul.rpgextends.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NiItem {
    public static void give(Player player, String ItemName, int amount){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"ni give " + player.getName() + " " + ItemName + " " + amount);
    }
}
