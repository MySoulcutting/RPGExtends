package com.whitesoul.rpgextends;

import com.whitesoul.rpgextends.command.MainCommand;
import com.whitesoul.rpgextends.command.MainCommandTab;
import com.whitesoul.rpgextends.module.antidrop.PlayerDropItemListener;
import com.whitesoul.rpgextends.module.antifkey.PlayerSwapHandItemsListener;
import com.whitesoul.rpgextends.module.antiwather.WeatherChangeListener;
import com.whitesoul.rpgextends.module.decompose.DecomposeConfig;
import com.whitesoul.rpgextends.module.decompose.DecomposeInventoryListener;
import com.whitesoul.rpgextends.module.level.LevelConfig;
import com.whitesoul.rpgextends.module.level.PlayerLevelChangeListener;
import com.whitesoul.rpgextends.module.recovery.RecoveryInventoryListener;
import com.whitesoul.rpgextends.util.Logger;
import org.bukkit.plugin.java.JavaPlugin;


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
        Logger.info("§6 自定义重生点 §c×");
        Logger.info("§6 等级指令 §a√");
        // 指令注册
        getCommand("rpgex").setExecutor(new MainCommand());
        getCommand("rpgex").setTabCompleter(new MainCommandTab());
        // 禁止F键使用注册
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsListener(),this);
        // 禁止丢弃物品注册
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(),this);
        // 禁止天气切换注册
        getServer().getPluginManager().registerEvents(new WeatherChangeListener(),this);
        // 物品回收系统注册
        getServer().getPluginManager().registerEvents(new RecoveryInventoryListener(),this);
        // 物品分解系统注册
        DecomposeConfig.initConfig();
        getServer().getPluginManager().registerEvents(new DecomposeInventoryListener(),this);
        // 等级称号系统注册
        LevelConfig.initConfig();
        getServer().getPluginManager().registerEvents(new PlayerLevelChangeListener(),this);
        // 耗时统计
        long endTime = System.currentTimeMillis();
        Logger.info("§a加载耗时: §f" + (endTime - startTime) + "ms");
    }
    @Override
    public void onDisable() {
    }
}
