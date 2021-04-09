package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HealStaff extends BaseStaff {

    public HealStaff() {
        super("heal", "§d§lHeal Staff", Material.TOTEM_OF_UNDYING);
    }

    @Override
    public void onClick(Player player) {
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);
        player.setSaturation(5);
        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 10, 10);
    }

}
