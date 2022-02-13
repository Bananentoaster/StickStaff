package me.bananentoast.stickstaffs.manager.staff;

import me.bananentoast.stickstaffs.helpers.OutlineUtil;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class HighligtStaff extends BaseStaff {

    public HighligtStaff() {
        super("highlight", "§c§lHighligt Staff",
                Arrays.asList(
                        Arrays.asList(Material.GLOW_BERRIES, Material.AIR, Material.AIR),
                        Arrays.asList(Material.AIR, Material.STICK, Material.AIR),
                        Arrays.asList(Material.AIR, Material.AIR, Material.STICK)
                )
        );
    }

    @Override
    public void onClick(Player player) {
        Block b = player.getTargetBlockExact(30);
        if(b != null) {
            OutlineUtil.outlineBlocks(Arrays.asList(b), Particle.SCRAPE, 8);
        }else{
            player.sendMessage("§bWhere da block at");
        }
    }
}
