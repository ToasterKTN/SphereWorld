package com.bukkit.toasterktn.SphereWorld.Chunk;

//import java.util.ArrayList;
//import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.bukkit.toasterktn.SphereWorld.Sphere;
import com.bukkit.toasterktn.SphereWorld.Spheres;

public class ChunkWorker extends Thread {
    private Chunk chunk;
    private Spheres ss;

    public ChunkWorker(Chunk chunk, Spheres ss) {
	this.chunk = chunk;
	this.ss = ss;
    }

    @Override
    public void run() {
	setPriority(Thread.MIN_PRIORITY);
	try {
	    sleep(50);
	    // Chunk chunk = event.getChunk();
//	    List<Sphere> nearspheres = new ArrayList<Sphere>();
//	    for (Sphere s : ss.GetSphereList()) {
//		if (s.getWorld().equalsIgnoreCase(chunk.getWorld().getName())) {
//		    // same world
//		    if (chunk.getBlock(8, 64, 8).getLocation().toVector().distance(s.getV()) < 200) {
//			nearspheres.add(s);
//		    }
//		}
//	    }
	    // loop
	    Boolean keep;
	    Block b;
	    for (int x1 = 0; x1 < 16; x1++) {
		 for (int z1 = 0; z1 < 16; z1++) {
		     for (int y1 = 127; y1 >= 0; y1--) {
			b = chunk.getBlock(x1, y1, z1);
			if (b != null && b.getTypeId() != 7) {
			    keep = false;
			    for (Sphere s : ss.GetSphereList()) {
				if (s.getV().distance(b.getLocation().toVector()) < s.getSize())
				    keep = true;
				if (s.getV().distance(b.getLocation().toVector()) < s.getSize() +1 && s.getV().distance(b.getLocation().toVector()) > s.getSize() ) {
				    b.setType(Material.GLASS);
				    keep = true;
				}
			    }
			    try {
				if (!keep  && b.getTypeId() != 0 )
				    b.setType(Material.AIR);
			    } catch (Exception e) {
				// gone.. shit happens
			    }
			}
		    }
		}
		//sleep(50);
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}

    }
}
