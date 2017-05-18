package com.ryanmichela.toxicskies;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.ryanmichela.toxicskies.WG.WGMain;
/**
 */
public class TsPlugin extends JavaPlugin  implements Listener
{
	
    private static Plugin instance;
    public TsPlugin() {
        instance = this;
    }

    public void onEnable() {
        if(Utill.loadPlugin("WorldGuard") != null){
    	getLogger().info("Adding WorldGuard stuff ");
        WGMain.Init();
        WGMain.hasWG = true;
        }
        else
        {


        }
        saveDefaultConfig();

        for (String worldName : TsSettings.getAffectedWorlds()) {
            getLogger().info("Making the skies toxic in " + worldName);
            if (TsSettings.getMode(worldName) == 1) {
                // Always raining
                AmbianceTask task = new AmbianceTask(this, worldName);
                getServer().getScheduler().scheduleSyncDelayedTask(this, task);
            }
        }

        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        MessageTracker.initPlayer(event.getPlayer());
        PoisonCheckTask task = new PoisonCheckTask(this, event.getPlayer());
        getServer().getScheduler().scheduleSyncDelayedTask(this, task, 20*10);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        MessageTracker.clearMessage(event.getPlayer());
    }

    static Plugin getInstance() {
        return instance;
    }
}
