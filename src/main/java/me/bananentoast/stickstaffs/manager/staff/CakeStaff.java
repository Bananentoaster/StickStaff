package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

public class CakeStaff extends BaseStaff {

    public CakeStaff() {
        super("cake", "§d§lCake Staff", Material.CAKE);
    }

    @Override
    public void onClick(Player player) {

        if (!consume(player, Material.CAKE, 1, false)) {
            player.sendMessage("§4Give me cake!");
            return;
        }

        FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(player.getLocation().add(0, 1, 0), Material.CAKE.createBlockData());
        fallingBlock.setVelocity(player.getLocation().getDirection().setY(0.4));
    }

}
