package com.bukkit.toasterktn.SphereWorld;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

//import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.util.Vector;

import com.bukkit.toasterktn.SphereWorld.Config.SphereWorldConfig;

public class Spheres implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	Random r=new Random(new Date().getTime());
	List<Sphere> thisspheres = new ArrayList<Sphere>();
	boolean debug= false;
	
	public void WriteSphereList(File filename) {
		// Write to disk with FileOutputStream
		FileOutputStream f_out;
		try {
			f_out = new FileOutputStream(filename);
			// Write object with ObjectOutputStream
			ObjectOutputStream obj_out;
			obj_out = new ObjectOutputStream (f_out);
		    // Write object out to disk
			obj_out.writeObject ( thisspheres );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void ReadSphereList(File filename ,Server server) {
		// Read from disk using FileInputStream
		try {
		    	thisspheres.clear();
			FileInputStream f_in;
			f_in = new FileInputStream(filename);
			// Read object using ObjectInputStream
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
			// Read an object
			Object obj = obj_in.readObject();
			if (obj instanceof List<?>)
			{
			    thisspheres = (List<Sphere>)obj;
			}
			for (Sphere s: thisspheres) {
			    s.setV(new Vector(s.getX(), s.getY(), s.getZ()));
			}
		} catch (Exception e) {
			System.out.print("First run lets generate the spheres");
			thisspheres.clear();
			// Now test if we make a n
			if(!debug) {
			    {
				try {
				    Sphere ns = new Sphere();
				    ns.setSize(SphereWorldConfig.maxradius);
		    	    		ns.setV(server.getWorld(SphereWorldConfig.world).getSpawnLocation().toVector());
		    	    		ns.setWorld(SphereWorldConfig.world);
		    	    		ns.setX(server.getWorld(SphereWorldConfig.world).getSpawnLocation().getX());
		    	    		ns.setY(server.getWorld(SphereWorldConfig.world).getSpawnLocation().getY());
		    	    		ns.setZ(server.getWorld(SphereWorldConfig.world).getSpawnLocation().getZ());
		    	    		//System.out.print("CREATE SPHERE at x " + ns.getX() +  " - y " + ns.getY() + " - z " + ns.getZ());
		    	    		System.out.println("Creating new Spheres... First run");
		    	    		System.out.print("working.. please wait.. this may take several minutes");
		    	    		thisspheres.add(ns);
		    	    	} catch (Exception e1) {
				    // Well seems like world is not default world.. so we cannot get spawnpoint.
		    	    	    // Lets create a default at 0,0,0
		    	    	    System.out.print("World does not exist... This is not Good. Should not happen");
		    	    	    return;
		    	    	   //thisspheres.add(ns);
				}
				
			    }
		    	    // Add this for start
			for (int x = - SphereWorldConfig.worldsize ; x <  SphereWorldConfig.worldsize ; x = x + 20) {
			    for (int y = - SphereWorldConfig.worldsize ; y <  SphereWorldConfig.worldsize ; y = y + 20) {   
				Boolean makenew = true;
				Location loc = new Location(null, x + r.nextInt(20),  r.nextInt(SphereWorldConfig.maxheight - SphereWorldConfig.minheight) + SphereWorldConfig.minheight , y + r.nextInt(20) );
			    	for (Sphere s: thisspheres) {
			    	    if (s.getV().distance(loc.toVector()) < SphereWorldConfig.mindist) makenew = false;
			    	}
			    	if (makenew) {
			    	    // distance ok make sphere
			    	    //System.out.print("CREATE SPHERE at x " + loc.getX() +  " - y " + loc.getY() + " - z " + loc.getZ());
			    	    //System.out.print(".");
			    	    Sphere ns = new Sphere();
			    	    ns.setSize(r.nextInt(SphereWorldConfig.maxradius - SphereWorldConfig.minradius) + SphereWorldConfig.minradius);
			    	    ns.setV(new Vector(loc.getX(), loc.getY(), loc.getZ()));
			    	    ns.setWorld(SphereWorldConfig.world);
			    	    ns.setX(loc.getX());
			    	    ns.setY(loc.getY());
			    	    ns.setZ(loc.getZ());
			    	    thisspheres.add(ns);
			    	    ns=null;
			    	}
			    }
			}
			}
			if (debug) {
			    thisspheres.clear();
			    Sphere ns = new Sphere();
		    	    ns.setSize(r.nextInt(SphereWorldConfig.maxradius - SphereWorldConfig.minradius) + SphereWorldConfig.minradius);
		    	    ns.setV(server.getWorld(SphereWorldConfig.world).getSpawnLocation().toVector());
		    	    ns.setWorld(SphereWorldConfig.world);
		    	    ns.setX(server.getWorld(SphereWorldConfig.world).getSpawnLocation().getX());
		    	    ns.setY(server.getWorld(SphereWorldConfig.world).getSpawnLocation().getY());
		    	    ns.setZ(server.getWorld(SphereWorldConfig.world).getSpawnLocation().getZ());
		    	    System.out.print("CREATE SPHERE at x " + ns.getX() +  " - y " + ns.getY() + " - z " + ns.getZ());
		    	    thisspheres.add(ns);
			}
			
			WriteSphereList(filename);
		}
		System.out.println("Created / Loaded Spheres: " + thisspheres.size());
	}
	public void AddSphereToList(Sphere sphere) {
	    thisspheres.add(sphere);
	}
	
	public List<Sphere> GetSphereList() {
	    return thisspheres;
	}
	public boolean isSphereInList(Sphere sphere) {
	    return thisspheres.contains(sphere);
	}
}
