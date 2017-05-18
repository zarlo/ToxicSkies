package com.ryanmichela.toxicskies;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import com.ryanmichela.toxicskies.WG.WGMain;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;

import java.util.Random;

public class PoisonCheckTask implements Runnable {
	private int RADIUS_TO_SEEK_SKY = 7;

	private Plugin plugin;
	private Player player;
	private Random r = new Random();

	public PoisonCheckTask(Plugin plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
	}


	@Override
	public void run() {

		
	if(TsSettings.getDebug().toLowerCase() == "true"){	
if(TsSettings.playerInAffectedWorld(player)){
		MessageTracker.sendMessage(player, "1True");
}else
{
	MessageTracker.sendMessage(player, "1False");	
}
if(modeAllowsDamage(player)){
	MessageTracker.sendMessage(player, "2True");
}else
{
MessageTracker.sendMessage(player, "2False");	
}		

if(!player.hasPermission("toxicskies.bypass.*")){
	MessageTracker.sendMessage(player, "3True");
}else
{
MessageTracker.sendMessage(player, "3False");	
}		

if(WGMain.IsInRegion(player)){
	MessageTracker.sendMessage(player, "4True");
}else
{
MessageTracker.sendMessage(player, "4False");	
}		

if(!player.hasPermission("toxicskies.bypass." + player.getWorld().getName())){
	MessageTracker.sendMessage(player, "5True");
}else
{
MessageTracker.sendMessage(player, "5False");	
}
if(!player.hasPotionEffect(PotionEffectType.WATER_BREATHING)){
	MessageTracker.sendMessage(player, "6True");
}else
{
MessageTracker.sendMessage(player, "6False");	
}
	}

		if (player.isOnline()
				&& TsSettings.playerInAffectedWorld(player)
				&& modeAllowsDamage(player)
				&& player.getGameMode() == GameMode.SURVIVAL
				&& !player.hasPermission("toxicskies.bypass.*")
				&& WGMain.IsInRegion(player)
				&& !player.hasPermission("toxicskies.bypass." + player.getWorld().getName())
				&& !player.hasPotionEffect(PotionEffectType.WATER_BREATHING)) {
			try {
				//MessageTracker.sendMessage(player, "RUN");
				Location playerHead = normalize(player.getLocation()).add(0, 1, 0);
				SkyFinder skyFinder = new DepthFirstSkyFinder();
				Runnable nextTask;
				if (skyFinder.canSeeSky(playerHead, RADIUS_TO_SEEK_SKY)) {

					if (player.getInventory().getHelmet() != null
							&& player.getInventory().getHelmet().getType() == TsSettings.getHelmetMaterial()) {
						nextTask = new HelmetDecayTask(player);
					} else {
						nextTask = new DamageApplyTask(player);
					}
					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, nextTask);
				} else {
					MessageTracker.sendMessage(player, TsSettings.getCleanAirMessage());
				}
			} catch (Throwable t) {
				plugin.getLogger().severe(t.toString());
			}
			if (plugin.isEnabled()) {
				int offset = r.nextInt(20);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this,
						TsSettings.getSecondsBetweenPolls() + offset);
			}
		}
		else
		{
			if (plugin.isEnabled()) {
				int offset = r.nextInt(40);
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this,
						TsSettings.getSecondsBetweenPolls() + offset);
			}
		}
		
	}

	private Location normalize(Location l) {
		return new Location(l.getWorld(), l.getBlockX(), l.getBlockY(), l.getBlockZ());
	}

	private boolean modeAllowsDamage(Player p) {
		if (TsSettings.getMode(p.getWorld().getName()) == 1 || TsSettings.getMode(p.getWorld().getName()) == 2) {
			return true;
		}
		return p.getWorld().hasStorm();
	}
}
