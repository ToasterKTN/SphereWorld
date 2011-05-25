package com.bukkit.toasterktn.SphereWorld;

import org.bukkit.util.Vector;

public class Sphere  implements java.io.Serializable {

    private static final long serialVersionUID = 1570149742281426141L;
    private String world;
    private double x;
    private double y;
    private double z;
    private int size;
    private transient Vector v;
    
    public Vector getV() {
        return v;
    }
    public void setV(Vector v) {
        this.v = v;
    }
    public String getWorld() {
        return world;
    }
    public void setWorld(String world) {
        this.world = world;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public void setZ(double z) {
        this.z = z;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
