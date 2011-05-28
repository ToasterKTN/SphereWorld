package com.bukkit.toasterktn.SphereWorld.Chunk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;

public class ChunckList implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	List<String> thisoldchunks = new ArrayList<String>();
	//worldname||x||y
	
	public void WriteChunkList(File filename) {
		// Write to disk with FileOutputStream
		FileOutputStream f_out;
		try {
			f_out = new FileOutputStream(filename);
			// Write object with ObjectOutputStream
			ObjectOutputStream obj_out;
			obj_out = new ObjectOutputStream (f_out);
		    // Write object out to disk
			obj_out.writeObject ( thisoldchunks );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void ReadChunkList(File filename) {
		// Read from disk using FileInputStream
		try {
			thisoldchunks.clear();
			FileInputStream f_in;
			f_in = new FileInputStream(filename);
			// Read object using ObjectInputStream
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
			// Read an object
			Object obj = obj_in.readObject();
			if (obj instanceof List<?>)
			{
				thisoldchunks = (List<String>)obj;
			}
		} catch (Exception e) {
			System.out.print("First run maybe");
			thisoldchunks.clear();
			//e.printStackTrace();
		}
	}
	public void AddChunkToList(Chunk chunk) {
		thisoldchunks.add(createChunkEntry(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()));
	}
	public void AddChunkToList(String worldname, int x, int z) {
		thisoldchunks.add(createChunkEntry(worldname,x,z));
	}
	
	public boolean isInChunkList(Chunk chunk) {
		return thisoldchunks.contains(createChunkEntry(chunk.getWorld().getName(), chunk.getX(), chunk.getZ()));
	}
	
	public String createChunkEntry(String worldname, int x, int z) {
		return worldname + "|" + String.valueOf(x) + "|" + String.valueOf(z);
	}
}
