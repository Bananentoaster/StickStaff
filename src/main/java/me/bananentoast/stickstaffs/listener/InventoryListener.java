package me.bananentoast.stickstaffs.listener;

import me.bananentoast.stickstaffs.StickStaffs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

    public InventoryListener() {
        StickStaffs instance = StickStaffs.getInstance();
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        Inventory inv = event.getWhoClicked().getOpenInventory().getTopInventory();
        if (inv.getType().equals(InventoryType.WORKBENCH) && event.getView().getTitle().contains("§7's Recipe")) {
            event.setCancelled(true);
        }
    }

}
