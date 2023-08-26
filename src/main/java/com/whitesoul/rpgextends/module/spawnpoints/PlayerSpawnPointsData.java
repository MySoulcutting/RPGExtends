package com.whitesoul.rpgextends.module.spawnpoints;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.concurrent.ConcurrentHashMap;

public class PlayerSpawnPointsData {
    public static ConcurrentHashMap<String,String> playerSpawnPointsData = new ConcurrentHashMap<>();
    public static Location getSpawnPoints(Player player) {
        for (String key : SpawnPointsConfig.getConfig().getConfigurationSection("Points").getKeys(false)) {
            if (PlayerSpawnPointsData.playerSpawnPointsData.get(player.getUniqueId().toString()).equals(key)) {
                World world = Bukkit.getWorld(SpawnPointsConfig.getConfig().getString("Points." + key + ".world"));
                return new Location(world, SpawnPointsConfig.getConfig().getDouble("Points." + key + ".x"), SpawnPointsConfig.getConfig().getDouble("Points." + key + ".y"), SpawnPointsConfig.getConfig().getDouble("Points." + key + ".z"), SpawnPointsConfig.getConfig().getInt("Points." + key + ".yaw"), SpawnPointsConfig.getConfig().getInt("Points." + key + ".pitch"));
            }
        }
        return null;
    }
    public static void setSpawnPoints(Player player, String spawnPointsName) {
        PlayerSpawnPointsData.playerSpawnPointsData.put(player.getUniqueId().toString(), spawnPointsName);
    }
}
