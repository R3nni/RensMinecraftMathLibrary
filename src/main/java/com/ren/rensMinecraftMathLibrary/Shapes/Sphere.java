package com.ren.rensMinecraftMathLibrary.Shapes;

import org.bukkit.Location;
import org.bukkit.World;
import java.util.ArrayList;
import java.util.List;

/**
 * Sphere class from RensMinecraftMathLibrary (RMML)
 */

public class Sphere {
    private Location center;
    private double radius;

    public Sphere(Location center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Location getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Returns a List of all block Locations inside the sphere
     * @param isFilled If true, returns all blocks inside the sphere, if false, returns all blocks outside the sphere
     */
    public List<Location> getBlocks(boolean isFilled) {
        List<Location> blocks = new ArrayList<>();
        World world = center.getWorld();
        int cx = center.getBlockX();
        int cy = center.getBlockY();
        int cz = center.getBlockZ();
        int r = (int) Math.ceil(radius);

        for (int x = cx - r; x <= cx + r; x++) {
            for (int y = cy - r; y <= cy + r; y++) {
                for (int z = cz - r; z <= cz + r; z++) {
                    double dx = x - cx;
                    double dy = y - cy;
                    double dz = z - cz;
                    double distSq = dx*dx + dy*dy + dz*dz;

                    if (isFilled) {
                        if (distSq <= radius*radius) {
                            blocks.add(new Location(world, x, y, z));
                        }
                    } else {
                        if (distSq <= radius*radius && distSq > (radius-1)*(radius-1)) {
                            blocks.add(new Location(world, x, y, z));
                        }
                    }
                }
            }
        }
        return blocks;
    }

}

