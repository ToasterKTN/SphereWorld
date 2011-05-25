package com.bukkit.toasterktn.SphereWorld.Config;

import java.io.File;


public class SphereWorldConfig {
	private static final String settingsFile = "SphereWorld.yml";
	public static String world;
	public static int mindist;
	public static int minheight;
	public static int maxheight;
	public static int minradius;
	public static int maxradius;
	public static int worldsize;
	public static boolean useglass;
	public static int spherechance;
	public static boolean usefloor;
	public static boolean killonfloor;
	
	public static void initialize(File dataFolder) {
		
	        if(!dataFolder.exists()) {
	            dataFolder.mkdirs();
	        }
	        
	        File configFile  = new File(dataFolder, settingsFile);
	        BetterConfig config = new BetterConfig(configFile);
	        config.load();
	     	
	     	world  = config.getString("world", "sphere");
	     	useglass = config.getBoolean("useglass", true);
	     	usefloor = config.getBoolean("usefloor", true);
	     	killonfloor = config.getBoolean("killonfloor", true);
	     	mindist = config.getInt("mindist", 100);
	     	minheight = config.getInt("minheight", 34);
	     	maxheight = config.getInt("maxheight", 96);
	     	minradius = config.getInt("minradius", 8);
	     	maxradius = config.getInt("maxradius", 32);
		worldsize = config.getInt("worldsize", 2000);
	     	spherechance = config.getInt("spherechance", 80);
	     	config.save();
	    }
}
