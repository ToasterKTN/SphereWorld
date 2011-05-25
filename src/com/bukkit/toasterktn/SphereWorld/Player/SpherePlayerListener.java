package com.bukkit.toasterktn.SphereWorld.Player;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpherePlayerListener extends PlayerListener{
    
    public void onPlayerMove(PlayerMoveEvent event) {
	if (event.getPlayer() != null)
	    if (event.getTo().getY() < 2) {
		event.getPlayer().sendMessage("You are not supposed to be here...");
		event.getPlayer().damage(1);
	}
    }	
}
