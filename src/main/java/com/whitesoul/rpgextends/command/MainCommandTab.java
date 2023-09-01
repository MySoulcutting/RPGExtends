package com.whitesoul.rpgextends.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class MainCommandTab implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> tabCompletions = new ArrayList<>();
        if (strings.length == 1) {
            tabCompletions.add("recovery");
            tabCompletions.add("decompose");
            tabCompletions.add("reload");
            tabCompletions.add("help");
        }
        return tabCompletions;

    }
}
