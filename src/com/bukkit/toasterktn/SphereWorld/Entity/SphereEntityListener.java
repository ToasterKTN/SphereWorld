package com.bukkit.toasterktn.SphereWorld.Entity;

import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityListener;

import com.bukkit.toasterktn.SphereWorld.Config.SphereWorldConfig;

public class SphereEntityListener extends EntityListener{
    public void onCreatureSpawn(CreatureSpawnEvent event) {
	if (event.isCancelled()) return;
	if (event.getLocation().getWorld().getName().equalsIgnoreCase(SphereWorldConfig.world)) 
	    if (event.getLocation().getY() < 5) event.setCancelled(true);
    }
}
