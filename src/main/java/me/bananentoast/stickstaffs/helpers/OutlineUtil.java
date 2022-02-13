package me.bananentoast.stickstaffs.helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;

import java.util.List;

public final class OutlineUtil {
    public static double offset = 0;

    public static void outlineBlocks(List<Block> blocks, Particle particle, int step) {
        for (Block block :
                blocks) {
            if(block.getType() != Material.AIR) {
                outlineBlockLimited(block, particle, step, blocks);
            }
        }
    }

    private static Block contains(List<Block> blocks, int x, int y, int z) {
        for (Block block:
                blocks) {
            Location loc = block.getLocation();
            if(loc.getX() == x && loc.getY() == y && loc.getZ() == z) {
                return block;
            }
        }
        return null;
    }

    private static boolean check(List<Block> blocks, Block block, int x, int y, int z, boolean biased) {
        Block b = contains(blocks, block.getX() + x, block.getY() + y, block.getZ() + z);
        if(b != null) {
            if(b.getType() == Material.AIR) {
                return biased;
            }
            return true;
        } else {
            return biased && (block.getWorld().getBlockAt(block.getX() + x, block.getY() + y, block.getZ() + z).getType() == Material.AIR);
        }
    }

    private static boolean check(List<Block> blocks, Block block, int x, int y, int z) {
        return check(blocks, block, x, y, z, true);
    }

    private static void outlineBlockLimited(Block block, Particle particle, int step, List<Block> blocks) {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    if(
                            (
                                    !check(blocks, block, 0, 0, (z == 0 ? -1 : 1)) ||
                                            !check(blocks, block, 0, (y == 0 ? -1 : 1), 0)
                            ) || (
                                    !check(blocks, block, 0, (y == 0 ? -1 : 1), (z == 0 ? -1 : 1))
                            ) || (
                                    !check(blocks, block, 0, (y == 0 ? -1 : 1), (z == 0 ? -1 : 1), false) &&
                                            !check(blocks, block, 0, 0, (z == 0 ? -1 : 1), false) &&
                                            !check(blocks, block, 0, (y == 0 ? -1 : 1), 0, false)
                            )
                    ) {
                        if(x == 0) {
                            for (int i = 0; i < step; i++) {
                                block.getWorld().spawnParticle(particle,
                                        block.getBoundingBox().getMinX() + ((block.getBoundingBox().getMaxX() - block.getBoundingBox().getMinX() ) * ((double)i / (double)(step - 1))),
                                        y == 0 ? block.getBoundingBox().getMinY() - offset : block.getBoundingBox().getMaxY() + offset,
                                        z == 0 ? block.getBoundingBox().getMinZ() - offset : block.getBoundingBox().getMaxZ() + offset,
                                        0, 0, 0, 0);
                            }
                        }
                    }
                    if(
                            (
                                    !check(blocks, block, (x == 0 ? -1 : 1), 0, 0) ||
                                            !check(blocks, block, 0, 0, (z == 0 ? -1 : 1))
                            ) || (
                                    !check(blocks, block, (x == 0 ? -1 : 1), 0, (z == 0 ? -1 : 1))
                            ) || (
                                    !check(blocks, block, (x == 0 ? -1 : 1), 0, 0, false) &&
                                            !check(blocks, block, 0, 0, (z == 0 ? -1 : 1), false) &&
                                            !check(blocks, block, (x == 0 ? -1 : 1), 0, (z == 0 ? -1 : 1), false)
                            )
                    ) {
                        if(y == 0) {
                            for (int i = 0; i < step; i++) {
                                block.getWorld().spawnParticle(particle,
                                        x == 0 ? block.getBoundingBox().getMinX() - offset : block.getBoundingBox().getMaxX() + offset,
                                        block.getBoundingBox().getMinY() + ((block.getBoundingBox().getMaxY() - block.getBoundingBox().getMinY() ) * ((double)i / (double)(step - 1))),
                                        z == 0 ? block.getBoundingBox().getMinZ() - offset : block.getBoundingBox().getMaxZ() + offset,
                                        0, 0, 0, 0);
                            }
                        }
                    }
                    if(
                            (
                                    !check(blocks, block, (x == 0 ? -1 : 1), 0, 0) ||
                                            !check(blocks, block, 0, (y == 0 ? -1 : 1), 0)
                            ) || (
                                    !check(blocks, block, (x == 0 ? -1 : 1), (y == 0 ? -1 : 1), 0)
                            ) || (
                                    !check(blocks, block, (x == 0 ? -1 : 1), 0, 0, false) &&
                                            !check(blocks, block, 0, (y == 0 ? -1 : 1), 0, false) &&
                                            !check(blocks, block, (x == 0 ? -1 : 1), (y == 0 ? -1 : 1), 0, false)
                            )
                    ) {
                        if(z == 0) {
                            for (int i = 0; i < step; i++) {
                                block.getWorld().spawnParticle(particle,
                                        x == 0 ? block.getBoundingBox().getMinX() - offset : block.getBoundingBox().getMaxX() + offset,
                                        y == 0 ? block.getBoundingBox().getMinY() - offset : block.getBoundingBox().getMaxY() + offset,
                                        block.getBoundingBox().getMinZ() + ((block.getBoundingBox().getMaxZ() - block.getBoundingBox().getMinZ() ) * ((double)i / (double)(step - 1))),
                                        0, 0, 0, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void outlineBlock(Block block, Particle particle, int step) {
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = 0; z < 2; z++) {
                    if(x == 0) {
                        for (int i = 0; i < step; i++) {
                            block.getWorld().spawnParticle(particle, block.getBoundingBox().getMinX() + ((block.getBoundingBox().getMaxX() - block.getBoundingBox().getMinX() ) * ((double)i / (double)(step - 1))), y == 0 ? block.getBoundingBox().getMinY() - offset : block.getBoundingBox().getMaxY() + offset, z == 0 ? block.getBoundingBox().getMinZ() - offset : block.getBoundingBox().getMaxZ() + offset, 0, 0, 0, 0);
                        }
                    }
                    if(y == 0) {
                        for (int i = 0; i < step; i++) {
                            block.getWorld().spawnParticle(particle, x == 0 ? block.getBoundingBox().getMinX() - offset : block.getBoundingBox().getMaxX() + offset, block.getBoundingBox().getMinY() + ((block.getBoundingBox().getMaxY() - block.getBoundingBox().getMinY() ) * ((double)i / (double)(step - 1))), z == 0 ? block.getBoundingBox().getMinZ() - offset : block.getBoundingBox().getMaxZ() + offset, 0, 0, 0, 0);
                        }
                    }
                    if(z == 0) {
                        for (int i = 0; i < step; i++) {
                            block.getWorld().spawnParticle(particle, x == 0 ? block.getBoundingBox().getMinX() - offset : block.getBoundingBox().getMaxX() + offset, y == 0 ? block.getBoundingBox().getMinY() - offset : block.getBoundingBox().getMaxY() + offset, block.getBoundingBox().getMinZ() + ((block.getBoundingBox().getMaxZ() - block.getBoundingBox().getMinZ() ) * ((double)i / (double)(step - 1))), 0, 0, 0, 0);
                        }
                    }
                }
            }
        }
    }
}
