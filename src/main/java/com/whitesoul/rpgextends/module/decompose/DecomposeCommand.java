package com.whitesoul.rpgextends.module.decompose;

import com.whitesoul.rpgextends.RPGExtends;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class DecomposeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1 && strings[0].equals("decompose")) {
            Player player = (Player) commandSender;
            player.sendMessage("§e打开物品分解界面！");
            DecomposeHolder.openInventory(player);
        }
        return false;
    }
}
