package com.whitesoul.rpgextends.command;

import com.whitesoul.rpgextends.RPGExtends;
import com.whitesoul.rpgextends.module.IdentifyInlay.IdentifyInlayConfig;
import com.whitesoul.rpgextends.module.decompose.DecomposeConfig;
import com.whitesoul.rpgextends.module.decompose.DecomposeHolder;
import com.whitesoul.rpgextends.module.level.LevelConfig;
import com.whitesoul.rpgextends.module.recovery.RecoveryHolder;
import com.whitesoul.rpgextends.module.spawnpoints.SpawnPointsConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            Player player = (Player) commandSender;
            switch (strings[0]) {
                case "reload":{
                    if(player.isOp()){
                        RPGExtends.INSTANCE.reloadConfig();
                        DecomposeConfig.reload();
                        LevelConfig.reload();
                        SpawnPointsConfig.reload();
                        IdentifyInlayConfig.reload();
                        player.sendMessage("§f[§eRPGExtends§e] §a所有配置文件重载成功！");
                        }
                    }
                    break;
                case "recovery":
                    player.sendMessage("§e打开物品回收界面！");
                    RecoveryHolder.openInventory(player);
                    break;
                case "decompose":
                    player.sendMessage("§e打开物品分解界面！");
                    DecomposeHolder.openInventory(player);
                    break;
                case "help":
                    player.sendMessage("§aRPGExtends帮助");
                    player.sendMessage("§e/rpgex reload §f重载插件全部配置");
                    player.sendMessage("§e/rpgex recovery §f打开物品回收界面");
                    player.sendMessage("§e/rpgex decompose §f打开物品分解界面");
                    player.sendMessage("§e/rpgex help §f查看帮助");
                    player.sendMessage("§e/spawn §f返回重生点");
                    player.sendMessage("§e/spawn set <玩家名> <重生点名> §f设置玩家重生点");
                    player.sendMessage("§e/spawn tp <玩家名> §f传送到玩家重生点");
                    break;
            }
        }
        return false;
    }
}
