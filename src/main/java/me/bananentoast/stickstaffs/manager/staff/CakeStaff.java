package me.bananentoast.stickstaffs.manager.staff;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

public class CakeStaff extends BaseStaff {

    public CakeStaff() {
        super("cake", "§d§lCake Staff", Material.CAKE, "§7Throw your own cake");
    }

    @Override
    public void onClick(Player player) {

        if (!consume(player, Material.CAKE, 1, false)) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cGive me cake!"));
            return;
        }

        FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(player.getLocation().add(0, 1, 0), Material.CAKE.createBlockData());
        fallingBlock.setVelocity(player.getLocation().getDirection().setY(0.4));
    }

}
