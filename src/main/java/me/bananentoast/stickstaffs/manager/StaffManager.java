package me.bananentoast.stickstaffs.manager;

import me.bananentoast.stickstaffs.StickStaffs;
import me.bananentoast.stickstaffs.manager.staff.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StaffManager {

    public StickStaffs instance;

    private final List<String> staffCasedNames = new ArrayList<>();

    private final HashMap<String, BaseStaff> staffNames = new HashMap<>();
    private final HashMap<String, BaseStaff> staffItemNames = new HashMap<>();

    public StaffManager() {
        instance = StickStaffs.getInstance();
    }

    public void loadStaffs() {

        addStaff(new HealStaff());
        addStaff(new LightningStaff());
        addStaff(new TeleportStaff());
        addStaff(new ExplodeStaff());
        addStaff(new CakeStaff());
        addStaff(new ChickenStaff());
        addStaff(new FlyStaff());
        addStaff(new HighligtStaff());
    }

    private void addStaff(BaseStaff newStaff) {
        staffCasedNames.add(newStaff.getName());
        staffNames.put(newStaff.getName().toLowerCase(), newStaff);
        staffItemNames.put(newStaff.getItemName().toLowerCase(), newStaff);

        //Register Recipe
        if (newStaff.getRecipe() != null) {
            instance.getServer().addRecipe(newStaff.getRecipe());
            instance.getLogger().info("Added recipe for " + newStaff.getName());
        }
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

    public List<String> getStaffNames() {
        return new ArrayList<>(staffNames.keySet());
    }

    public List<String> getCasedStaffNames() {
        return staffCasedNames;
    }

}
