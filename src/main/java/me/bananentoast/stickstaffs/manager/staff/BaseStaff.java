package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public abstract class BaseStaff {

    private String name;
    private String itemName;
    private Material material;

    public BaseStaff(String name, String itemName, Material material) {
        this.name = name;
        this.itemName = itemName;
        this.material = material;
    }

    public abstract void onClick(Player player);

    public String getName() {
        return name;
    }

    public String getItemName() {
        return itemName;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean consume(Player player, Material material, int count, boolean consumeInCreative) {
        if (!consumeInCreative) {
            return true;
        }
        if (!player.getInventory().contains(material, count)) {
            return false;
        }
        PlayerInventory inv = player.getInventory();
        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack itemStack = inv.getItem(i);
            if (itemStack != null) {
                if (itemStack.getType() == material) {
                    if (itemStack.getAmount() >= count) {
                        itemStack.setAmount(itemStack.getAmount() - count);
                        if (itemStack.getAmount() < 1) {
                            inv.clear(i);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
