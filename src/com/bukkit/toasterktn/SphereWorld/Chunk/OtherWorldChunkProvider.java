package com.bukkit.toasterktn.SphereWorld.Chunk;

import java.util.Random;

import com.bukkit.toasterktn.SphereWorld.SphereWorld;
import com.bukkit.toasterktn.SphereWorld.Block.WorldGenGrass;
import com.bukkit.toasterktn.SphereWorld.Block.WorldGenLongGrass;
import com.bukkit.toasterktn.SphereWorld.Config.SphereWorldConfig;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.Block;
import net.minecraft.server.BlockSand;
import net.minecraft.server.Chunk;
import net.minecraft.server.IChunkProvider;
import net.minecraft.server.IProgressUpdate;
import net.minecraft.server.MapGenBase;
import net.minecraft.server.MapGenCaves;
import net.minecraft.server.Material;
import net.minecraft.server.NoiseGeneratorOctaves;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenCactus;
import net.minecraft.server.WorldGenClay;
import net.minecraft.server.WorldGenDungeons;
import net.minecraft.server.WorldGenFlowers;
import net.minecraft.server.WorldGenLakes;
import net.minecraft.server.WorldGenLiquids;
import net.minecraft.server.WorldGenMinable;
import net.minecraft.server.WorldGenPumpkin;
import net.minecraft.server.WorldGenReed;
import net.minecraft.server.WorldGenerator;

public class OtherWorldChunkProvider implements IChunkProvider {
    private SphereWorld plugin;
    private Random j;
    private NoiseGeneratorOctaves k;
    private NoiseGeneratorOctaves l;
    private NoiseGeneratorOctaves m;
    private NoiseGeneratorOctaves n;
    private NoiseGeneratorOctaves o;
    public NoiseGeneratorOctaves a;
    public NoiseGeneratorOctaves b;
    public NoiseGeneratorOctaves c;
    private World p;
    private double[] q;
    private double[] r = new double[256];
    private double[] s = new double[256];
    private double[] t = new double[256];
    private MapGenBase u = new MapGenCaves();
    private BiomeBase[] v;
    double[] d;
    double[] e;
    double[] f;
    double[] g;
    double[] h;
    int[][] i = new int[32][32];
    private double[] w;

    public OtherWorldChunkProvider(World world, long i, SphereWorld instance) {
	this.p = world;
	this.j = new Random(i);
	this.k = new NoiseGeneratorOctaves(this.j, 16);
	this.l = new NoiseGeneratorOctaves(this.j, 16);
	this.m = new NoiseGeneratorOctaves(this.j, 8);
	this.n = new NoiseGeneratorOctaves(this.j, 4);
	this.o = new NoiseGeneratorOctaves(this.j, 4);
	this.a = new NoiseGeneratorOctaves(this.j, 10);
	this.b = new NoiseGeneratorOctaves(this.j, 16);
	this.c = new NoiseGeneratorOctaves(this.j, 8);
	this.plugin = instance;
    }

    public void a(int i1, int j1, byte[] abyte0, BiomeBase[] abiomebase, double[] adouble) {
	byte byte0 = 2;
	int k1 = byte0 + 1;
	byte byte1 = 33;
	int l1 = byte0 + 1;
	    q = a(q, i1 * byte0, 0, j1 * byte0, k1, byte1, l1);
	        for(int i2 = 0; i2 < byte0; i2++)
	        {
	            for(int j2 = 0; j2 < byte0; j2++)
	            {
	                for(int k2 = 0; k2 < 32; k2++)
	                {
	                    double d1 = 0.25D;
	                    double d2 = q[((i2 + 0) * l1 + (j2 + 0)) * byte1 + (k2 + 0)];
	                    double d3 = q[((i2 + 0) * l1 + (j2 + 1)) * byte1 + (k2 + 0)];
	                    double d4 = q[((i2 + 1) * l1 + (j2 + 0)) * byte1 + (k2 + 0)];
	                    double d5 = q[((i2 + 1) * l1 + (j2 + 1)) * byte1 + (k2 + 0)];
	                    double d6 = (q[((i2 + 0) * l1 + (j2 + 0)) * byte1 + (k2 + 1)] - d2) * d1;
	                    double d7 = (q[((i2 + 0) * l1 + (j2 + 1)) * byte1 + (k2 + 1)] - d3) * d1;
	                    double d8 = (q[((i2 + 1) * l1 + (j2 + 0)) * byte1 + (k2 + 1)] - d4) * d1;
	                    double d9 = (q[((i2 + 1) * l1 + (j2 + 1)) * byte1 + (k2 + 1)] - d5) * d1;
	                    for(int l2 = 0; l2 < 4; l2++)
	                    {
	                        double d10 = 0.125D;
	                        double d11 = d2;
	                        double d12 = d3;
	                        double d13 = (d4 - d2) * d10;
	                        double d14 = (d5 - d3) * d10;
	                        for(int i3 = 0; i3 < 8; i3++)
	                        {
	                            int j3 = i3 + i2 * 8 << 11 | 0 + j2 * 8 << 7 | k2 * 4 + l2;
	                            char c1 = '\200';
	                            double d15 = 0.125D;
	                            double d16 = d11;
	                            double d17 = (d12 - d11) * d15;
	                            for(int k3 = 0; k3 < 8; k3++)
	                            {
	                                int l3 = 0;
	                                if(d16 > 0.0D)
	                                    l3 = Block.STONE.id;
	                                abyte0[j3] = (byte)l3;
	                                j3 += c1;
	                                d16 += d17;
	                            }

	                            d11 += d13;
	                            d12 += d14;
	                        }

	                        d2 += d6;
	                        d3 += d7;
	                        d4 += d8;
	                        d5 += d9;
	                    }

	                }

	            }

	        }
    }

    public void a(int i1, int j1, byte[] abyte0, BiomeBase[] ajz) {
	      double d1 = 0.03125D;
	        r = n.a(r, i1 * 16, j1 * 16, 0.0D, 16, 16, 1, d1, d1, 1.0D);
	        s = n.a(s, i1 * 16, 109.0134D, j1 * 16, 16, 1, 16, d1, 1.0D, d1);
	        t = o.a(t, i1 * 16, j1 * 16, 0.0D, 16, 16, 1, d1 * 2D, d1 * 2D, d1 * 2D);
	        for(int k1 = 0; k1 < 16; k1++)
	        {
	            for(int l1 = 0; l1 < 16; l1++)
	            {
	        	BiomeBase jz1 = ajz[k1 + l1 * 16];
	                int i2 = (int)(t[k1 + l1 * 16] / 3D + 3D + j.nextDouble() * 0.25D);
	                int j2 = -1;
	                byte byte0 = jz1.p;
	                byte byte1 = jz1.q;
	                for(int k2 = 127; k2 >= 0; k2--)
	                {
	                    int l2 = (l1 * 16 + k1) * 128 + k2;
	                    byte byte2 = abyte0[l2];
	                    if(byte2 == 0)
	                    {
	                        j2 = -1;
	                        continue;
	                    }
	                    if(byte2 != Block.BEDROCK.id)
	                        continue;
	                    if(j2 == -1)
	                    {
	                        if(i2 <= 0)
	                        {
	                            byte0 = 0;
	                            byte1 = (byte)Block.STONE.id;
	                        }
	                        j2 = i2;
	                        if(k2 >= 0)
	                            abyte0[l2] = byte0;
	                        else
	                            abyte0[l2] = byte1;
	                        continue;
	                    }
	                    if(j2 <= 0)
	                        continue;
	                    j2--;
	                    abyte0[l2] = byte1;
	                    if(j2 == 0 && byte1 == Block.SAND.id)
	                    {
	                        j2 = j.nextInt(4);
	                        byte1 = (byte)Block.SANDSTONE.id;
	                    }
	                }
	            }
	        }
	    }

    public Chunk getChunkAt(int i, int j) {
	return this.getOrCreateChunk(i, j);
    }

 
    public Chunk getOrCreateChunk(int i, int j) {
	this.j.setSeed((long) i * 341873128712L + (long) j * 132897987541L);
	byte[] abyte = new byte['\u8000'];
	Chunk chunk = new Chunk(this.p, abyte, i, j);

	this.v = this.p.getWorldChunkManager().a(this.v, i * 16, j * 16, 16, 16);
	this.v = this.p.getWorldChunkManager().a(this.v, i * 16, j * 16, 16, 16);
	double[] adouble = this.p.getWorldChunkManager().rain;

	this.a(i, j, abyte, this.v, adouble);
	this.a(i, j, abyte, this.v);
	this.u.a(this, this.p, i, j, abyte);
	  	
	if (SphereWorldConfig.usefloor) {
		 for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
			   int k1 = 1;
			   int l1 = (l * 16 + k) * 128 + k1;
			   if (!SphereWorldConfig.nowater) {
			       abyte[l1] = (byte) 9;
			   } else {
			       abyte[l1] = (byte) 0; 
			   }
			   k1 = 0;
			   l1 = (l * 16 + k) * 128 + k1;
			   abyte[l1] = (byte) 7;
			}
		    } 
		} 
	plugin.oldchunks.AddChunkToList(SphereWorldConfig.world,i,j);
	chunk.initLighting();
	return chunk;
    }

    private double[] a(double[] ad, int i1, int j1, int k1, int l1, int i2, int j2) {
	{
	        if(ad == null)
	            ad = new double[l1 * i2 * j2];
	        double d1 = 684.41200000000003D;
	        double d2 = 684.41200000000003D;
	        double ad1[] = p.getWorldChunkManager().temperature;
	        double ad2[] = p.getWorldChunkManager().rain;
	        g = a.a(g, i1, k1, l1, j2, 1.121D, 1.121D, 0.5D);
	        h = b.a(h, i1, k1, l1, j2, 200D, 200D, 0.5D);
	        d1 *= 2D;
	        d = m.a(d, i1, j1, k1, l1, i2, j2, d1 / 80D, d2 / 160D, d1 / 80D);
	        e = k.a(e, i1, j1, k1, l1, i2, j2, d1, d2, d1);
	        f = l.a(f, i1, j1, k1, l1, i2, j2, d1, d2, d1);
	        int k2 = 0;
	        int l2 = 0;
	        int i3 = 16 / l1;
	        for(int j3 = 0; j3 < l1; j3++)
	        {
	            int k3 = j3 * i3 + i3 / 2;
	            for(int l3 = 0; l3 < j2; l3++)
	            {
	                int i4 = l3 * i3 + i3 / 2;
	                double d3 = ad1[k3 * 16 + i4];
	                double d4 = ad2[k3 * 16 + i4] * d3;
	                double d5 = 1.0D - d4;
	                d5 *= d5;
	                d5 *= d5;
	                d5 = 1.0D - d5;
	                double d6 = (g[l2] + 256D) / 512D;
	                d6 *= d5;
	                if(d6 > 1.0D)
	                    d6 = 1.0D;
	                double d7 = h[l2] / 8000D;
	                if(d7 < 0.0D)
	                    d7 = -d7 * 0.29999999999999999D;
	                d7 = d7 * 3D - 2D;
	                if(d7 > 1.0D)
	                    d7 = 1.0D;
	                d7 /= 8D;
	                d7 = 0.0D;
	                if(d6 < 0.0D)
	                    d6 = 0.0D;
	                d6 += 0.5D;
	                d7 = (d7 * (double)i2) / 16D;
	                l2++;
	                double d8 = (double)i2 / 2D;
	                for(int j4 = 0; j4 < i2; j4++)
	                {
	                    double d9 = 0.0D;
	                    double d10 = (((double)j4 - d8) * 8D) / d6;
	                    if(d10 < 0.0D)
	                        d10 *= -1D;
	                    double d11 = e[k2] / 512D;
	                    double d12 = f[k2] / 512D;
	                    double d13 = (d[k2] / 10D + 1.0D) / 2D;
	                    if(d13 < 0.0D)
	                        d9 = d11;
	                    else
	                    if(d13 > 1.0D)
	                        d9 = d12;
	                    else
	                        d9 = d11 + (d12 - d11) * d13;
	                    d9 -= 8D;
	                    int k4 = 32;
	                    if(j4 > i2 - k4)
	                    {
	                        double d14 = (float)(j4 - (i2 - k4)) / ((float)k4 - 1.0F);
	                        d9 = d9 * (1.0D - d14) + -30D * d14;
	                    }
	                    k4 = 8;
	                    if(j4 < k4)
	                    {
	                        double d15 = (float)(k4 - j4) / ((float)k4 - 1.0F);
	                        d9 = d9 * (1.0D - d15) + -30D * d15;
	                    }
	                    ad[k2] = d9;
	                    k2++;
	                }

	            }

	        }
	     }
	return ad;
    	}
	
    public boolean isChunkLoaded(int i, int j) {
	return true;
    }

    public void getChunkAt(IChunkProvider cj1, int i1, int j1) {
	BlockSand.instaFall = true;
	      int k1 = i1 * 16;
	        int l1 = j1 * 16;
	        BiomeBase jz1 = this.p.getWorldChunkManager().getBiome(k1 + 16, l1 + 16);
	        j.setSeed(p.getSeed());
	        long l2 = (j.nextLong() / 2L) * 2L + 1L;
	        long l3 = (j.nextLong() / 2L) * 2L + 1L;
	        j.setSeed((long)i1 * l2 + (long)j1 * l3 ^ p.getSeed());
	        double d1 = 0.25D;
	        if(j.nextInt(4) == 0)
	        {
	            int i2 = k1 + j.nextInt(16) + 8;
	            int l5 = j.nextInt(128);
	            int i9 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLakes(Block.STATIONARY_WATER.id)).a(p, j, i2, l5, i9);
	        }
	        if(j.nextInt(8) == 0)
	        {
	            int j2 = k1 + j.nextInt(16) + 8;
	            int i6 = j.nextInt(j.nextInt(120) + 8);
	            int j9 = l1 + j.nextInt(16) + 8;
	            if(i6 < 64 || j.nextInt(10) == 0)
	        	(new WorldGenLakes(Block.STATIONARY_LAVA.id)).a(p, j, j2, i6, j9);
	        }
	        for(int k2 = 0; k2 < 8; k2++)
	        {
	            int j6 = k1 + j.nextInt(16) + 8;
	            int k9 = j.nextInt(128);
	            int i14 = l1 + j.nextInt(16) + 8;
	            (new WorldGenDungeons()).a(p, j, j6, k9, i14);
	        }

	        for(int i3 = 0; i3 < 10; i3++)
	        {
	            int k6 = k1 + j.nextInt(16);
	            int l9 = j.nextInt(128);
	            int j14 = l1 + j.nextInt(16);
	            (new WorldGenClay(32)).a(p, j, k6, l9, j14);
	        }

	        for(int j3 = 0; j3 < 20; j3++)
	        {
	            int l6 = k1 + j.nextInt(16);
	            int i10 = j.nextInt(128);
	            int k14 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.DIRT.id, 32)).a(p, j, l6, i10, k14);
	        }

	        for(int k3 = 0; k3 < 10; k3++)
	        {
	            int i7 = k1 + j.nextInt(16);
	            int j10 = j.nextInt(128);
	            int l14 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.GRAVEL.id, 32)).a(p, j, i7, j10, l14);
	        }

	        for(int i4 = 0; i4 < 20; i4++)
	        {
	            int j7 = k1 + j.nextInt(16);
	            int k10 = j.nextInt(128);
	            int i15 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.COAL_ORE.id, 16)).a(p, j, j7, k10, i15);
	        }

	        for(int j4 = 0; j4 < 20; j4++)
	        {
	            int k7 = k1 + j.nextInt(16);
	            int l10 = j.nextInt(64);
	            int j15 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.IRON_ORE.id, 8)).a(p, j, k7, l10, j15);
	        }

	        for(int k4 = 0; k4 < 2; k4++)
	        {
	            int l7 = k1 + j.nextInt(16);
	            int i11 = j.nextInt(32);
	            int k15 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.GOLD_ORE.id, 8)).a(p, j, l7, i11, k15);
	        }

	        for(int l4 = 0; l4 < 8; l4++)
	        {
	            int i8 = k1 + j.nextInt(16);
	            int j11 = j.nextInt(16);
	            int l15 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.REDSTONE_ORE.id, 7)).a(p, j, i8, j11, l15);
	        }

	        for(int i5 = 0; i5 < 1; i5++)
	        {
	            int j8 = k1 + j.nextInt(16);
	            int k11 = j.nextInt(16);
	            int i16 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.DIAMOND_ORE.id, 7)).a(p, j, j8, k11, i16);
	        }

	        for(int j5 = 0; j5 < 1; j5++)
	        {
	            int k8 = k1 + j.nextInt(16);
	            int l11 = j.nextInt(16) + j.nextInt(16);
	            int j16 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.LAPIS_ORE.id, 6)).a(p, j, k8, l11, j16);
	        }

	        d1 = 0.5D;
	        int k5 = (int)((c.a((double)k1 * d1, (double)l1 * d1) / 8D + j.nextDouble() * 4D + 4D) / 3D);
	        int l8 = 0;
	        if(j.nextInt(10) == 0)
	            l8++;
	        if(jz1 == BiomeBase.FOREST)
	            l8 += k5 + 5;
	        if(jz1 == BiomeBase.RAINFOREST)
	            l8 += k5 + 5;
	        if(jz1 == BiomeBase.SEASONAL_FOREST)
	            l8 += k5 + 2;
	        if(jz1 == BiomeBase.TAIGA)
	            l8 += k5 + 5;
	        if(jz1 == BiomeBase.DESERT)
	            l8 -= 20;
	        if(jz1 == BiomeBase.TUNDRA)
	            l8 -= 20;
	        if(jz1 == BiomeBase.PLAINS)
	            l8 -= 20;
	        for(int i12 = 0; i12 < l8; i12++)
	        {
	            int k16 = k1 + j.nextInt(16) + 8;
	            int j19 = l1 + j.nextInt(16) + 8;
	            WorldGenerator pc1 = jz1.a(j);
	            pc1.a(1.0D, 1.0D, 1.0D);
	            pc1.a(p, j, k16, p.getHighestBlockYAt(k16, j19), j19);
	        }

	        for(int j12 = 0; j12 < 2; j12++)
	        {
	            int l16 = k1 + j.nextInt(16) + 8;
	            int k19 = j.nextInt(128);
	            int i22 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.YELLOW_FLOWER.id)).a(p, j, l16, k19, i22);
	        }

	        if(j.nextInt(2) == 0)
	        {
	            int k12 = k1 + j.nextInt(16) + 8;
	            int i17 = j.nextInt(128);
	            int l19 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.RED_ROSE.id)).a(p, j, k12, i17, l19);
	        }
	        if(j.nextInt(4) == 0)
	        {
	            int l12 = k1 + j.nextInt(16) + 8;
	            int j17 = j.nextInt(128);
	            int i20 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.BROWN_MUSHROOM.id)).a(p, j, l12, j17, i20);
	        }
	        if(j.nextInt(8) == 0)
	        {
	            int i13 = k1 + j.nextInt(16) + 8;
	            int k17 = j.nextInt(128);
	            int j20 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.RED_MUSHROOM.id)).a(p, j, i13, k17, j20);
	        }
	        for(int j13 = 0; j13 < 10; j13++)
	        {
	            int l17 = k1 + j.nextInt(16) + 8;
	            int k20 = j.nextInt(128);
	            int j22 = l1 + j.nextInt(16) + 8;
	            (new WorldGenReed()).a(p, j, l17, k20, j22);
	        }

	        if(j.nextInt(32) == 0)
	        {
	            int k13 = k1 + j.nextInt(16) + 8;
	            int i18 = j.nextInt(128);
	            int l20 = l1 + j.nextInt(16) + 8;
	            (new WorldGenPumpkin()).a(p, j, k13, i18, l20);
	        }
	        int l13 = 0;
	        if(jz1 == BiomeBase.DESERT)
	            l13 += 10;
	        for(int j18 = 0; j18 < l13; j18++)
	        {
	            int i21 = k1 + j.nextInt(16) + 8;
	            int k22 = j.nextInt(128);
	            int k23 = l1 + j.nextInt(16) + 8;
	            (new WorldGenCactus()).a(p, j, i21, k22, k23);
	        }

	        for(int k18 = 0; k18 < 50; k18++)
	        {
	            int j21 = k1 + j.nextInt(16) + 8;
	            int l22 = j.nextInt(j.nextInt(120) + 8);
	            int l23 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLiquids(Block.WATER.id)).a(p, j, j21, l22, l23);
	        }

	        for(int l18 = 0; l18 < 20; l18++)
	        {
	            int k21 = k1 + j.nextInt(16) + 8;
	            int i23 = j.nextInt(j.nextInt(j.nextInt(112) + 8) + 8);
	            int i24 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLiquids(Block.LAVA.id)).a(p, j, k21, i23, i24);
	        }

	        // Generate some Grass Blocks and some Longgrass
	        byte byte1 = 0;
	        if(jz1 == BiomeBase.FOREST)
	            byte1 = 2;
	        if(jz1 == BiomeBase.RAINFOREST)
	            byte1 = 8;
	        if(jz1 == BiomeBase.SEASONAL_FOREST)
	            byte1 = 4;
	        if(jz1 == BiomeBase.TAIGA)
	            byte1 = 2;
	        if(jz1 == BiomeBase.PLAINS)
	            byte1 = 14;

	        for(int l15 = 0; l15 < byte1; l15++)
	        {
	            int l20 = k1 + j.nextInt(16) + 8;
	            //int k23 = j.nextInt(128);
	            int j25 = l1 + j.nextInt(16) + 8;
	            (new WorldGenGrass(Block.GRASS.id)).a(p, j, l20, 0, j25);
	        }
	        
	        for(int l15 = 0; l15 < byte1; l15++)
	        {
	            byte byte2 = 1;
	            if(jz1 == BiomeBase.RAINFOREST && j.nextInt(3) != 0)
	                byte2 = 2;
	            int l20 = k1 + j.nextInt(16) + 8;
	            //int k23 = j.nextInt(128);
	            int j25 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLongGrass(Block.LONG_GRASS.id, byte2)).a(p, j, l20, 0, j25);
	        }
	        
	        
	        w = p.getWorldChunkManager().a(w, k1 + 8, l1 + 8, 16, 16);
	        for(int i19 = k1 + 8; i19 < k1 + 8 + 16; i19++)
	        {
	            for(int l21 = l1 + 8; l21 < l1 + 8 + 16; l21++)
	            {
	                int j23 = i19 - (k1 + 8);
	                int j24 = l21 - (l1 + 8);
	                int k24 = p.e(i19, l21);
	                double d2 = w[j23 * 16 + j24] - ((double)(k24 - 64) / 64D) * 0.29999999999999999D;
	                if(d2 < 0.5D && k24 > 0 && k24 < 128 && p.e(i19, k24, l21) 
	                	&& p.getMaterial(i19, k24 - 1, l21).isSolid() 
	                	&& p.getMaterial(i19, k24 - 1, l21) != Material.ICE)
	                    p.setTypeId(i19, k24, l21, Block.SNOW.id);
	            }
	        }
	        BlockSand.instaFall = false;
	    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
	return true;
    }

    public boolean unloadChunks() {
	return false;
    }

    public boolean b() {
	return true;
    }

	public boolean canSave() {
		return false;
	}
}
