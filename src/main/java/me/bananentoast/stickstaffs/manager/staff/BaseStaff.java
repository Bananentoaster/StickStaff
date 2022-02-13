package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public abstract class BaseStaff {

    private final String name;
    private final String itemName;

    private final boolean hasRecipe;
    private final Material recipeMaterial;

    public BaseStaff(String name, String itemName) {
        this.name = name;
        this.itemName = itemName;
        hasRecipe = false;
        recipeMaterial = null;
    }

    public BaseStaff(String name, String itemName, Material recipeMaterial) {
        this.name = name;
        this.itemName = itemName;
        this.hasRecipe = true;
        this.recipeMaterial = recipeMaterial;
    }

    public abstract void onClick(Player player);

    public String getName() {
        return name;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean hasRecipe() {
        return hasRecipe;
    }

    public Material getRecipeMaterial() {
        return recipeMaterial;
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
