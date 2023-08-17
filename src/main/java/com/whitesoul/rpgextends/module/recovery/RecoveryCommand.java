package com.whitesoul.rpgextends.module.recovery;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class RecoveryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1 && strings[0].equals("recovery")) {
            Player player = (Player) commandSender;
            player.sendMessage("§e打开物品回收界面！");
            RecoveryHolder.openInventory(player);
        }
        return false;
    }
}
