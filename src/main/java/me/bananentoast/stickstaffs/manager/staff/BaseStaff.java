package me.bananentoast.stickstaffs.manager.staff;

import me.bananentoast.stickstaffs.StickStaffs;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseStaff {

    private final String name;
    private final String itemName;

    private final boolean hasRecipe;
    private final Material recipeMaterial;
    private final List<List<Material>> recipe;

    public BaseStaff(String name, String itemName) {
        this.name = name;
        this.itemName = itemName;
        this.hasRecipe = false;
        this.recipeMaterial = null;
        this.recipe = null;
    }

    public BaseStaff(String name, String itemName, Material recipeMaterial) {
        this.name = name;
        this.itemName = itemName;
        this.hasRecipe = true;
        this.recipeMaterial = recipeMaterial;
        this.recipe = null;
    }

    public BaseStaff(String name, String itemName, List<List<Material>> recipe) {
        this.name = name;
        this.itemName = itemName;
        this.hasRecipe = true;
        this.recipe = recipe;
        this.recipeMaterial = null;
    }

    public abstract void onClick(Player player);

    public ItemStack getItem() {
        ItemStack itemStack = new ItemStack(Material.STICK);
        itemStack.setAmount(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(this.getItemName());
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public String getName() {
        return name;
    }

    public String getItemName() {
        return itemName;
    }

    public Recipe getRecipe() {
        NamespacedKey key = new NamespacedKey(StickStaffs.getInstance(), "staff_" + this.getName());
        ShapedRecipe recipeInstance = new ShapedRecipe(key, this.getItem());
        Map<Material, Character> m = new HashMap<>();
        int current = 0;
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        if (recipeMaterial != null) {
            recipeInstance.shape("AAM", "ASA", "SAA");
            recipeInstance.setIngredient('M', recipeMaterial);
            recipeInstance.setIngredient('S', Material.STICK);
        } else if (recipe != null) {
            List<String> shapes = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String shape = "";
                if (recipe.get(i) != null) {
                    for (int j = 0; j < 3; j++) {
                        if (recipe.get(i).get(j) != null && recipe.get(i).get(j) != Material.AIR) {
                            Character symbol;

                            if (m.containsKey(recipe.get(i).get(j))) {
                                symbol = m.get(recipe.get(i).get(j));
                            } else {
                                symbol = chars.charAt(current);
                                current++;

                                m.put(recipe.get(i).get(j), symbol);
                            }

                            shape += symbol;
                        } else {
                            shape += " ";
                        }
                    }
                } else {
                    shape = "   ";
                }

                shapes.add(shape);
            }

            recipeInstance.shape(shapes.get(0) != null ? shapes.get(0) : "   ", shapes.get(1) != null ? shapes.get(1) : "   ", shapes.get(2) != null ? shapes.get(2) : "   ");

            for (Material mat :
                    m.keySet()) {
                recipeInstance.setIngredient(m.get(mat), mat);
            }
        } else {
            return null;
        }

        return recipeInstance;
    }

    public boolean hasRecipe() {
        return hasRecipe;
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
