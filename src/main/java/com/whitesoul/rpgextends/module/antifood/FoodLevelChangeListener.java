package com.whitesoul.rpgextends.module.antifood;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {
    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}
