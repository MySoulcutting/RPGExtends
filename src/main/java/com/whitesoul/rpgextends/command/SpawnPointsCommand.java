package com.whitesoul.rpgextends.command;

import com.whitesoul.rpgextends.module.spawnpoints.PlayerSpawnPointsData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnPointsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 0 ){
            player.teleport(PlayerSpawnPointsData.getSpawnPoints(player));
        }
        if (strings.length == 2) {
            switch (strings[0]){
                case "set":
                    PlayerSpawnPointsData.setSpawnPoints(player, strings[1]);
                    break;
                case "tp":
                    player.teleport(PlayerSpawnPointsData.getSpawnPoints(Bukkit.getPlayer(strings[1])));
                    break;
            }
        }
        return false;
    }
}
