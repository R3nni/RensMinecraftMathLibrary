package com.ren.rensMinecraftMathLibrary.Shapes;

import org.bukkit.Location;
import org.bukkit.World;
import java.util.ArrayList;
import java.util.List;

/**
 * Toroid (Torus) class from RensMinecraftMathLibrary (RMML)
 */
public class Toroid {
    private Location center;
    private double majorRadius; // R: distance from center to tube center
    private double minorRadius; // r: radius of tube

    public Toroid(Location center, double majorRadius, double minorRadius) {
        this.center = center;
        this.majorRadius = majorRadius;
        this.minorRadius = minorRadius;
    }

    public Location getCenter() {
        return center;
    }

    public double getMajorRadius() {
        return majorRadius;
    }

    public double getMinorRadius() {
        return minorRadius;
    }

    /**
     * Returns all block Locations inside the toroid
     * @param isFilled If true, fills the tube, if false, returns only the "surface" of the tube
     */
    public List<Location> getBlocks(boolean isFilled) {
        List<Location> blocks = new ArrayList<>();
        World world = center.getWorld();
        int cx = center.getBlockX();
        int cy = center.getBlockY();
        int cz = center.getBlockZ();

        int bound = (int) Math.ceil(majorRadius + minorRadius);

        for (int x = cx - bound; x <= cx + bound; x++) {
            for (int y = cy - bound; y <= cy + bound; y++) {
                for (int z = cz - bound; z <= cz + bound; z++) {
                    double dx = x - cx;
                    double dy = y - cy;
                    double dz = z - cz;

                    double distXY = Math.sqrt(dx*dx + dz*dz);
                    double torusEq = (distXY - majorRadius) * (distXY - majorRadius) + dy*dy;

                    if (isFilled) {
                        if (torusEq <= minorRadius * minorRadius) {
                            blocks.add(new Location(world, x, y, z));
                        }
                    } else {
                        if (torusEq <= minorRadius*minorRadius &&
                                torusEq > (minorRadius - 1)*(minorRadius - 1)) {
                            blocks.add(new Location(world, x, y, z));
                        }
                    }
                }
            }
        }
        return blocks;
    }
}
