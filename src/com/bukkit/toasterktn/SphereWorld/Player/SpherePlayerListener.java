package com.bukkit.toasterktn.SphereWorld.Player;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.bukkit.toasterktn.SphereWorld.Config.SphereWorldConfig;

public class SpherePlayerListener extends PlayerListener{
    
    public void onPlayerMove(PlayerMoveEvent event) {
	if (event.getPlayer() != null)
	    if (event.getTo().getY() < 2) {
		if (event.getPlayer().getWorld().getName().equalsIgnoreCase(SphereWorldConfig.world)) {
			event.getPlayer().sendMessage("You are not supposed to be here...");
			event.getPlayer().damage(4);  
		}
	}
    }	
}
