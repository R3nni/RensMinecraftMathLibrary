package com.ren.rensMinecraftMathLibrary.Shapes;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class Cylinder {
    private Location center;
    private double radiusX;
    private double radiusY;
    private double height;
    public Cylinder(Location center, double radiusX, double radiusY, double height) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.height = height;
    }

    public Location getCenter() {
        return center;
    }
    public double getRadiusX() {
        return radiusX;
    }
    public double getRadiusY() {
        return radiusY;
    }
    public double getHeight() {
        return height;
    }

    public List<Location> getBlocks(boolean isFilled) {
        List<Location> blocks = new ArrayList<>();
        World world = center.getWorld();
        int cx = center.getBlockX();
        int cy = center.getBlockY();
        int cz = center.getBlockZ();
        int rX = (int) Math.ceil(radiusX);
        int rY = (int) Math.ceil(radiusY);
        int h = (int) Math.ceil(height);

        for (int x = cx - rX; x <= cx + rX; x++) {
            for (int y = cy - rY; y <= cy + rY; y++) {
                for (int z = cz - h; z <= cz + h; z++) {
                    double dx = x - cx;
                    double dy = y - cy;
                    double dz = z - cz;
                    double distSq = dx*dx + dy*dy + dz*dz;
                    if (isFilled) {
                        if (distSq <= radiusX*radiusX && distSq <= radiusY*radiusY && Math.abs(dz) <= height) {
                            blocks.add(new Location(world, x, y, z));
                        }
                    } else {
                        if (distSq <= radiusX*radiusX && distSq <= radiusY*radiusY && Math.abs(dz) <= height-1) {
                            blocks.add(new Location(world, x, y, z));
                        }
                    }
                }
            }
        }
        return blocks;
    }
}
