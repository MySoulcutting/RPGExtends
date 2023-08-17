package com.whitesoul.rpgextends;

import com.whitesoul.rpgextends.module.antidrop.PlayerDropItemListener;
import com.whitesoul.rpgextends.module.antifkey.PlayerSwapHandItemsListener;
import com.whitesoul.rpgextends.module.recovery.RecoveryInventoryEvent;
import com.whitesoul.rpgextends.module.recovery.RecoveryCommand;
import com.whitesoul.rpgextends.util.Logger;
import org.bukkit.plugin.java.JavaPlugin;


public final class RPGExtends extends JavaPlugin {
    public static RPGExtends INSTANCE;
    @Override
    public void onEnable() {
        INSTANCE = this;
        saveDefaultConfig();
        Logger.info("§a插件作者: §fWhiteSoul");
        Logger.info("§c RPGExtends已加载！");
        Logger.info("§e 当前模块:");
        Logger.info("§6 禁用F键使用 §a√");
        Logger.info("§6 禁用物品丢弃 §a√");
        Logger.info("§6 物品回收 §a√");
        Logger.info("§6 物品分解 §a√");
        // 禁止F键使用注册
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsListener(),this);
        // 禁止丢弃物品注册
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(),this);
        // 物品回收系统注册
        getServer().getPluginManager().registerEvents(new RecoveryInventoryEvent(),this);
        getCommand("rpgex").setExecutor(new RecoveryCommand());
    }

    @Override
    public void onDisable() {

    }
}
