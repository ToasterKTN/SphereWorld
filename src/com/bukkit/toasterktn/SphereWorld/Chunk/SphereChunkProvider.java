package com.bukkit.toasterktn.SphereWorld.Chunk;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import org.bukkit.util.Vector;

import com.bukkit.toasterktn.SphereWorld.Sphere;
import com.bukkit.toasterktn.SphereWorld.SphereWorld;
import com.bukkit.toasterktn.SphereWorld.Spheres;
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

public class SphereChunkProvider implements IChunkProvider {
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
    Spheres ss;
    private Vector vt = null;
// checked
    public SphereChunkProvider(World world, long i, Spheres ss, SphereWorld instance) {
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
	this.ss = ss;
	this.plugin = instance;
    }

    public void a(int i1, int j1, byte[] abyte0, BiomeBase[] abiomebase, double[] ad) {
	byte byte0 = 4;
	int k1 = byte0 + 1;
	byte byte1 = 64;
	byte byte2 = 17;
	int l1 = byte0 + 1;
	
	q = a(q, i1 * byte0, 0, j1 * byte0, k1, byte2, l1);
	        for(int i2 = 0; i2 < byte0; i2++)
	        {
	            for(int j2 = 0; j2 < byte0; j2++)
	            {
	                for(int k2 = 0; k2 < 16; k2++)
	                {
	                    double d1 = 0.125D;
	                    double d2 = q[((i2 + 0) * l1 + (j2 + 0)) * byte2 + (k2 + 0)];
	                    double d3 = q[((i2 + 0) * l1 + (j2 + 1)) * byte2 + (k2 + 0)];
	                    double d4 = q[((i2 + 1) * l1 + (j2 + 0)) * byte2 + (k2 + 0)];
	                    double d5 = q[((i2 + 1) * l1 + (j2 + 1)) * byte2 + (k2 + 0)];
	                    double d6 = (q[((i2 + 0) * l1 + (j2 + 0)) * byte2 + (k2 + 1)] - d2) * d1;
	                    double d7 = (q[((i2 + 0) * l1 + (j2 + 1)) * byte2 + (k2 + 1)] - d3) * d1;
	                    double d8 = (q[((i2 + 1) * l1 + (j2 + 0)) * byte2 + (k2 + 1)] - d4) * d1;
	                    double d9 = (q[((i2 + 1) * l1 + (j2 + 1)) * byte2 + (k2 + 1)] - d5) * d1;
	                    for(int l2 = 0; l2 < 8; l2++)
	                    {
	                        double d10 = 0.25D;
	                        double d11 = d2;
	                        double d12 = d3;
	                        double d13 = (d4 - d2) * d10;
	                        double d14 = (d5 - d3) * d10;
	                        for(int i3 = 0; i3 < 4; i3++)
	                        {
	                            int j3 = i3 + i2 * 4 << 11 | 0 + j2 * 4 << 7 | k2 * 8 + l2;
	                            char c1 = '\200';
	                            double d15 = 0.25D;
	                            double d16 = d11;
	                            double d17 = (d12 - d11) * d15;
	                            for(int k3 = 0; k3 < 4; k3++)
	                            {
	                        	double d18 = ad[(i2 * 4 + i3) * 16 + (j2 * 4 + k3)];
	                                int l3 = 0;
	                                if(k2 * 8 + l2 < byte1)
	                                    if(d18 < 0.5D && k2 * 8 + l2 >= byte1 - 1)
	                                        l3 = Block.ICE.id;
	                                    else
	                                        l3 = Block.WATER.id;
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
	      byte byte0 = 64;
	      double d1 = 0.03125D;
	        r = n.a(r, i1 * 16, j1 * 16, 0.0D, 16, 16, 1, d1, d1, 1.0D);
	        s = n.a(s, i1 * 16, 109.0134D, j1 * 16, 16, 1, 16, d1, 1.0D, d1);
	        t = o.a(t, i1 * 16, j1 * 16, 0.0D, 16, 16, 1, d1 * 2D, d1 * 2D, d1 * 2D);
	        for(int k1 = 0; k1 < 16; k1++)
	        {
	            for(int l1 = 0; l1 < 16; l1++)
	            {
	        	BiomeBase jz1 = ajz[k1 + l1 * 16];
	        	boolean flag = r[k1 + l1 * 16] + j.nextDouble() * 0.20000000000000001D > 0.0D;
	                boolean flag1 = s[k1 + l1 * 16] + j.nextDouble() * 0.20000000000000001D > 3D;
	                int i2 = (int)(t[k1 + l1 * 16] / 3D + 3D + j.nextDouble() * 0.25D);
	                int j2 = -1;
	                byte byte1 = jz1.p;
	                byte byte2 = jz1.q;
	                for(int k2 = 127; k2 >= 0; k2--)
	                {
	                    int l2 = (l1 * 16 + k1) * 128 + k2;
	                    if(k2 <= 0 + j.nextInt(5))
	                    {
	                        abyte0[l2] = (byte)Block.BEDROCK.id;
	                        continue;
	                    }
	                    byte byte3 = abyte0[l2];
	                    if(byte3 == 0)
	                    {
	                        j2 = -1;
	                        continue;
	                    }
	                    if(byte3 != Block.STONE.id)
	                        continue;
	                    if(j2 == -1)
	                    {
	                        if(i2 <= 0)
	                        {
	                            byte1 = 0;
	                            byte2 = (byte)Block.STONE.id;
	                        } else
	                        if(k2 >= byte0 - 4 && k2 <= byte0 + 1)
	                        {
	                            byte1 = jz1.p;
	                            byte2 = jz1.q;
	                            if(flag1)
	                                byte1 = 0;
	                            if(flag1)
	                                byte2 = (byte)Block.GRAVEL.id;
	                            if(flag)
	                                byte1 = (byte)Block.SAND.id;
	                            if(flag)
	                                byte2 = (byte)Block.SAND.id;
	                        }
	                        if(k2 < byte0 && byte1 == 0)
	                            byte1 = (byte)Block.WATER.id;
	                        j2 = i2;
	                        if(k2 >= byte0 - 1)
	                            abyte0[l2] = byte1;
	                        else
	                            abyte0[l2] = byte2;
	                        continue;
	                    }
	                    if(j2 <= 0)
	                        continue;
	                    j2--;
	                    abyte0[l2] = byte2;
	                    if(j2 == 0 && byte2 == Block.SAND.id)
	                    {
	                        j2 = j.nextInt(4);
	                        byte2 = (byte)Block.SANDSTONE.id;
	                    }
	                }

	            }
	        }
	    }

    public Chunk getChunkAt(int i, int j) {
	return this.getOrCreateChunk(i, j);
    }

    private Comparator<Sphere> COMPARATOR = new Comparator<Sphere>() 
        {
        // This is where the sorting happens.
            public int compare(Sphere o1, Sphere o2)
            {
                return (int) ((o1.getV().distance(vt) - o1.getSize()) - (o2.getV().distance(vt) - o2.getSize()));
            }
        };

    
    public Chunk getOrCreateChunk(int i, int j) {
	this.j.setSeed((long) i * 0x9939f508L + (long)j * 0xf1565bd5L);
	byte[] abyte = new byte['\u8000'];
	Chunk chunk = new Chunk(this.p, abyte, i, j);
	this.v = this.p.getWorldChunkManager().a(this.v, i * 16, j * 16, 16, 16);
	double[] adouble = this.p.getWorldChunkManager().rain;
	this.a(i, j, abyte, this.v, adouble);
	this.a(i, j, abyte, this.v);
	this.u.a(this, this.p, i, j, abyte);
	BlockSand.instaFall = true;
	boolean hassphere = false;
	Spheres ts = new Spheres();
	ts.GetSphereList().clear();
	for (Sphere s : ss.GetSphereList()) {
	    if (s.getX() > i * 16 - SphereWorldConfig.maxradius && s.getX() < i * 16 + SphereWorldConfig.maxradius + 16) {
		if (s.getZ() > j * 16 - SphereWorldConfig.maxradius && s.getZ() < j * 16 + SphereWorldConfig.maxradius + 16) {
		    ts.AddSphereToList(s);
		    hassphere = true;
		}
	    }
	}
	if (hassphere) {
	    if (SphereWorldConfig.userandomglass && ts.GetSphereList().size() > 1) {
		// Lets sort this to get the right random	
		vt = new Vector((double) i * 16 + 8 , (double) 64, (double) j * 16 + 8);
		Collections.sort(ts.GetSphereList(), COMPARATOR);
	    }
	    if (SphereWorldConfig.useglass || (SphereWorldConfig.userandomglass && (ts.GetSphereList().get(0).getSize() % 2 == 1))) {
		    for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
			    for (int k1 = 127; k1 >= 2; --k1) {
				int l1 = (l * 16 + k) * 128 + k1;
				 boolean keep = false;
				 for (Sphere s : ts.GetSphereList()) {
				     if (s.getV().distance(new Vector((double) i * 16 + l, (double) k1, (double) j * 16 + k)) < s.getSize()) {
					 keep = true;
					 if (s.getV().distance(new Vector((double) i * 16 + l, (double) k1, (double) j * 16 + k)) > s.getSize() - 1.1) {
					    if (SphereWorldConfig.useglow) {
						if (s.getX() == i* 16 + l || s.getZ() ==  j * 16 + k) {
						    abyte[l1] = (byte) 89;
						} else {
						    abyte[l1] = (byte) SphereWorldConfig.glassblock;
					    	}
					    } else {
						abyte[l1] = (byte) SphereWorldConfig.glassblock;
					    }					     
					 }
				     }
				 }
				 if (!keep) abyte[l1] = (byte) 0x0;
			    }
			   
			}
		    }
	    } else if (SphereWorldConfig.usehalfglass || (SphereWorldConfig.userandomglass && (ts.GetSphereList().get(0).getSize() % 2 == 0))) {
		 for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
			    for (int k1 = 127; k1 >= 64; --k1) {
				int l1 = (l * 16 + k) * 128 + k1;
				 boolean keep = false;
				 for (Sphere s : ts.GetSphereList()) {
				     if (s.getV().distance(new Vector((double) i * 16 + l, (double) k1, (double) j * 16 + k)) < s.getSize()) {
					 keep = true;
				     }
				 }
				 if (!keep) abyte[l1] = (byte) 0x0;
			    }
			   
			}
		    }
		 for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
			    for (int k1 = 64; k1 >= 2; --k1) {
				int l1 = (l * 16 + k) * 128 + k1;
				 boolean keep = false;
				 for (Sphere s : ts.GetSphereList()) {
				     if (s.getV().distance(new Vector((double) i * 16 + l, (double) k1, (double) j * 16 + k)) < s.getSize()) {
					 keep = true;
					 if (s.getV().distance(new Vector((double) i * 16 + l, (double) k1, (double) j * 16 + k)) > s.getSize() - 1.1) {
					     if (SphereWorldConfig.useglow) {
						if (s.getX() == i* 16 + l || s.getZ() ==  j * 16 + k) {
						    abyte[l1] = (byte) 89;
						} else {
						    abyte[l1] = (byte) SphereWorldConfig.glassblock;
					    	}
					    } else {
						abyte[l1] = (byte) SphereWorldConfig.glassblock;
					    }		
					 }
				     }
				 }
				 if (!keep) abyte[l1] = (byte) 0x0;
			    }
			   
			}
		   }
		
	    }
	    else {
		for (int k = 0; k < 16; ++k) {
			for (int l = 0; l < 16; ++l) {
			    for (int k1 = 127; k1 >= 2; --k1) {
				int l1 = (l * 16 + k) * 128 + k1;
				 boolean keep = false;
				 for (Sphere s : ts.GetSphereList()) {
				     if (s.getV().distance(new Vector((double) i * 16 + l, (double) k1, (double) j * 16 + k)) < s.getSize()) {
					 keep = true;
				     }
				 }
				 if (!keep) abyte[l1] = (byte) 0x0;
			    }
			   
			}
		}
	    }	  
	} else {
	    for (int k = 0; k < 16; ++k) {
		for (int l = 0; l < 16; ++l) {
		    for (int k1 = 127; k1 >= 2; --k1) {
			int l1 = (l * 16 + k) * 128 + k1;
			abyte[l1] = (byte) 0x0;
		    }
		}
	    } 
	}
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
	} else{
	    for (int k = 0; k < 16; ++k) {
		for (int l = 0; l < 16; ++l) {
		   int k1 = 1;
		   	int l1 = (l * 16 + k) * 128 + k1;
			abyte[l1] = (byte) 0x0;
			k1 = 0;
			l1 = (l * 16 + k) * 128 + k1;
			abyte[l1] = (byte) 0x0;
		}
	    } 
	}
	  
	BlockSand.instaFall = false;
	chunk.initLighting();
	plugin.oldchunks.AddChunkToList(SphereWorldConfig.world,i,j);
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
	                if(d7 > 1.0D) {
	                    d7 = 1.0D;
	                    if(d7 < -1D)
	                        d7 = -1D;
	                    d7 /= 1.3999999999999999D;
	                    d7 /= 2D;
	                    d6 = 0.0D;
	                } else
	                {
	                    if(d7 > 1.0D)
	                        d7 = 1.0D;
	                    d7 /= 8D;
	                }
	                if(d6 < 0.0D)
	                    d6 = 0.0D;
	                d6 += 0.5D;
	                d7 = (d7 * (double)i2) / 16D;
	                double d8 = (double)i2 / 2D+ d7 * 4D;
	                l2++;
	                for(int j4 = 0; j4 < i2; j4++)
	                {
	                    double d9 = 0.0D;
	                    double d10 = (((double)j4 - d8) * 12D) / d6;
	                    if(d10 < 0.0D)
	                        d10 *= 4D;
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
	                    d9 -= d10;
	                    if(j4 > i2 - 4)
	                    {
	                        double d14 = (float)(j4 - (i2 - 4)) / 3F;
	                        d9 = d9 * (1.0D - d14) + -10D * d14;
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
	            int j12 = l1 + j.nextInt(16) + 8;
	            (new WorldGenDungeons()).a(p, j, j6, k9, j12);
	        }

	        for(int i3 = 0; i3 < 10; i3++)
	        {
	            int k6 = k1 + j.nextInt(16);
	            int l9 = j.nextInt(128);
	            int k12 = l1 + j.nextInt(16);
	            (new WorldGenClay(32)).a(p, j, k6, l9, k12);
	        }

	        for(int j3 = 0; j3 < 20; j3++)
	        {
	            int l6 = k1 + j.nextInt(16);
	            int i10 = j.nextInt(128);
	            int l12 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.DIRT.id, 32)).a(p, j, l6, i10, l12);
	        }

	        for(int k3 = 0; k3 < 10; k3++)
	        {
	            int i7 = k1 + j.nextInt(16);
	            int j10 = j.nextInt(128);
	            int i13 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.GRAVEL.id, 32)).a(p, j, i7, j10, i13);
	        }

	        for(int i4 = 0; i4 < 20; i4++)
	        {
	            int j7 = k1 + j.nextInt(16);
	            int k10 = j.nextInt(128);
	            int j13 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.COAL_ORE.id, 16)).a(p, j, j7, k10, j13);
	        }

	        for(int j4 = 0; j4 < 20; j4++)
	        {
	            int k7 = k1 + j.nextInt(16);
	            int l10 = j.nextInt(64);
	            int k13 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.IRON_ORE.id, 8)).a(p, j, k7, l10, k13);
	        }

	        for(int k4 = 0; k4 < 2; k4++)
	        {
	            int l7 = k1 + j.nextInt(16);
	            int i11 = j.nextInt(32);
	            int l13 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.GOLD_ORE.id, 8)).a(p, j, l7, i11, l13);
	        }

	        for(int l4 = 0; l4 < 8; l4++)
	        {
	            int i8 = k1 + j.nextInt(16);
	            int j11 = j.nextInt(16);
	            int i14 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.REDSTONE_ORE.id, 7)).a(p, j, i8, j11, i14);
	        }

	        for(int i5 = 0; i5 < 1; i5++)
	        {
	            int j8 = k1 + j.nextInt(16);
	            int k11 = j.nextInt(16);
	            int j14 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.DIAMOND_ORE.id, 7)).a(p, j, j8, k11, j14);
	        }

	        for(int j5 = 0; j5 < 1; j5++)
	        {
	            int k8 = k1 + j.nextInt(16);
	            int l11 = j.nextInt(16) + j.nextInt(16);
	            int k14 = l1 + j.nextInt(16);
	            (new WorldGenMinable(Block.LAPIS_ORE.id, 6)).a(p, j, k8, l11, k14);
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
	            int l14 = k1 + j.nextInt(16) + 8;
	            int j15 = l1 + j.nextInt(16) + 8;
	            WorldGenerator pc1 = jz1.a(j);
	            pc1.a(1.0D, 1.0D, 1.0D);
	            pc1.a(p, j, l14, p.getHighestBlockYAt(l14, j15), j15);
	        }

	        byte byte0 = 0;
	        if(jz1 == BiomeBase.FOREST)
	            byte0 = 2;
	        if(jz1 == BiomeBase.SEASONAL_FOREST)
	            byte0 = 4;
	        if(jz1 == BiomeBase.TAIGA)
	            byte0 = 2;
	        if(jz1 == BiomeBase.PLAINS)
	            byte0 = 3;
	        for(int i15 = 0; i15 < byte0; i15++)
	        {
	            int k15 = k1 + j.nextInt(16) + 8;
	            int l17 = j.nextInt(128);
	            int k20 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.YELLOW_FLOWER.id)).a(p, j, k15, l17, k20);
	        }

	        
	        // NO LONG GRASS TILL NOW , WE MUST MAKE IT OUR OWN
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
	            byte byte2 = 1;
	            if(jz1 == BiomeBase.RAINFOREST && j.nextInt(3) != 0)
	                byte2 = 2;
	            int l20 = k1 + j.nextInt(16) + 8;
	            //int k23 = j.nextInt(128);
	            int j25 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLongGrass(Block.LONG_GRASS.id, byte2)).a(p, j, l20, 0, j25);
	        }
	        // NO DEAD BUSHES..., WE MUST MAKE IT OURSELF
	        byte1 = 0;
	        if(jz1 == BiomeBase.TAIGA)
	            byte1 = 2;
	    
	        for(int i16 = 0; i16 < byte1; i16++)
	        {
	            int i18 = k1 + j.nextInt(16) + 8;
	            //int i21 = j.nextInt(128);
	            int l23 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLongGrass(Block.DEAD_BUSH.id, byte1)).a(p, j, i18, 0, l23);
	        }

	        if(j.nextInt(2) == 0)
	        {
	            int j16 = k1 + j.nextInt(16) + 8;
	            int j18 = j.nextInt(128);
	            int j21 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.RED_ROSE.id)).a(p, j, j16, j18, j21);
	        }
	        if(j.nextInt(4) == 0)
	        {
	            int k16 = k1 + j.nextInt(16) + 8;
	            int k18 = j.nextInt(128);
	            int k21 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.BROWN_MUSHROOM.id)).a(p, j, k16, k18, k21);
	        }
	        if(j.nextInt(8) == 0)
	        {
	            int l16 = k1 + j.nextInt(16) + 8;
	            int l18 = j.nextInt(128);
	            int l21 = l1 + j.nextInt(16) + 8;
	            (new WorldGenFlowers(Block.RED_MUSHROOM.id)).a(p, j, l16, l18, l21);
	        }
	        for(int i17 = 0; i17 < 10; i17++)
	        {
	            int i19 = k1 + j.nextInt(16) + 8;
	            int i22 = j.nextInt(128);
	            int i24 = l1 + j.nextInt(16) + 8;
	            (new WorldGenReed()).a(p, j, i19, i22, i24);
	        }

	        if(j.nextInt(32) == 0)
	        {
	            int j17 = k1 + j.nextInt(16) + 8;
	            int j19 = j.nextInt(128);
	            int j22 = l1 + j.nextInt(16) + 8;
	            (new WorldGenPumpkin()).a(p, j, j17, j19, j22);
	        }
	        int k17 = 0;
	        if(jz1 == BiomeBase.DESERT)
	            k17 += 10;
	
	        for(int k19 = 0; k19 < k17; k19++)
	        {
	            int k22 = k1 + j.nextInt(16) + 8;
	            int j24 = j.nextInt(128);
	            int k25 = l1 + j.nextInt(16) + 8;
	            (new WorldGenCactus()).a(p, j, k22, j24, k25);
	        }

	        for(int l19 = 0; l19 < 50; l19++)
	        {
	            int l22 = k1 + j.nextInt(16) + 8;
	            int k24 = j.nextInt(j.nextInt(120) + 8);
	            int l25 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLiquids(Block.WATER.id)).a(p, j, l22, k24, l25);
	        }

	        for(int i20 = 0; i20 < 20; i20++)
	        {
	            int i23 = k1 + j.nextInt(16) + 8;
	            int l24 = j.nextInt(j.nextInt(j.nextInt(112) + 8) + 8);
	            int i26 = l1 + j.nextInt(16) + 8;
	            (new WorldGenLiquids(Block.LAVA.id)).a(p, j, i23, l24, i26);
	        }

	        w = p.getWorldChunkManager().a(w, k1 + 8, l1 + 8, 16, 16);
	        for(int j20 = k1 + 8; j20 < k1 + 8 + 16; j20++)
	        {
	            for(int j23 = l1 + 8; j23 < l1 + 8 + 16; j23++)
	            {
	                int i25 = j20 - (k1 + 8);
	                int j26 = j23 - (l1 + 8);
	                int k26 = p.e(j20, j23);
	                double d2 = w[i25 * 16 + j26] - ((double)(k26 - 64) / 64D) * 0.29999999999999999D;
	                if(d2 < 0.5D && k26 > 0 && k26 < 128 && p.e(j20, k26, j23) && 
	                	p.getMaterial(j20, k26 - 1, j23).isSolid() && 
	                	p.getMaterial(j20, k26 - 1, j23) != Material.ICE)
	                    p.setTypeId(j20, k26, j23, Block.SNOW.id);
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

	@Override
	public boolean canSave() {
		// TODO Auto-generated method stub
		return false;
	}
}
