package com.ryanmichela.toxicskies.WG;

import org.bukkit.entity.Player;

import com.ryanmichela.toxicskies.TsPlugin;
import com.ryanmichela.toxicskies.Utill;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.*;;

public class WGMain {

	//public static final Flag ToxicSkies_Flag = new StateFlag("ToxicSkies", true);

	/** The World guard. */
    public static WorldGuardPlugin WorldGuard = null;
    public static boolean hasWG = false;
	
	public static void Init()
	{
		
    	WorldGuard = ((WorldGuardPlugin) Utill.loadPlugin("WorldGuard"));
    	
		
	}
	
	public static Boolean IsInRegion(Player p)
	{
		if(hasWG)
		{
			if(WorldGuard.getRegionManager(p.getWorld()).getApplicableRegions(p.getLocation()) == null)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		return true;
	}
	
	
	
}
