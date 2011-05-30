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
	public static boolean usehalfglass;
	public static boolean userandomglass;
	public static int spherechance;
	public static boolean usefloor;
	public static boolean killonfloor;
	public static boolean dobridges;
	public static boolean useglow;
	public static int bridgetype;
	public static int worldseed;
	public static int sphereseed;
	public static boolean otherworld;
	public static int glassblock;
	public static boolean potprotect;
	public static boolean sphereprotect;
	public static boolean floorprotect;
	public static boolean nowater;
	public static boolean nofloorspawn;
	public static boolean autosavechunklist;
	public static int autosaveinterval;
	
	public static void initialize(File dataFolder) {
		
	        if(!dataFolder.exists()) {
	            dataFolder.mkdirs();
	        }
	        
	        File configFile  = new File(dataFolder, settingsFile);
	        BetterConfig config = new BetterConfig(configFile);
	        config.load();
	     	
	     	world  = config.getString("world", "sphere");
	     	useglass = config.getBoolean("useglass", true);
	     	usehalfglass = config.getBoolean("usehalfglass", false);
	     	userandomglass = config.getBoolean("userandomglass", false);
	     	usefloor = config.getBoolean("usefloor", true);
	     	killonfloor = config.getBoolean("killonfloor", true);
	     	dobridges = config.getBoolean("dobridges", true);
	     	useglow = config.getBoolean("useglow", false);
	     	mindist = config.getInt("mindist", 100);
	     	minheight = config.getInt("minheight", 34);
	     	maxheight = config.getInt("maxheight", 96);
	     	minradius = config.getInt("minradius", 8);
	     	maxradius = config.getInt("maxradius", 32);
		worldsize = config.getInt("worldsize", 2000);
		worldseed = config.getInt("worldseed", 12345);
		sphereseed = config.getInt("sphereseed", 12345);
	     	spherechance = config.getInt("spherechance", 80);
	     	glassblock = config.getInt("glassblock", 20);
	     	bridgetype = config.getInt("bridgetype", 1);
	     	otherworld = config.getBoolean("otherworld", false);
	     	potprotect = config.getBoolean("potprotect", true);
	     	sphereprotect = config.getBoolean("sphereprotect", false);
	     	floorprotect = config.getBoolean("floorprotect", false);
	     	nowater = config.getBoolean("nowater", true);
	     	nofloorspawn = config.getBoolean("nofloorspawn", true);
	     	autosavechunklist = config.getBoolean("autosavechunklist", true);
	     	autosaveinterval = config.getInt("autosaveinterval", 30);
	     	config.save();
	    }
}
