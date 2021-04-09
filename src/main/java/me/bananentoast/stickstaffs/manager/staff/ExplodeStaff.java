package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ExplodeStaff extends BaseStaff {

    public ExplodeStaff() {
        super("explode", "§4§lExplode §f§lStaff", Material.TNT);
    }

    @Override
    public void onClick(Player player) {

        if (player.getItemInHand().getAmount() > 1) {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
        } else {
            player.setItemInHand(new ItemStack(Material.AIR));
        }
        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 10, 10);
        player.getLocation().getWorld().createExplosion(player.getLocation(), 5);
    }

}
