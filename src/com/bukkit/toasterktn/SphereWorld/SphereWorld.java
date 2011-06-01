package com.bukkit.toasterktn.SphereWorld;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.Vector;

import com.bukkit.toasterktn.SphereWorld.Block.SphereBlockListener;
import com.bukkit.toasterktn.SphereWorld.Chunk.ChunckList;
import com.bukkit.toasterktn.SphereWorld.Chunk.ChunkListener;
import com.bukkit.toasterktn.SphereWorld.Config.SphereWorldConfig;
import com.bukkit.toasterktn.SphereWorld.Entity.SphereEntityListener;
import com.bukkit.toasterktn.SphereWorld.Player.SpherePlayerListener;
import com.bukkit.toasterktn.SphereWorld.Thread.SphereWorldSaveThread;

// TODO Add bridges ( dobridges  / bridgetype )
// TODO Other Shapes like Cubes ?
// TODO Underwaterworld
// TODO ..

public class SphereWorld extends JavaPlugin {
    // Starts the class
    // Links the ChunkListener
    private ChunkListener chunkListener;// = new ChunkListener(this);
    // Spheres
    public Spheres spheres = new Spheres();
    public ChunckList oldchunks = new ChunckList();
    public boolean isGenerating = false;
    public File chunkfile;
    private File speheresfile;

    public static final Logger log = Logger.getLogger("Minecraft");

    @Override
    // When the plugin is disabled this method is called.
    public void onDisable() {
	oldchunks.WriteChunkList(chunkfile);
	System.out.println("[SphereWorld] Disabled");
    }

    @Override
    // When the plugin is enabled this method is called.
    public void onEnable() {
	// Create the pluginmanage pm.
	SphereWorldConfig.initialize(getDataFolder());
	// Force Worldload
	if(getServer().getWorld(SphereWorldConfig.world) == null) {
	    getServer().createWorld(SphereWorldConfig.world, Environment.NORMAL, SphereWorldConfig.worldseed);
	}
	// Get Chunk data
	PluginManager pm = getServer().getPluginManager();
	PluginDescriptionFile pdfFile = this.getDescription();
	pm.registerEvent(Event.Type.BLOCK_PHYSICS, new SphereBlockListener(this), Event.Priority.Low, this);
	pm.registerEvent(Event.Type.BLOCK_FROMTO, new SphereBlockListener(this), Event.Priority.Low, this);	
	
	this.isGenerating = true;
	
	chunkfile = new File(getDataFolder(), "chunklist.data");
	oldchunks.ReadChunkList(chunkfile);
	// Get / Create Sphere data
	if (!SphereWorldConfig.otherworld) {
	    speheresfile = new File(getDataFolder(), "spheres.data");
	    spheres.ReadSphereList(speheresfile, getServer());
	    // Create Attach Listener / Attach MyShereGenerator
	    if (spheres.GetSphereList().size() < 1) {
		log.info("[SphereWorld] NOT Loaded");
		return;
	    }
	}
	chunkListener = new ChunkListener(this);
	this.isGenerating = false;

	//Default Stuff
	
	// Register a Chunk Creation, we may want to add a Cache
	pm.registerEvent(Event.Type.CHUNK_LOAD, this.chunkListener, Event.Priority.Normal, this);
	// Kill Players on Floor
	if (SphereWorldConfig.killonfloor)
	    pm.registerEvent(Event.Type.PLAYER_MOVE, new SpherePlayerListener(), Event.Priority.Normal, this);
	// Protect Floor if needed
	if (SphereWorldConfig.floorprotect) {
	    pm.registerEvent(Event.Type.BLOCK_PLACE, new SphereBlockListener(this), Event.Priority.Low, this);
	}
	// Protect Blocks if needed
	if (SphereWorldConfig.potprotect || SphereWorldConfig.sphereprotect || SphereWorldConfig.floorprotect) {
	    pm.registerEvent(Event.Type.BLOCK_BREAK, new SphereBlockListener(this), Event.Priority.Normal, this);
	}
	
	if (SphereWorldConfig.nofloorspawn) {
	    pm.registerEvent(Event.Type.CREATURE_SPAWN, new SphereEntityListener(this), Event.Priority.Normal, this);
	}
	
	if (SphereWorldConfig.potprotect || SphereWorldConfig.sphereprotect ) {
	    pm.registerEvent(Event.Type.ENTITY_EXPLODE, new SphereEntityListener(this), Event.Priority.Normal, this);
	}
	
	// Initialize Autosave
	if (SphereWorldConfig.autosavechunklist) {
	    getServer().getScheduler().scheduleAsyncRepeatingTask(this, new SphereWorldSaveThread(this),1200 * SphereWorldConfig.autosaveinterval, 1200 * SphereWorldConfig.autosaveinterval);
	}
	log.info("[SphereWorld] version " + pdfFile.getVersion()
		+ " is enabled!");

	log.info("[SphereWorld] Loaded");
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	if (command.getName().equalsIgnoreCase("sphere")) {
	    if (!(sender instanceof Player)) {
		log.info("This command cannot be used in the console.");
		return true;
	    }
	    Player player = (Player) sender;
	    Vector v = player.getLocation().toVector();
	    for (Sphere s:spheres.GetSphereList()) {
		if(s.getV().distance(v) < s.getSize())
		{
		    player.sendMessage("Sphere you are in has");
		    player.sendMessage("Center x:"+ s.getX() +" y:"+ s.getY()+ "z:"+s.getZ());
		    player.sendMessage("Radius r:"+ s.getSize());
		    player.sendMessage("From x,z:"+ (s.getX() - s.getSize()) +","+(s.getZ() - s.getSize()));
		    player.sendMessage("To x,z:"+ (s.getX() + s.getSize()) +","+(s.getZ() + s.getSize()));
		}
	    }
	    return true;
	}
	
	if (command.getName().equalsIgnoreCase("regeneratechunk")) {
	    if (!(sender instanceof Player)) {
		log.info("This command cannot be used in the console.");
		return true;
	    }
	    Player player = (Player) sender;
	    if (!player.isOp()) return false;
	    if (args.length >= 2) {
		try {
		    player.getWorld().regenerateChunk(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		    player.sendMessage("¤7Regenerated chunk at " + args[0]
			    + "," + args[1] + "!");
		    log.info(player.getName() + " regenerated chunk at "
			    + args[0] + "," + args[1] + "!");
		} catch (NumberFormatException n) {
		    player.sendMessage("¤cUnknown chunk coordinates!");
		}
	    } else {
		player.getWorld().regenerateChunk(player.getLocation().getBlock().getChunk().getX(), player.getLocation().getBlock().getChunk().getZ());
		player.sendMessage("¤7Regenerated chunk at "
			+ player.getLocation().getBlock().getChunk().getX()
			+ ","
			+ player.getLocation().getBlock().getChunk().getZ()
			+ "!");
		log.info(player.getName() + " regenerated chunk at "
			+ player.getLocation().getBlock().getChunk().getX()
			+ ","
			+ player.getLocation().getBlock().getChunk().getZ()
			+ "!");
	    }
	    return true;
	}
	return false;
    }

}