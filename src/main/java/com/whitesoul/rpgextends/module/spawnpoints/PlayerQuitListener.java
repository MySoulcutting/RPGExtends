package com.whitesoul.rpgextends.module.spawnpoints;

import com.whitesoul.soulsql.database.SQL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerLeaveEvent(PlayerQuitEvent event){
        String uuid = event.getPlayer().getUniqueId().toString();
        // 保存玩家数据
        SQL.update("spawnpointname",PlayerSpawnPointsData.playerSpawnPointsData.get(uuid),"spawnpoints","uuid",uuid);
    }

}
