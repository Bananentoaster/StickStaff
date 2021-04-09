package me.bananentoast.stickstaffs.listener;

import me.bananentoast.stickstaffs.StickStaffs;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    private StickStaffs instance;

    public InteractListener(StickStaffs instance) {
        this.instance = instance;
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
            return;
        if (event.getItem() == null)
            return;
        ItemStack item = event.getItem();
        if (item.getType() != Material.STICK)
            return;
        if (!item.hasItemMeta())
            return;
        String itemName = item.getItemMeta().getDisplayName();
        if (!instance.getStaffManager().isStaffByItemName(itemName))
            return;
        instance.getStaffManager().getStaffByItemName(itemName).onClick(event.getPlayer());
    }

}
