package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class HealStaff extends BaseStaff {

    private static final double healBonus = 3;

    public HealStaff() {
        super("heal", "§d§lHeal Staff", Material.TOTEM_OF_UNDYING, "Heals you by 1.5 heats");
    }

    @Override
    public void onClick(Player player) {
        player.setHealth(Math.min(player.getHealth() + healBonus, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
        player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 10, 10);
    }

}
