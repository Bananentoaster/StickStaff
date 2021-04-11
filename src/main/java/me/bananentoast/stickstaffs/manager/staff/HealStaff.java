package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HealStaff extends BaseStaff {

    private final double healBonus = 2.5;

    public HealStaff() {
        super("heal", "§d§lHeal Staff", Material.TOTEM_OF_UNDYING);
    }

    @Override
    public void onClick(Player player) {
        if ((player.getHealth() + healBonus) > player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        } else
            player.setHealth(player.getHealth() + healBonus);
        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 10, 10);
    }

}
