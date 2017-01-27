package com.ryanmichela.toxicskies;

import org.bukkit.plugin.Plugin;

public class Utill {


    public static Plugin loadPlugin(String pluginName) {
        Plugin plugin = TsPlugin.getInstance().getServer().getPluginManager().getPlugin(pluginName);
        if (plugin == null) {
            return null;
        }
        return plugin;
    }
	
}
