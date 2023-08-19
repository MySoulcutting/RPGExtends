package com.whitesoul.rpgextends;

import com.whitesoul.rpgextends.module.antidrop.PlayerDropItemListener;
import com.whitesoul.rpgextends.module.antifkey.PlayerSwapHandItemsListener;
import com.whitesoul.rpgextends.module.antiwather.WeatherChangeListener;
import com.whitesoul.rpgextends.module.decompose.DecomposeCommand;
import com.whitesoul.rpgextends.module.decompose.DecomposeConfig;
import com.whitesoul.rpgextends.module.decompose.DecomposeInventoryEvent;
import com.whitesoul.rpgextends.module.recovery.RecoveryInventoryEvent;
import com.whitesoul.rpgextends.module.recovery.RecoveryCommand;
import com.whitesoul.rpgextends.util.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


public final class RPGExtends extends JavaPlugin {
    public static RPGExtends INSTANCE;
    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        INSTANCE = this;
        saveDefaultConfig();
        Logger.info("§a插件作者: §fWhiteSoul");
        Logger.info("§c RPGExtends已加载！");
        Logger.info("§e 当前模块:");
        Logger.info("§6 禁止F键使用 §a√");
        Logger.info("§6 禁止物品丢弃 §a√");
        Logger.info("§6 禁止天气切换 §a√");
        Logger.info("§6 物品回收 §a√");
        Logger.info("§6 物品分解 §a√");
        // 禁止F键使用注册
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsListener(),this);
        // 禁止丢弃物品注册
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(),this);
        // 禁止天气切换注册
        getServer().getPluginManager().registerEvents(new WeatherChangeListener(),this);
        // 物品回收系统注册
        getServer().getPluginManager().registerEvents(new RecoveryInventoryEvent(),this);
        getCommand("rpgex").setExecutor(new RecoveryCommand());
        // 物品分解系统注册
        DecomposeConfig.initConfig();
        getServer().getPluginManager().registerEvents(new DecomposeInventoryEvent(),this);
        getCommand("rpgex").setExecutor(new DecomposeCommand());
        // 耗时统计
        long endTime = System.currentTimeMillis();
        Logger.info("§a加载耗时: §f" + (endTime - startTime) + "ms");
    }
    @Override
    public void onDisable() {
    }
}
