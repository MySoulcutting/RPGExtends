package com.whitesoul.rpgextends;

import com.whitesoul.rpgextends.command.MainCommand;
import com.whitesoul.rpgextends.command.MainCommandTab;
import com.whitesoul.rpgextends.command.SpawnPointsCommand;
import com.whitesoul.rpgextends.command.SpawnPointsCommandTab;
import com.whitesoul.rpgextends.module.IdentifyInlay.IdentifyInlayConfig;
import com.whitesoul.rpgextends.module.IdentifyInlay.IdentifyInlayInventoryListener;
import com.whitesoul.rpgextends.module.dropprotect.DropProtectPlayerDropItemListener;
import com.whitesoul.rpgextends.module.antifkey.PlayerSwapHandItemsListener;
import com.whitesoul.rpgextends.module.antifood.FoodLevelChangeListener;
import com.whitesoul.rpgextends.module.antiwather.WeatherChangeListener;
import com.whitesoul.rpgextends.module.autorespawn.AutoRespawnListener;
import com.whitesoul.rpgextends.module.decompose.DecomposeConfig;
import com.whitesoul.rpgextends.module.decompose.DecomposeInventoryListener;
import com.whitesoul.rpgextends.module.level.LevelConfig;
import com.whitesoul.rpgextends.module.level.PlayerLevelChangeListener;
import com.whitesoul.rpgextends.module.quiver.QuiverConfig;
import com.whitesoul.rpgextends.module.quiver.QuiverPlayerInteractListener;
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
        Logger.info("§e 当前版本: §f" + getDescription().getVersion());
        Logger.info("§e 当前服务器版本: §f" + getServer().getVersion());
        Logger.info("§e 当前模块:");
        // 统计
        new Metrics(this, 19703);
        // 指令注册
        getCommand("rpgex").setExecutor(new MainCommand());
        getCommand("rpgex").setTabCompleter(new MainCommandTab());
        // 禁止F键使用注册
        if (getConfig().getBoolean("Module.AntiFKey")) {
            getServer().getPluginManager().registerEvents(new PlayerSwapHandItemsListener(),this);
            Logger.info("§6 禁止F键使用 §a已启用");
        } else {
            Logger.info("§6 禁止F键使用 §c未启用");
        }
        // 丢弃保护注册
        if (getConfig().getBoolean("Module.DropProtect")) {
            getServer().getPluginManager().registerEvents(new DropProtectPlayerDropItemListener(),this);
            Logger.info("§6 丢弃保护 §a已启用");
        } else {
            Logger.info("§6 丢弃保护 §c未启用");
        }
        // 禁止天气切换注册
        if (getConfig().getBoolean("Module.AntiWeather")) {
            getServer().getPluginManager().registerEvents(new WeatherChangeListener(),this);
            Logger.info("§6 禁止天气切换 §a已启用");
        } else {
            Logger.info("§6 禁止天气切换 §c未启用");
        }
        // 禁止饥饿度使用注册
        if (getConfig().getBoolean("Module.AntiFood")) {
            getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(),this);
            Logger.info("§6 禁止饥饿度使用 §a已启用");
        } else {
            Logger.info("§6 禁止饥饿度使用 §c未启用");
        }
        // 物品回收系统注册
        if (getConfig().getBoolean("Module.Recovery")) {
            getServer().getPluginManager().registerEvents(new RecoveryInventoryListener(), this);
            Logger.info("§6 物品回收 §a已启用");
        } else {
            Logger.info("§6 物品回收 §c未启用");
        }
        // 物品分解系统注册
        if (getConfig().getBoolean("Module.Decompose")) {
            DecomposeConfig.initConfig();
            getServer().getPluginManager().registerEvents(new DecomposeInventoryListener(), this);
            Logger.info("§6 物品分解 §a已启用");
        } else {
            Logger.info("§6 物品分解 §c未启用");
        }
        // 等级称号系统注册
        if (getConfig().getBoolean("Module.Level")) {
            LevelConfig.initConfig();
            getServer().getPluginManager().registerEvents(new PlayerLevelChangeListener(), this);
            Logger.info("§6 原版等级指令 §a已启用");
        } else {
            Logger.info("§6 原版等级指令 §c未启用");
        }
        // 重生点系统注册
        if (getConfig().getBoolean("Module.SpawnPoints")) {
            SpawnPointsConfig.initConfig();
            getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
            getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
            getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
            getCommand("spawn").setExecutor(new SpawnPointsCommand());
            getCommand("spawn").setExecutor(new SpawnPointsCommandTab());
            // 数据库连接
            Mysql.createConfig("mysql",this);
            // 数据库表创建
            SQL.createTable("spawnpoints",new String[]{"uuid","name","spawnpointname"},new String[]{"varchar(255)","varchar(255)","varchar(255)"},new String[]{"not null","not null","not null"});
            Logger.info("§6 自定义重生点 §a已启用");
        } else {
            Logger.info("§6 自定义重生点 §c未启用");
        }
        // 镶嵌识别注册
        if (getConfig().getBoolean("Module.IdentifyInlay")) {
            IdentifyInlayConfig.initConfig();
            getServer().getPluginManager().registerEvents(new IdentifyInlayInventoryListener(), this);
            Logger.info("§6 镶嵌识别 §a已启用");
        } else {
            Logger.info("§6 镶嵌识别 §c未启用");
        }
        // 职业选择注册
        if (getConfig().getBoolean("Module.SelectJob")) {
            SelectJobConfig.initConfig();
            getServer().getPluginManager().registerEvents(new SelectJobInventoryListener(), this);
            Logger.info("§6 职业选择 §a已启用");
        } else {
            Logger.info("§6 职业选择 §c未启用");
        }
        // 自动重生注册
        if (getConfig().getBoolean("Module.AutoRespawn")) {
            getServer().getPluginManager().registerEvents(new AutoRespawnListener(),this);
            Logger.info("§6 自动重生 §a已启用");
        } else {
            Logger.info("§6 自动重生 §c未启用");
        }
        // 箭袋注册
        if (getConfig().getBoolean("Module.Quiver")) {
            QuiverConfig.initConfig();
            getServer().getPluginManager().registerEvents(new QuiverPlayerInteractListener(),this);
            Logger.info("§6 箭袋 §a已启用");
        } else {
            Logger.info("§6 箭袋 §c未启用");
        }
        // 耗时统计
        long endTime = System.currentTimeMillis();
        Logger.info("§a加载耗时: §f" + (endTime - startTime) + "ms");
    }
    @Override
    public void onDisable() {
    }
}
