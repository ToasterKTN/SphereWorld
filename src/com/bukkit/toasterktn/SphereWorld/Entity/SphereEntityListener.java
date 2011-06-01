package com.bukkit.toasterktn.SphereWorld.Entity;

import java.util.List;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.util.Vector;

import com.bukkit.toasterktn.SphereWorld.Sphere;
import com.bukkit.toasterktn.SphereWorld.SphereWorld;
import com.bukkit.toasterktn.SphereWorld.Config.SphereWorldConfig;
import com.bukkit.toasterktn.SphereWorld.Thread.SphereWorldGlassThread;

public class SphereEntityListener extends EntityListener {
    private SphereWorld plugin;

    public void onCreatureSpawn(CreatureSpawnEvent event) {
	if (event.isCancelled())
	    return;
	if (event.getLocation().getWorld().getName().equalsIgnoreCase(SphereWorldConfig.world))
	    if (event.getLocation().getY() < 5)
		event.setCancelled(true);
    }

    public SphereEntityListener(SphereWorld instance) {
	this.plugin = instance;
    }

    public void onEntityExplode(EntityExplodeEvent event) {
	if (event.isCancelled())
	    return;
	if (event.getLocation().getWorld().getName().equalsIgnoreCase(SphereWorldConfig.world)) {
	    if (SphereWorldConfig.potprotect || SphereWorldConfig.sphereprotect) {
		List<Block> redolist = new ArrayList<Block>();
		for (Block b : event.blockList()) {
		    if (b.getTypeId() == SphereWorldConfig.glassblock || b.getType() == Material.GLOWSTONE) {
			// Check if it is in one of our Spheres
			for (Sphere s : plugin.spheres.GetSphereList()) {
			    if (s.getX() > b.getX()
				    - SphereWorldConfig.maxradius
				    && s.getX() < b.getX()
					    + SphereWorldConfig.maxradius + 16) {
				if (s.getZ() > b.getZ()
					- SphereWorldConfig.maxradius
					&& s.getZ() < b.getZ()
						+ SphereWorldConfig.maxradius
						+ 16) {
				    if (s.getV().distance(new Vector((double) b.getX(), (double) b.getY(), (double) b.getZ())) < s.getSize()) {
					if (s.getV().distance(new Vector((double) b.getX(), (double) b.getY(), (double) b.getZ())) > s.getSize() - 1.1) {
					    // This is on the Edge.
					    if (SphereWorldConfig.sphereprotect
						    || (SphereWorldConfig.potprotect && b.getY() < 65)) {
						    redolist.add(b);
					    }
					}
				    }
				}
			    }
			}
		    }
		}
		if (redolist.size() > 0)
		    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SphereWorldGlassThread(redolist),5);  
	    }
	}
    }
}
