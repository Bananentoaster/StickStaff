package me.bananentoast.stickstaffs.manager.staff;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class LightningStaff extends BaseStaff {

    public LightningStaff() {
        super("lightning", "§e§lLightning Staff", Material.FIRE_CHARGE, "§7Summon a lightning bolt");
    }

    @Override
    public void onClick(Player player) {

        if (!consume(player, Material.FIRE_CHARGE, 16, false)) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cOut of charges!"));
            return;
        }

        HashSet<Material> set = new HashSet<>();
        set.add(Material.AIR);
        Block block = player.getTargetBlock(set, 512);
        Location location = block.getLocation();
        location.setY(location.getBlockY() + 1);

        location.getWorld().strikeLightning(location);
    }

}
