package com.bukkit.toasterktn.SphereWorld.Block;

import java.util.Random;

import net.minecraft.server.Material;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
 
 public class WorldGenGrass extends WorldGenerator {
 
     private int a;

 
     public WorldGenGrass(int i) {
         this.a = i;
     }
 
     public boolean a(World world, Random random, int i, int j, int k) {
         for (int l = 0; l < 64; ++l) {
             int i1 = i + random.nextInt(8) - random.nextInt(8);
             int j1 = world.getHighestBlockYAt(i, k);
             int k1 = k + random.nextInt(8) - random.nextInt(8);
 
             if (world.getMaterial(i1, j1-1, k1) == Material.EARTH) {
                 world.setTypeId(i1, j1-1, k1, this.a);
             }
         }
         return true;
     }
 }