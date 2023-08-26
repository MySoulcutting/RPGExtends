package com.whitesoul.rpgextends.module.spawnpoints;

import com.whitesoul.soulsql.database.SQL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.util.UUID;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        // 查询玩家在数据库是否有数据
        ResultSet resultSet;
        try {
            resultSet = SQL.queruy("uuid, spawnpointname", "spawnpoints", "uuid", uuid.toString());
            while (resultSet.next()) {
                PlayerSpawnPointsData.playerSpawnPointsData.put(resultSet.getString("uuid"), resultSet.getString("spawnpointname"));
            }
            // 如果没有数据则插入数据
            if (PlayerSpawnPointsData.playerSpawnPointsData.get(uuid.toString()) == null) {
                SQL.insert("spawnpoints", new String[]{"uuid", "name", "spawnpointname"}, new String[]{uuid.toString(), event.getPlayer().getName(), "default"});
                PlayerSpawnPointsData.playerSpawnPointsData.put(uuid.toString(), "default");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
