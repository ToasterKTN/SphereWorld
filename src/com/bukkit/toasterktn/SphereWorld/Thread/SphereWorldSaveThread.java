package com.bukkit.toasterktn.SphereWorld.Thread;

import com.bukkit.toasterktn.SphereWorld.SphereWorld;

public class SphereWorldSaveThread implements Runnable{
    private SphereWorld plugin;
    public SphereWorldSaveThread(SphereWorld instance) {
	this.plugin = instance;
    } 

    public void run()
    {
	plugin.oldchunks.WriteChunkList(plugin.chunkfile);
	System.out.println("[SphereWorld] Autosaved Chunklist");
    }
}
