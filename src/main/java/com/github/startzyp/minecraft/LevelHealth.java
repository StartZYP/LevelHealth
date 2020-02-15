package com.github.startzyp.minecraft;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class LevelHealth extends JavaPlugin implements Listener {
    private int Health = 0;
    private String Msg;

    @EventHandler
    public void PlayerLevel(PlayerLevelChangeEvent event){
        event.getPlayer().setMaxHealth(20+event.getNewLevel()*Health);
        event.getPlayer().sendMessage(Msg);
    }

    @EventHandler
    public void PlayerChangeWorld(PlayerChangedWorldEvent event){
        event.getPlayer().setMaxHealth(20+event.getPlayer().getLevel()*Health);
    }
    @EventHandler
    public void PlayerJoinGame(PlayerJoinEvent event){
        event.getPlayer().setMaxHealth(20+event.getPlayer().getLevel()*Health);
    }

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event){
        event.getEntity().setMaxHealth(20+event.getEntity().getLevel()*Health);
    }

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveDefaultConfig();
        Health = getConfig().getInt("LevelAdd");
        Msg = getConfig().getString("LevelMsg");
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        super.onEnable();
    }
}
