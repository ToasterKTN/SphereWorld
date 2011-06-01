package com.bukkit.toasterktn.SphereWorld.Thread;

import java.util.List;
import  org.bukkit.block.Block;
import com.bukkit.toasterktn.SphereWorld.Config.SphereWorldConfig;

public class SphereWorldGlassThread implements Runnable{

    private List<Block> bb;
    public SphereWorldGlassThread(List<Block> bb) {
	this.bb = bb;
    } 

    public void run()
    {
	for (Block b:bb) {
	    b.setTypeId(SphereWorldConfig.glassblock);
	}
    }
}
