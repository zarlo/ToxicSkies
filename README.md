Toxic Skies
===========

[![Build Status](https://ci.zarlo.xyz/job/ToxicSkies/badge/icon)](https://ci.zarlo.xyz/job/ToxicSkies/)

Toxic Skies is a Bukkit plugin that makes the world a harsher and more dangerous place. Players are forced to survive underground or else be destroyed by the elements. Toxic Skies adds the following game mechanics:

1. Players who get too close to the surface get periodically poisoned by the surface air.
2. Staying above ground without protection will result in death after about two minutes.
3. Pumpkins work like gas masks. Wearing one above ground prevents damage for a time.
4. The weather is always storming. Monsters never catch on fire and visibility is reduced.

Installation
============
1. Put .jar file in the Plugins directory.
2. Start the server to generated a default config file.
3. Update the config.yml file to set which worlds you want made toxic.
4. Restart or reload the server.

Configuration
=============
* _Mode_:
  * Mode 1 - Always raining. Air always toxic.
  * Mode 2 - Rains now and then (like normal). Air always toxic.
  * Mode 3 - Rains now and then (like normal). Air only toxic when raining.
* _SecondsBetweenPolls_: sets how frequently player exposure is checked.
* _AboveGroundDamage_: how many points of damage to do for each exposure.
* _AboveGroundMessage_: the message to display when a player is exposed.
* _PumpkinHelmetBreakChancePercent_: the percent chance that a player's pumpkin helmet will break.
* _PumpkinHelmetBreakMessage_: the message to display when a pumpkin helmet breaks.
* _PumpkinHelmetSurviveMessage_: the message to display when a pumpkin helmet does not break.
* _PumpkinHelmetMaterial_: the material name of the pumpkin helmet.
* _PoisonEffects_: a list of effects to apply when the player is poisoned.
* _AffectedWorlds_: a list of world names to make toxic.
  * To change a world's mode, add a pipe followed by the mode number after the world name
  * Ex: myWorld|2
