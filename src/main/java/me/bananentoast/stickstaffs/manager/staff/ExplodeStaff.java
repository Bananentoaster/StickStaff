package me.bananentoast.stickstaffs.manager.staff;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

public class ExplodeStaff extends BaseStaff {

    public ExplodeStaff() {
        super("explode", "§4§lExplode Staff", Material.TNT, "§7Booooooooooooooooom!");
    }

    @Override
    public void onClick(Player player) {

        if (!consume(player, Material.TNT, 16, false)) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cGive me TNT!"));
            return;
        }

        player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 10, 10);
        TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(player.getLocation(), EntityType.PRIMED_TNT);
        tnt.setYield(100);
        tnt.setFuseTicks(0);

    }

}
