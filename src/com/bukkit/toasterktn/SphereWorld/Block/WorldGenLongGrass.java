package com.bukkit.toasterktn.SphereWorld.Block;

import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.BlockFlower;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
 
 public class WorldGenLongGrass extends WorldGenerator {
 
     private int a;
     private int type;
 
     public WorldGenLongGrass(int i, byte type) {
         this.a = i;
         this.type = type;
     }
 
     public boolean a(World world, Random random, int i, int j, int k) {
         for (int l = 0; l < 64; ++l) {
             int i1 = i + random.nextInt(8) - random.nextInt(8);
             int j1 = world.getHighestBlockYAt(i, k);
             int k1 = k + random.nextInt(8) - random.nextInt(8);
 
             if (world.isEmpty(i1, j1, k1) && ((BlockFlower) Block.byId[this.a]).f(world, i1, j1, k1)) {
                 world.setTypeIdAndData(i1, j1, k1, this.a, type);
             }
         }
         return true;
     }
 }