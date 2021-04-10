package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;

public class ExplodeStaff extends BaseStaff {

    public ExplodeStaff() {
        super("explode", "§4§lExplode §f§lStaff", Material.TNT);
    }

    @Override
    public void onClick(Player player) {

        if (!consume(player, Material.TNT, 16, false)) {
            player.sendMessage("§4Give me TNT!");
            return;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 10, 10);
        TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
        tnt.setYield(10);
        tnt.setFuseTicks(0);

    }

}
