package me.bananentoast.stickstaffs.manager;

import me.bananentoast.stickstaffs.StickStaffs;
import me.bananentoast.stickstaffs.manager.staff.*;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaffManager {

    private StickStaffs instance;

    private final HashMap<String, BaseStaff> staffNames = new HashMap<>();
    private final HashMap<String, BaseStaff> staffItemNames = new HashMap<>();

    public StaffManager(StickStaffs instance) {
        this.instance = instance;
    }

    public void loadStaffs() {
        addStaff(new HealStaff());
        addStaff(new LightningStaff());
        addStaff(new TeleportStaff());
        addStaff(new ExplodeStaff());
        addStaff(new CakeStaff());
        addStaff(new ChickenStaff());
        addStaff(new FlyStaff());
    }

    private void addStaff(BaseStaff newStaff) {
        staffNames.put(newStaff.getName().toLowerCase(), newStaff);
        staffItemNames.put(newStaff.getItemName().toLowerCase(), newStaff);

        //Register Recipe
        NamespacedKey key = new NamespacedKey(instance, "staff_" + newStaff.getName());
        ShapedRecipe recipe = new ShapedRecipe(key, getStaffItem(newStaff));
        recipe.shape("AAM", "ASA", "SAA");
        recipe.setIngredient('M', newStaff.getMaterial());
        recipe.setIngredient('S', Material.STICK);
        instance.getServer().addRecipe(recipe);
    }

    public BaseStaff getStaff(String name) {
        return staffNames.get(name.toLowerCase());
    }

    public BaseStaff getStaffByItemName(String itemName) {
        return staffItemNames.get(itemName.toLowerCase());
    }

    public boolean isStaff(String name) {
        return getStaff(name) != null;
    }

    public boolean isStaffByItemName(String itemName) {
        return getStaffByItemName(itemName) != null;
    }

    public ItemStack getStaffItem(BaseStaff staff) {
        ItemStack itemStack = new ItemStack(Material.STICK);
        itemStack.setAmount(1);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(staff.getItemName());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public List<String> getStaffNames() {
        return new ArrayList<>(staffNames.keySet());
    }

}
