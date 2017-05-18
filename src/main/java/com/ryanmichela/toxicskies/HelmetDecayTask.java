
package com.ryanmichela.toxicskies;

import org.bukkit.entity.Player;

import java.util.Random;

/**
 */
public class HelmetDecayTask implements Runnable {
    private Player player;

    public HelmetDecayTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        Random r = new Random();
        int breakRoll = r.nextInt(100);
        if (breakRoll < TsSettings.getHelmetBreakChancePercent()) {
            player.getInventory().setHelmet(null);
            MessageTracker.sendMessage(player, TsSettings.getHelmetBreakMessage());
        } else {
            MessageTracker.sendMessage(player, TsSettings.getHelmetSurviveMessage());
        }
    }
}
