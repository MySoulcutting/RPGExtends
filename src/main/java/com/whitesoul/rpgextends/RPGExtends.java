package com.whitesoul.rpgextends;

import com.whitesoul.rpgextends.command.MainCommand;
import com.whitesoul.rpgextends.command.MainCommandTab;
import com.whitesoul.rpgextends.command.SpawnPointsCommand;
import com.whitesoul.rpgextends.command.SpawnPointsCommandTab;
import com.whitesoul.rpgextends.module.IdentifyInlay.IdentifyInlayConfig;
import com.whitesoul.rpgextends.module.IdentifyInlay.IdentifyInlayInventoryListener;
import com.whitesoul.rpgextends.module.antidrop.PlayerDropItemListener;
import com.whitesoul.rpgextends.module.antifkey.PlayerSwapHandItemsListener;
import com.whitesoul.rpgextends.module.antifood.FoodLevelChangeListener;
import com.whitesoul.rpgextends.module.antiwather.WeatherChangeListener;
import com.whitesoul.rpgextends.module.decompose.DecomposeConfig;
import com.whitesoul.rpgextends.module.decompose.DecomposeInventoryListener;
import com.whitesoul.rpgextends.module.level.LevelConfig;
import com.whitesoul.rpgextends.module.level.PlayerLevelChangeListener;
import com.whitesoul.rpgextends.module.recovery.RecoveryInventoryListener;
import com.whitesoul.rpgextends.module.selectjob.SelectJobConfig;
import com.whitesoul.rpgextends.module.selectjob.SelectJobInventoryListener;
import com.whitesoul.rpgextends.module.spawnpoints.PlayerJoinListener;
import com.whitesoul.rpgextends.module.spawnpoints.PlayerQuitListener;
import com.whitesoul.rpgextends.module.spawnpoints.PlayerRespawnListener;
import com.whitesoul.rpgextends.module.spawnpoints.SpawnPointsConfig;
import com.whitesoul.rpgextends.util.Logger;
import com.whitesoul.rpgextends.util.Metrics;
import com.whitesoul.soulsql.database.Mysql;
import com.whitesoul.soulsql.database.SQL;
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
        Logger.info("§6 禁止饥饿度使用 §a√");
        Logger.info("§6 物品回收 §a√");
        Logger.info("§6 物品分解 §a√");
        Logger.info("§6 自定义重生点 §a√×");
        Logger.info("§6 等级指令 §a√");
        Logger.info("§6 镶嵌识别 §a√");
        Logger.info("§6 职业选择 §a√");
        // 统计
        new Metrics(this, 19703);
//        // 数据库连接
//        Mysql.createConfig("mysql",this);
//        // 数据库表创建
//        SQL.createTable("spawnpoints",new String[]{"uuid","name","spawnpointname"},new String[]{"varchar(255)","varchar(255)","varchar(255)"},new String[]{"not null","not null","not null"});
        // 指令注册
        getCommand("rpgex").setExecutor(new MainCommand());
        getCommand("rpgex").setTabCompleter(new MainCommandTab());
        getCommand("spawn").setExecutor(new SpawnPointsCommand());
        getCommand("spawn").setExecutor(new SpawnPointsCommandTab());
        // 禁止F键使用注册
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsListener(),this);
        // 禁止丢弃物品注册
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(),this);
        // 禁止天气切换注册
        getServer().getPluginManager().registerEvents(new WeatherChangeListener(),this);
        // 禁止饥饿度使用注册
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(),this);
        // 物品回收系统注册
        getServer().getPluginManager().registerEvents(new RecoveryInventoryListener(),this);
        // 物品分解系统注册
        DecomposeConfig.initConfig();
        getServer().getPluginManager().registerEvents(new DecomposeInventoryListener(),this);
        // 等级称号系统注册
        LevelConfig.initConfig();
        getServer().getPluginManager().registerEvents(new PlayerLevelChangeListener(),this);
        // 重生点系统注册
        SpawnPointsConfig.initConfig();
        getServer().getPluginManager().registerEvents(new PlayerRespawnListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(),this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(),this);
        // 镶嵌识别注册
        IdentifyInlayConfig.initConfig();
        getServer().getPluginManager().registerEvents(new IdentifyInlayInventoryListener(),this);
        // 职业选择注册
        SelectJobConfig.initConfig();
        getServer().getPluginManager().registerEvents(new SelectJobInventoryListener(),this);
        // 耗时统计
        long endTime = System.currentTimeMillis();
        Logger.info("§a加载耗时: §f" + (endTime - startTime) + "ms");
    }
    @Override
    public void onDisable() {
    }
}
