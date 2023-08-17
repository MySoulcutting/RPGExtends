package com.whitesoul.rpgextends.module.recovery;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length = 1 && strings[0].equals("open")) {
            commandSender.sendMessage("§e打开物品回收界面！");
            return true;
        }
        return false;
    }
}
