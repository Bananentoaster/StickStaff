package me.bananentoast.stickstaffs.manager.staff;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class TeleportStaff extends BaseStaff {

    public TeleportStaff() {
        super("teleport", "§3§lTeleport Staff", Material.ENDER_PEARL, "§7Teleport...");
    }

    @Override
    public void onClick(Player player) {

        if (!consume(player, Material.ENDER_PEARL, 4, false)) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cGive me pearls!"));
            return;
        }

        HashSet<Material> set = new HashSet<>();
        set.add(Material.AIR);
        Block block = player.getTargetBlock(set, 512);
        Location location = block.getLocation();
        location.setY(location.getBlockY() + 1);
        location.setYaw(player.getLocation().getYaw());
        location.setPitch(player.getLocation().getPitch());

        if (!block.getType().isSolid())
            return;
        if (block.getRelative(BlockFace.UP).getType().isSolid())
            return;
        if (block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType().isSolid())
            return;

        player.teleport(location);
        player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);
    }

}
