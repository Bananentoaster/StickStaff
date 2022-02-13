package me.bananentoast.stickstaffs.manager.staff;

import me.bananentoast.stickstaffs.helpers.OutlineUtil;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class HighligtStaff extends BaseStaff {

    public HighligtStaff() {
        super("highlight", "§c§lHighligt Staff");
    }

    @Override
    public void onClick(Player player) {
        Block b = player.getTargetBlockExact(30);
        if(b != null) {
            OutlineUtil.outlineBlock(b, Particle.SOUL, 3);
        }else{
            player.sendMessage("§bWhere da block at");
        }
    }
}
